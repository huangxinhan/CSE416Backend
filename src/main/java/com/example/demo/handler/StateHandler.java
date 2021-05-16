package com.example.demo.handler;

import com.example.demo.entities.*;
import com.example.demo.entities.enums.CompactnessType;
import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;
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
import java.util.Random;

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
    private Plot plot = new Plot();



    @Autowired

    public StateHandler(com.example.demo.handler.precintRepository precintRepository, DistrictingRepository districtingRepository, DistrictRepository districtRepository, com.example.demo.handler.countyRepository countyRepository, JobRepository jobRepository, StateRepository stateRepository, JobSummaryRepository jobSummaryRepository) throws IOException, ParseException {
        this.precintRepository = precintRepository;
        this.districtingRepository = districtingRepository;
        this.districtRepository = districtRepository;
        this.countyRepository = countyRepository;
        this.jobRepository = jobRepository;
        this.stateRepository = stateRepository;
        this.jobSummaryRepository = jobSummaryRepository;

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
        System.out.println(stateId);
        if(stateId.charAt(0) == 'P')
        {
            state = stateRepository.findById("PENNSYLVANIA").get();
        }
        else if(stateId.charAt(0) == 'N')
        {
            state = stateRepository.findById("NEWYORK").get();
        }
        else
        {
            state = stateRepository.findById("MARYLAND").get();
        }
        System.out.println("done");
        this.defaultDistricing = state.getEnactedDistricting();
        List<Precinct> allP = state.getPrecincts();
        for(Precinct p : allP)
        {
            this.allPrecinct.add(p);
        }
    }

    public Job getSelectedJob() {
        return selectedJob;
    }

    public void selectJob(String jobID)
    {
        Job selectedJob = jobRepository.findById(jobID).get();
        this.selectedJob = selectedJob;
        this.selectedJob.setDistrictings(districtingRepository.findByjobID(jobID));
        this.selectedJob.setConstrainedDistrictings(new ConstrainedDistrictings());
        this.selectedJob.getConstrainedDistrictings().setPlot(new Plot());
        this.selectedJob.getConstrainedDistrictings().setEnactedDistricting(state.getEnactedDistricting());
    }

    public State getPA(){
        return PA;
    }

    public JSONObject calculateDefaultDistrictBoundary() throws ParseException, IOException {
        Constraints constraints = new Constraints();
        constraints.setCompactnessType(CompactnessType.GRAPH_COMPACTNESS);
        constraints.setPopulationType(PopulationType.TOTAL);
        constraints.setCompactnessValue(5000);
        constraints.setMinorityType(RaceType.AFRICAN_AMERICAN);
        constraints.setMajorMinorThres(0.3);
        constraints.setNumberOfMajorityMinorityDistricts(3);
        constraints.setPopulationEqualityThres(500);
        constraints.setPopulationValue(4l);
        constraints.setIncumbentValue(null);
        selectJob(PA.getJobs().get(0).getJobID());
        filterDistrictings(constraints);
        //Job job = new Job();
        Job job = new Job();
        job.calculateDistrictingGeometry(selectedJob.getConstrainedDistrictings().getDistrictings().get(4));
        selectedJob.getConstrainedDistrictings().getDistrictings().get(4).setDistrictBoundaryJSON();
        JSONObject districtingBoundaries = new JSONObject();
        districtingBoundaries.put("type", "FeatureCollection");
        districtingBoundaries.put("features", selectedJob.getConstrainedDistrictings().getDistrictings().get(4).getDistrictBoundaries());
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

    public void calculateObjectiveFunctionScores(HashMap<Measures, Double> weights) throws ParseException {
        Job currentJob = selectedJob;
        currentJob.setWeights(weights);
        currentJob.calculateDistrictingScoresByObjectiveFunction();



//        for (int i = 0; i < currentJob.getConstrainedDistrictings().getDistrictings().size(); i++){
//            for (int j = 0; j < currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().size(); j++){
//                currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).setPrecincts(null);
////                for (int k = 0; k < currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().size(); k++){
////                    //System.out.println("precinct coordinates: " + currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).getCoordinates());
////                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setDistrictCollection(new ArrayList<>());
////                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setNeighbours(new ArrayList<>());
////                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setDefaultDistrictID(null);
////                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setCountyID(null);
////                }
//            }
//        }
    }

    public void filterDistrictings(Constraints constraints) throws IOException, ParseException {
        //this will filter the 100k districtings down to about 1k districtings
        Job currentJob = selectedJob;
        currentJob.setConstraints(constraints);
        currentJob.filterPopEqualityDistrictings();
        currentJob.filterCompactnessGraph();

        ArrayList<Districting> remainingDistricting = currentJob.getConstrainedDistrictings().getDistrictings();

        //remainingDistricting.subList(0,3000);

        currentJob.getConstrainedDistrictings().setDistrictings(new ArrayList<Districting>());

        for( Districting districting : remainingDistricting)
        {
            //System.out.println(districting.getDistrictingID());

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

            String fileLocator;

            if(this.state.getStateID() == "PENNSYLVANIA")
            {
                fileLocator = "PA";
            }
            else if(this.state.getStateID() == "NEWYORK")
            {
                fileLocator = "NY";
            }
            else
            {
                fileLocator = "MD";
            }


            Object obj6 = new JSONParser().parse(new BufferedReader(new FileReader("src/main/java/com/example/demo/orgJson/" + fileLocator +"randomDistricting" + jobID +"/" + fName + ".json")));


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

                for (int j = 0; j < dArray.size(); j++) {
                    if (j == dArray.size()-1){
                        continue;
                    }
                    String id = (String) dArray.get(j).toString();

                    Precinct toAdd;

                    if(this.state.getStateID() == "NEWYORK")
                    {   toAdd = newAllPrecint.get("NY" + id);}
                    else if(this.state.getStateID() == "MARYLAND")
                    {
                        toAdd = newAllPrecint.get("MD" + id);
                    }
                    else
                    {
                        toAdd = newAllPrecint.get(id);
                    }

                    //System.out.println(id);
                    toAdd.setCurrentDistrictId(toAddDistrict.getDistrictID());

                    //System.out.println(toAdd.getPrecinctID())
                    toAddDistrict.getPrecincts().add(toAdd);
                    toAdd.getDistrictCollection().add(toAddDistrict);

                }

                toAddDistrict.calculateAllPopulation();
                newDistricting.getDistricts().add(toAddDistrict);
                newDistricting.calculateGraphCompactness();
                newDistricting.calculatePopulationConstraintAll();
                //System.out.println(newDistricting.getDistricts().get(0).getPrecincts());
            }
//                    System.out.println("start save");
//                    System.out.println(newDistrictCollection);
            //districtRepository.saveAll(newDistrictCollection);
            ArrayList<County> tempCounties = new ArrayList<>();
            for (int i = 0; i< this.state.getCounties().size(); i++){
                tempCounties.add(this.state.getCounties().get(i));
            }
            newDistricting.setCounties(tempCounties);
            currentJob.getConstrainedDistrictings().getDistrictings().add(newDistricting);
        }

        currentJob.filterMajorMinorDistrictings();
        currentJob.filterIncumbentProtectDistrictings();

        if (currentJob.getConstrainedDistrictings().getDistrictings().size() == 0){
            currentJob.getConstrainedDistrictings().getDistrictings().add(currentJob.getDistrictings().get(0));
        }

        currentJob.calculateAverageDistricting(constraints.getMinorityType());

//        for (int i = 0; i < currentJob.getConstrainedDistrictings().getDistrictings().size(); i++){
////            currentJob.calculateDistrictingGeometry(currentJob.getConstrainedDistrictings().getDistrictings().get(i));
////            currentJob.getConstrainedDistrictings().getDistrictings().get(i).setDistrictBoundaryJSON();
////            JSONObject districtingBoundaries = new JSONObject();
////            districtingBoundaries.put("type", "FeatureCollection");
////            districtingBoundaries.put("features", currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistrictBoundaries());
////            currentJob.getConstrainedDistrictings().getDistrictings().get(i).setDistrictingBoundary(districtingBoundaries);
////            System.out.println("done with one!" + i);
//
//            for (int j = 0; j < currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().size(); j++){
//                //currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).setPrecincts(new ArrayList<Precinct>());
//                for (int k = 0; k < currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().size(); k++){
//                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setDistrictCollection(new ArrayList<>());
//                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setNeighbours(new ArrayList<>());
//                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setDefaultDistrictID(null);
//                    currentJob.getConstrainedDistrictings().getDistrictings().get(i).getDistricts().get(j).getPrecincts().get(k).setCountyID(null);
//                }
//            }
//        }
        //selectedJob = currentJob;
    }


    public State getState()
    {
        return state;
    }

}
