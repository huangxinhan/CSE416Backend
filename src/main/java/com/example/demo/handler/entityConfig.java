package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import com.example.demo.entities.County;
import com.example.demo.entities.District;
import com.example.demo.entities.Districting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.example.demo.entities.Precinct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileNotFoundException;


@Configuration
public class entityConfig {

    @Bean
    CommandLineRunner commandLineRunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository) throws IOException, ParseException {


            return args->{

                Districting de = new Districting("PAX");

                List<District> result = districtRepository.findAll();
                ArrayList<District> dDistrict = new ArrayList<>();
                for(int i =0 ; i< result.size(); i++)
                {
                    result.get(i).setDistrictingID(de);
                    dDistrict.add(result.get(i));

                }

                de.setDistricts(dDistrict);

                districtRepository.saveAll(dDistrict);

                districtingRepository.save(de);

            };
        };
    }
