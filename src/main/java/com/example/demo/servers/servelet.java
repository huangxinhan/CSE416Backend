package com.example.demo.servers;
import java.util.*;

import com.example.demo.entities.County;
import com.example.demo.entities.Precinct;
import com.example.demo.entities.State;
import com.example.demo.handler.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/test1")
public class servelet {

    private final StateHandler stateHandler;

    @Autowired
    public servelet(com.example.demo.handler.StateHandler stateHandler) {
        this.stateHandler = stateHandler;
    }

    @GetMapping
    public List<Precinct> getPrecint()
    {
        return stateHandler.getPrecint();
    }

    @RequestMapping("/index")
    public List<Precinct> getState()
    {
       return (stateHandler.getState().getPrecincts());
    }
    @PostMapping()
    public void addMember(@RequestBody String state) {
//        String[] arrOfStr = state.split("\\+");
//
//        for (String a : arrOfStr)
//            System.out.println(a+"iiii");
        //System.out.println(state);
    }
}
