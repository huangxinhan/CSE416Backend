package com.example.demo.handler;

import com.example.demo.entities.Job;
import com.example.demo.entities.Precinct;
import com.example.demo.entities.State;
import org.hibernate.Hibernate;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    public StateHandler(com.example.demo.handler.precintRepository precintRepository, DistrictingRepository districtingRepository, DistrictRepository districtRepository, com.example.demo.handler.countyRepository countyRepository, JobRepository jobRepository, StateRepository stateRepository, JobSummaryRepository jobSummaryRepository) {
        this.precintRepository = precintRepository;
        this.districtingRepository = districtingRepository;
        this.districtRepository = districtRepository;
        this.countyRepository = countyRepository;
        this.jobRepository = jobRepository;
        this.stateRepository = stateRepository;
        this.jobSummaryRepository = jobSummaryRepository;
        collection = (ArrayList<State>) this.stateRepository.findAll();
        this.PA= this.collection.get(0);

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
