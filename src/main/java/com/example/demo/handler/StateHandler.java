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

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
    private State PA;
    private ArrayList<State> collection;



    @Autowired

    public StateHandler(com.example.demo.handler.precintRepository precintRepository, DistrictingRepository districtingRepository, DistrictRepository districtRepository, com.example.demo.handler.countyRepository countyRepository, JobRepository jobRepository, StateRepository stateRepository, JobSummaryRepository jobSummaryRepository) throws IOException, ParseException {
        this.precintRepository = precintRepository;
        this.districtingRepository = districtingRepository;
        this.districtRepository = districtRepository;
        this.countyRepository = countyRepository;
        this.jobRepository = jobRepository;
        this.stateRepository = stateRepository;
        this.jobSummaryRepository = jobSummaryRepository;
        collection = (ArrayList<State>) this.stateRepository.findAll();
        this.PA = this.collection.get(0);

        JobSummary PAJS1 = new JobSummary("PA_JOB1_SUM","PENNSYLVANIA","3","18","50","1");
        JobSummary PAJS2 = new JobSummary("PA_JOB2_SUM","PENNSYLVANIA","3","18","60","2");
        JobSummary PAJS3 = new JobSummary("PA_JOB3_SUM","PENNSYLVANIA","3","18","70","3");
        ArrayList<JobSummary> PAJSColleciton = new ArrayList<JobSummary>();
        PAJSColleciton.add(PAJS1);
        PAJSColleciton.add(PAJS2);
        PAJSColleciton.add(PAJS3);
        // PA
        for (int k = 1; k < 4; k++) {
            String jobName = "PA_JOB" + String.valueOf(k);
            Job jobAdd = new Job(jobName);
            jobAdd.setJobSummary(PAJSColleciton.get(k-1));
            jobAdd.setDistrictings(new ArrayList<Districting>());
            List<Precinct> PA_Precinct_Collection =  this.PA.getPrecincts();
            File folder = new File("src/main/java/com/example/demo/orgJson/randomDistricting" + String.valueOf(k));
            File[] listOfFiles = folder.listFiles();
            List<Precinct> allPrecinct =  PA_Precinct_Collection;
            HashMap<String, Precinct> newAllPrecint = new HashMap<>();
            for (int i = 0; i < allPrecinct.size(); i++) {
                newAllPrecint.put(allPrecinct.get(i).getPrecinctID(), allPrecinct.get(i));

            }


            for (File file : listOfFiles) {
                if (file.isFile() && file.getName().startsWith("PA")) {

                    Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/randomDistricting" + String.valueOf(k) +"/" + file.getName()));

                    String districtingName = file.getName().substring(0, file.getName().indexOf(".json"));

                    Districting newDistricting = new Districting(districtingName);

                    newDistricting.setDistricts(new ArrayList<District>());

                    JSONObject jo6 = (JSONObject) obj6;

                    JSONObject mid = (JSONObject) jo6.get("districts");

                    ArrayList<District> newDistrictCollection = new ArrayList<>();

                    for (int i = 1; i < 19; i++) {

                        String name = districtingName + "_" + Integer.toString(i);

                        District toAddDistrict = new District(name);

                        toAddDistrict.setDistrictingID(newDistricting);

                        JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));

                        //System.out.println(toAddDistrict.getPrecincts());

                        for (int j = 0; j < ((JSONObject) dArray.get(0)).keySet().size(); j++) {

                            String id = ((JSONObject) dArray.get(0)).keySet().toArray()[j].toString();

                            Precinct toAdd = newAllPrecint.get(id);

                            //System.out.println(toAdd.getPrecinctID());
                            toAddDistrict.getPrecincts().add(toAdd);
                            toAdd.getDistrictCollection().add(toAddDistrict);


                        }


                        newDistricting.getDistricts().add(toAddDistrict);
                        newDistrictCollection.add(toAddDistrict);

                    }
//                    System.out.println("start save");
//                    System.out.println(newDistrictCollection);
                    //districtRepository.saveAll(newDistrictCollection);
                    jobAdd.getDistrictings().add(newDistricting);

                }
            }
//            precintRepository.saveAll(newAllPrecint.values());


        PA.getJobs().add(jobAdd);
        }
        System.out.println("finish");
    }

    @Transactional
    public List<Precinct> getPrecint() throws ParseException {



        //System.out.println(PA.getEnactedDistricting().getDistricts().get(0).getPrecincts());
        //System.out.println(PA.getCounties());
        Job job = new Job();
        job.calculateDistrictingGeometry(PA.getEnactedDistricting());
        PA.getStateBoundary();

        return null;
    }

    public JSONObject calculateDefaultDistrictBoundary() throws ParseException {
        //Job job = new Job();
        PA.getJobs().get(0).calculateDistrictingGeometry(PA.getEnactedDistricting());
        //job.calculateDistrictingGeometry(PA.getEnactedDistricting());
        PA.getEnactedDistricting().setDistrictBoundaryJSON();
        JSONObject districtingBoundaries = new JSONObject();
        districtingBoundaries.put("type", "FeatureCollection");
        districtingBoundaries.put("features", PA.getEnactedDistricting().getDistrictBoundaries());
        return districtingBoundaries;
    }

    public JSONObject getPrecinctBoundary() throws ParseException {
        for (int i = 0; i < PA.getEnactedDistricting().getDistricts().size(); i++){
            for (int j = 0; j < PA.getEnactedDistricting().getDistricts().get(i).getPrecincts().size(); j++){
                PA.getEnactedDistricting().getDistricts().get(i).getPrecincts().get(j).setPrecinctCoordinateJson();
            }
            PA.getEnactedDistricting().getDistricts().get(i).setPrecinctBoundaryJsonArray();
        }
        PA.getEnactedDistricting().setPrecinctBoundaryJSON();
        return PA.getEnactedDistricting().getPrecinctBoundaries();
    }

    public void filterDistrictings(){
        //this will filter the 100k districtings down to about 1k districtings
        Job currentJob = PA.getJobs().get(0); //will change when we have several jobs
        currentJob.filterMajorityMinorityDistrictings();
        currentJob.filterPopulationEqualityDistrictings();
        currentJob.filterIncumbentProtectionDistrictings();
        currentJob.filterGraphCompactness();
        System.out.println("Remaining Districtings Left: ");
        System.out.println(currentJob.getConstrainedDistrictings().getDistrictings().size());
    }


    public State getState()
    {
        return PA;
    }

}
