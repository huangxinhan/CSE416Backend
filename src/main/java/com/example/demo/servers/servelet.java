package com.example.demo.servers;
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
    public JSONObject calculateDefaultDistrictBoundary() throws ParseException {
        JSONObject districtingJson = stateHandler.calculateDefaultDistrictBoundary();
        return districtingJson;
    }

    @GetMapping("/getStatePrecinctBoundary/PA")
    public JSONObject getStatePrecinctBoundary() throws ParseException {
        JSONObject precinctsJson = stateHandler.getPrecinctBoundary();
        return precinctsJson;
    }

    @PostMapping("/constraints")
    public void setConstraints(@RequestBody Constraints constraints) {
        System.out.println(constraints.getCompactnessType());
        stateHandler.filterDistrictings(constraints);
    }

    @PostMapping("/weights")
    public void setWeights(@RequestBody HashMap<Measures,Double> weights) {
        //System.out.println(weights);

    }

    @PostMapping("/state")
    public JobSummary getJobs(@RequestBody String state) {
        System.out.println("JobSummary" + stateHandler.getState().getJobs().get(0).getJobSummary());
        return (stateHandler.getState().getJobs().get(0).getJobSummary());

    }

    @PostMapping("/job")
    public void getJobID(@RequestBody String job) {
        String jobID="";
        //if (stateName.charAt(0)=='P') {
        if (job.charAt(0) == '1') {
            jobID = "PA_JOB1";
        }
        if (job.charAt(0) == '2') {
            jobID = "PA_JOB2";
        }
        if (job.charAt(0) == '3') {
            jobID = "PA_JOB3";
        }
        // }
//        if (stateName.charAt(0)=='N') {
//            if (job.charAt(0) == '1') {
//                jobID = "NY_JOB1";
//            }
//            if (job.charAt(0) == '2') {
//                jobID = "NY_JOB2";
//            }
//            if (job.charAt(0) == '3') {
//                jobID = "NY_JOB3";
//            }
//        }
//        if (stateName.charAt(0)=='M') {
//            if (job.charAt(0) == '1') {
//                jobID = "MD_JOB1";
//            }
//            if (job.charAt(0) == '2') {
//                jobID = "MD_JOB2";
//            }
//            if (job.charAt(0) == '3') {
//                jobID = "MD_JOB3";
//            }
//        }
        System.out.println(jobID);
        stateHandler.selectJob(jobID);

        //return (stateHandler.getState().getJobs().get(0).getJobSummary());
    }
}
