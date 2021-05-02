package com.example.demo.handler;

import com.example.demo.entities.Precinct;
import com.example.demo.entities.State;
import org.hibernate.Hibernate;
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
    private State PA;



    @Autowired

    public StateHandler(com.example.demo.handler.precintRepository precintRepository, DistrictingRepository districtingRepository, DistrictRepository districtRepository, com.example.demo.handler.countyRepository countyRepository, JobRepository jobRepository, StateRepository stateRepository) {
        this.precintRepository = precintRepository;
        this.districtingRepository = districtingRepository;
        this.districtRepository = districtRepository;
        this.countyRepository = countyRepository;
        this.jobRepository = jobRepository;
        this.stateRepository = stateRepository;
        this.PA= this.stateRepository.findById("PENNSYLVANIA").get();

    }

    @Transactional
    public List<Precinct> getPrecint()
    {



        //System.out.println(PA.getEnactedDistricting().getDistricts().get(0).getPrecincts());
        System.out.println(PA.getCounties());

        return null;
    }
    public State getState()
    {

        return PA;
    }

}
