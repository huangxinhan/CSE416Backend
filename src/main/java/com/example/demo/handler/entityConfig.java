package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import com.example.demo.entities.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileNotFoundException;


@Configuration
public class entityConfig {

    @Bean
    CommandLineRunner commandLineRunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository, StateRepository stateRepository) throws IOException, ParseException {


        Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/pa_state_bound.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray pArray = (JSONArray) jo.get("features");

        ArrayList<JSONObject> precincts = new ArrayList<JSONObject>();

        for (int i = 0; i < pArray.size(); i++) {
            precincts.add((JSONObject) pArray.get(i));
        }
        ArrayList<JSONObject> precinctProperties = new ArrayList<JSONObject>();

        for (int i = 0; i < precincts.size(); i++) {
            precinctProperties.add((JSONObject) precincts.get(i).get("properties"));
        }


        ArrayList<JSONObject> precinctGeos = new ArrayList<JSONObject>();

        for (int i = 0; i < precincts.size(); i++) {
            precinctGeos.add((JSONObject) precincts.get(i).get("geometry"));
        }

        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();

        for (int i = 0; i < precinctGeos.size(); i++) {
            coordinatesJSON.add((JSONArray) precinctGeos.get(0).get("coordinates"));
        }

        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion = new ArrayList<ArrayList<ArrayList<Double>>>();


        for (int k = 0; k < coordinatesJSON.size(); k++) {
            JSONArray realList = (JSONArray) coordinatesJSON.get(k).get(0);
            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
            for (int i = 0; i < realList.size(); i++) {
                JSONArray realPair = (JSONArray) realList.get(i);

                ArrayList<Double> inner = new ArrayList<Double>();

                for (int j = 0; j < realPair.size(); j++) {
                    inner.add((Double) realPair.get(j));
                }

                coordinates.add(inner);
            }

            coordinatesColletion.add(coordinates);

        }


        return args -> {


            State newState = new State("PENNSYLVANIA");

            newState.setStateBoundary(coordinatesColletion.get(0));

            Districting a = districtingRepository.findById("PAX").get();

            newState.setEnactedDistricting(a);



            List<County> result = countyRepository.findAll();
            ArrayList<County> dcounty = new ArrayList<>();
            for(int i =0 ; i< result.size(); i++)
            {

                dcounty.add(result.get(i));

            }

            newState.setCounties(dcounty);

            List<Precinct> result1 = precinctRepository.findAll();
            ArrayList<Precinct> dcounty1 = new ArrayList<>();
            for(int i =0 ; i< result1.size(); i++)
            {

                dcounty1.add(result1.get(i));

            }

            newState.setPrecincts(result1);


            stateRepository.save(newState);

        };
    }
}
