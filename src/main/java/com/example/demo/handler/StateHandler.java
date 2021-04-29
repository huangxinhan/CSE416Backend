package com.example.demo.handler;

import com.example.demo.entities.Precinct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateHandler {

    private final precintRepository precintRepository;

    @Autowired
    public StateHandler(com.example.demo.handler.precintRepository precintRepository) {
        this.precintRepository = precintRepository;
    }


    public List<Precinct> getPrecint()
    {


        return precintRepository.findAll();
    }
}
