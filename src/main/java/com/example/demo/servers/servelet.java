package com.example.demo.servers;
import java.util.*;
import com.example.demo.entities.precint;
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
    public List<precint> getPrecint()
    {
        return stateHandler.getPrecint();
    }

    @RequestMapping("/index")
    String index(){
        //mapped to hostname:port/home/index/
        return "Hello from index";
    }
    @PostMapping()
    public void addMember(@RequestBody String state) {
//        String[] arrOfStr = state.split("\\+");
//
//        for (String a : arrOfStr)
//            System.out.println(a+"iiii");
        System.out.println(state);
    }
}
