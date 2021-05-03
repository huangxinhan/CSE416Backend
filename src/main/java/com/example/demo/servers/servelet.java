package com.example.demo.servers;
import java.util.*;

import com.example.demo.entities.Constraints;
import com.example.demo.entities.County;
import com.example.demo.entities.Precinct;
import com.example.demo.entities.State;
import com.example.demo.entities.enums.Measures;
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

//    @RequestMapping("/index")
//    public List<Precinct> getState()
//    {
//       return (stateHandler.getState().getPrecincts());
//    }
    @PostMapping()
    public void addMember(@RequestBody String state) {
//        String[] arrOfStr = state.split("\\+");
//
//        for (String a : arrOfStr)
//            System.out.println(a+"iiii");
//        System.out.println(state);
    }
    @GetMapping("/getStateBoundary/PA")
    public JSONObject getStateGeometry() throws ParseException {
        //return (stateHandler.getState().getPrecincts());
        System.out.println(stateHandler.getState().getStateBoundaryJson());
        return (stateHandler.getState().getStateBoundaryJson());
    }

    @GetMapping("/getStateDistrictBoundary/PA")
    public JSONObject calculateDefaultDistrictBoundary() throws ParseException {
        JSONObject districtingJson = stateHandler.calculateDefaultDistrictBoundary();
        return districtingJson;
    }
    @PostMapping("/constraints")
    public void setConstraints(@RequestBody Constraints constraints) {
        System.out.println(constraints.getCompactnessType());
    }
    @PostMapping("/weights")
    public void setWeights(@RequestBody HashMap<Measures,Double> weights) {
        System.out.println(weights);
    }
}
