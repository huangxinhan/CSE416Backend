package com.example.demo.servers;

import com.example.demo.entities.precint;
import com.example.demo.handler.StateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
