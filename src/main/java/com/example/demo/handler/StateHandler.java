package com.example.demo.handler;

import com.example.demo.entities.*;
import org.hibernate.Hibernate;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Component
@Transactional
public class StateHandler {

    private final precintRepository precintRepository;
    private final DistrictingRepository districtingRepository;
    private final DistrictRepository districtRepository;
    private final countyRepository countyRepository;
    private final JobRepository jobRepository;
    private final StateRepository stateRepository;
    private final JobSummaryRepository jobSummaryRepository;
    private State state;
    private State PA;
    private State NY;
    private State MD;
    private ArrayList<Precinct> allPrecinct = new ArrayList<>();
    private Districting defaultDistricing;
    private Job selectedJob;
    private ArrayList<State> collection;
    private ConstrainedDistrictings constrainedDistrictings = new ConstrainedDistrictings();



    @Autowired

    public StateHandler(com.example.demo.handler.precintRepository precintRepository, DistrictingRepository districtingRepository, DistrictRepository districtRepository, com.example.demo.handler.countyRepository countyRepository, JobRepository jobRepository, StateRepository stateRepository, JobSummaryRepository jobSummaryRepository) throws IOException, ParseException {
        this.precintRepository = precintRepository;
        this.districtingRepository = districtingRepository;
        this.districtRepository = districtRepository;
        this.countyRepository = countyRepository;
        this.jobRepository = jobRepository;
        this.stateRepository = stateRepository;
        this.jobSummaryRepository = jobSummaryRepository;
        this.PA = stateRepository.findById("PENNSYLVANIA").get();
        selectState("PENNSYLVANIA");
    }

    @Transactional
    public List<Precinct> getPrecint() throws ParseException {



        //System.out.println(state.getEnactedDistricting().getDistricts().get(0).getPrecincts());
        //System.out.println(state.getCounties());
        Job job = new Job();
        job.calculateDistrictingGeometry(state.getEnactedDistricting());
        state.getStateBoundary();

        return null;
    }

    public void selectState(String stateId)
    {
        if(stateId == "PENNSYLVANIA")
        {
            state = PA;
        }
        else if(stateId == "NEWYORK")
        {
            state = NY;
        }
        else
        {
            state = MD;
        }
        this.defaultDistricing = state.getEnactedDistricting();
        List<Precinct> allP = state.getPrecincts();
        for(Precinct p : allP)
        {
            this.allPrecinct.add(p);
        }
    }


    public void selectJob( String jobID)
    {
        Job selectedJob = jobRepository.findById(jobID).get();
        this.selectedJob = selectedJob;
        this.selectedJob.setConstrainedDistrictings(new ConstrainedDistrictings());
    }

    public State getPA(){
        return PA;
    }

    public JSONObject calculateDefaultDistrictBoundary() throws ParseException {
        //Job job = new Job();
        Job job = new Job();
        job.calculateDistrictingGeometry(PA.getEnactedDistricting());
        PA.getEnactedDistricting().setDistrictBoundaryJSON();
        JSONObject districtingBoundaries = new JSONObject();
        districtingBoundaries.put("type", "FeatureCollection");
        districtingBoundaries.put("features", PA.getEnactedDistricting().getDistrictBoundaries());
        return districtingBoundaries;
    }

    public JSONObject getPrecinctBoundary() throws ParseException {
        for (int i = 0; i < state.getEnactedDistricting().getDistricts().size(); i++){
            for (int j = 0; j < state.getEnactedDistricting().getDistricts().get(i).getPrecincts().size(); j++){
                state.getEnactedDistricting().getDistricts().get(i).getPrecincts().get(j).setPrecinctCoordinateJson();
            }
            state.getEnactedDistricting().getDistricts().get(i).setPrecinctBoundaryJsonArray();
        }
        state.getEnactedDistricting().setPrecinctBoundaryJSON();
        return state.getEnactedDistricting().getPrecinctBoundaries();
    }

    public void filterDistrictings(Constraints constraints) throws IOException, ParseException {
        //this will filter the 100k districtings down to about 1k districtings
        Job currentJob = this.selectedJob;
        currentJob.setConstraints(constraints);
        currentJob.filterPopEqualityDistrictings();
        currentJob.filterCompactnessGraph();

        ArrayList<Districting> remainingDistricting = currentJob.getConstrainedDistrictings().getDistrictings();

        //remainingDistricting.subList(0,3000);

        currentJob.getConstrainedDistrictings().setDistrictings(new ArrayList<Districting>());

        for( Districting districting : remainingDistricting)
        {
            System.out.println(districting.getDistrictingID());

            HashMap<String, Precinct> newAllPrecint = new HashMap<>();
            for (int i = 0; i < allPrecinct.size(); i++) {
                newAllPrecint.put(allPrecinct.get(i).getPrecinctID(), allPrecinct.get(i));
            }
            String[] haha = districting.getDistrictingID().split("_");
            String fName = haha[0];
            String jobID;
            if(this.selectedJob.getJobID().contains("1"))
            {
                jobID = "1";
            }else if (this.selectedJob.getJobID().contains("2"))
            {
                jobID = "2";
            }
            else
            {
                jobID = "3";
            }


            Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/randomDistricting" + jobID +"/" + fName + ".json"));


            Districting newDistricting = new Districting(districting.getDistrictingID());

            newDistricting.setDistricts(new ArrayList<District>());

            JSONObject jo6 = (JSONObject) obj6;

            JSONObject mid = (JSONObject) jo6.get("districts");

            ArrayList<District> newDistrictCollection = new ArrayList<>();

            ArrayList<District> ToSaveNewDistrictCollection = new ArrayList<>();

            int boundNumber =0;

            if(this.state.getStateID() == "PENNSYLVANIA")
            {
                boundNumber = 19;
            }
            else if(this.state.getStateID() == "NEWYORK")
            {
                boundNumber = 30;
            }
            else
            {
                boundNumber = 9;
            }

            for (int i = 1; i < boundNumber; i++) {

                String name = districting.getDistrictingID() + "_" + Integer.toString(i);

                District toAddDistrict = new District(name);

                toAddDistrict.setDistrictingID(newDistricting);

                JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));

                //System.out.println(toAddDistrict.getPrecincts());

                for (int j = 0; j < ((JSONObject) dArray.get(0)).keySet().size(); j++) {

                    String id = ((JSONObject) dArray.get(0)).keySet().toArray()[j].toString();

                    Precinct toAdd = newAllPrecint.get(id);

                    toAdd.setCurrentDistrictId(toAddDistrict.getDistrictID());

                    //System.out.println(toAdd.getPrecinctID())
                    toAddDistrict.getPrecincts().add(toAdd);
                    toAdd.getDistrictCollection().add(toAddDistrict);

                }


                newDistricting.getDistricts().add(toAddDistrict);

            }
//                    System.out.println("start save");
//                    System.out.println(newDistrictCollection);
            //districtRepository.saveAll(newDistrictCollection);


            currentJob.getConstrainedDistrictings().getDistrictings().add(newDistricting);
        }

        currentJob.filterMajorMinorDistrictings();
        currentJob.filterIncumbentProtectDistrictings();
        System.out.println("Remaining Districtings Left: ");
        System.out.println(currentJob.getConstrainedDistrictings().getDistrictings().size());
    }


    public State getState()
    {
        return state;
    }

}
