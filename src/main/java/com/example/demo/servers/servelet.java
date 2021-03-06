package com.example.demo.servers;
import java.io.IOException;
import java.util.*;

import com.example.demo.entities.*;
import com.example.demo.entities.enums.Measures;
import com.example.demo.handler.JobRepository;
import com.example.demo.handler.JobSummaryRepository;
import com.example.demo.handler.StateHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.locationtech.jts.geom.Geometry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "api")
public class servelet {

    private final StateHandler stateHandler;

    @Autowired
    public servelet(com.example.demo.handler.StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @GetMapping
    public List<Precinct> getPrecint() throws ParseException {
        return stateHandler.getPrecint();
    }

    @GetMapping("/getStateBoundary/PA")
    public JSONObject getStateGeometry() throws ParseException {
        //return (stateHandler.getState().getPrecincts());
        return (stateHandler.getState().getStateBoundaryJson());
    }

    @GetMapping("/getStateDistrictBoundary/PA")
    public JSONObject calculateDefaultDistrictBoundary() throws ParseException, IOException, InterruptedException {
        JSONObject districtingJson = stateHandler.calculateDefaultDistrictBoundary();
        return districtingJson;
    }

    @GetMapping("/getStatePrecinctBoundary/PA")
    public JSONObject getStatePrecinctBoundary() throws ParseException {
        JSONObject precinctsJson = stateHandler.getPrecinctBoundary();
        return precinctsJson;
    }

    @PostMapping("/constraints")
    public HashMap<String, Integer> setConstraints(@RequestBody Constraints constraints) throws IOException, ParseException, InterruptedException {
//        System.out.println("major minor thres is," + constraints.getMajorMinorThres());
//        System.out.println(constraints.getMajorMinorThres());
        stateHandler.filterDistrictings(constraints);
        Job job = stateHandler.getSelectedJob();
        HashMap<String,Integer> constraintsResults= new HashMap<>();
        constraintsResults.put("filteredByMMDCount",job.getFilteredByMMDCount());
        constraintsResults.put("filteredByPopConstraintCount",job.getFilteredByPopConstraintCount());
        constraintsResults.put("filteredByCompactnessCount",job.getFilteredByCompactnessCount());
        constraintsResults.put("filteredByIncumbentCount",job.getFilteredByIncumbentCount());
        constraintsResults.put("constrainedDistrictings", job.getConstrainedDistrictings().getDistrictings().size());
        return constraintsResults;
    }

    @PostMapping("/weights")
    public  ArrayList<ArrayList<Districting>> setWeights(@RequestBody HashMap<Measures,Double> weights) throws ParseException{
        System.out.println(weights);
        stateHandler.calculateObjectiveFunctionScores(weights);
        Job job = stateHandler.getSelectedJob();
        for (int i = 0; i < 10; i++){
            job.getTopDistrictingsByOFScore().get(i).calculateGraphCompactness();
            job.getTopDistrictingsByHighScoreMajMinDistricts().get(i).calculateGraphCompactness();
            job.getTopDistrictingsByEnacted().get(i).calculateGraphCompactness();
            job.getTopDistrictingsByAreaPairDeviation().get(i).calculateGraphCompactness();
            job.getTopDistrictingsByOFScore().get(i).calculatePopulationConstraintAll();
            job.getTopDistrictingsByHighScoreMajMinDistricts().get(i).calculatePopulationConstraintAll();
            job.getTopDistrictingsByEnacted().get(i).calculatePopulationConstraintAll();
            job.getTopDistrictingsByAreaPairDeviation().get(i).calculatePopulationConstraintAll();
        }
        ArrayList<ArrayList<Districting>> topDistrictings = new ArrayList<>();
        topDistrictings.add(job.getTopDistrictingsByOFScore());
        topDistrictings.add(job.getTopDistrictingsByHighScoreMajMinDistricts());
        topDistrictings.add(job.getTopDistrictingsByEnacted());
        topDistrictings.add(job.getTopDistrictingsByAreaPairDeviation());

        return topDistrictings;
    }

//    @PostMapping("/state")
//    public JobSummary getJobs(@RequestBody String state) {
//        stateHandler.selectState(state);
//        System.out.println("JobSummary" + stateHandler.getState().getJobs().get(0).getJobSummary());
//
//        return (stateHandler.getState().getJobs().get(0).getJobSummary());
//
//    }
    @PostMapping("/state")
    public ArrayList<JobSummary> getJobs(@RequestBody String state) {
        stateHandler.selectState(state);
        System.out.println("JobSummary" + stateHandler.getState().getJobs().get(0).getJobSummary());
        ArrayList<JobSummary>JobSumList=new ArrayList<>();
        JobSumList.add(stateHandler.getState().getJobs().get(0).getJobSummary());
        JobSumList.add(stateHandler.getState().getJobs().get(1).getJobSummary());
        JobSumList.add(stateHandler.getState().getJobs().get(2).getJobSummary());
        System.out.println(JobSumList);
        return JobSumList;
    }

    @PostMapping("/job")
    public String getJobID(@RequestBody HashMap<String,String> jobs ) throws InterruptedException {
        String job=jobs.get("jobid");
        String stateName=jobs.get("stateName");

        String jobID="";

        if (stateName.charAt(0)=='P') {
        if (job.charAt(0) == '1') {
            jobID = "PA_JOB1";
        }
        if (job.charAt(0) == '2') {
            jobID = "PA_JOB2";
        }
        if (job.charAt(0) == '3') {
            jobID = "PA_JOB3";
        }
         }
        if (stateName.charAt(0)=='N') {
            if (job.charAt(0) == '1') {
                jobID = "NY_JOB1";
            }
            if (job.charAt(0) == '2') {
                jobID = "NY_JOB2";
            }
            if (job.charAt(0) == '3') {
                jobID = "NY_JOB3";
            }
        }
        if (stateName.charAt(0)=='M') {
            if (job.charAt(0) == '1') {
                jobID = "MD_JOB1";
            }
            if (job.charAt(0) == '2') {
                jobID = "MD_JOB2";
            }
            if (job.charAt(0) == '3') {
                jobID = "MD_JOB3";
            }
        }

        stateHandler.selectJob(jobID);
        return "done";

        //return (stateHandler.getState().getJobs().get(0).getJobSummary());
    }

    @PostMapping("/boxWhisker")
    public Plot calculateBoxWhisker(@RequestBody String DistrictingId) {
        String newDistrictingID = DistrictingId.substring(0, DistrictingId.length() - 1);
        System.out.println(newDistrictingID);
        return stateHandler.retrievePlotData(newDistrictingID);
    }

    @PostMapping("/getJson")
    public JSONObject retrieveDistrictingJSON(@RequestBody String DistrictingId) throws ParseException {
        String newDistrictingID = DistrictingId.substring(0, DistrictingId.length() - 1);
        System.out.println(newDistrictingID);
        return stateHandler.calculateDistrictBoundaries(newDistrictingID);
    }
}
