package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import com.example.demo.entities.County;
import com.example.demo.entities.District;
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
    CommandLineRunner commandLineRunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\PA_precincts_with_incumbents.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray pArray = (JSONArray) jo.get("features");

        ArrayList<JSONObject> counties = new ArrayList<JSONObject>();

        for (int i = 0; i < pArray.size(); i++) {
            counties.add((JSONObject) pArray.get(i));
        }
        ArrayList<JSONObject> countyProperties = new ArrayList<JSONObject>();

        for (int i = 0; i < counties.size(); i++) {
            countyProperties.add((JSONObject) counties.get(i).get("properties"));
        }


        ArrayList<JSONObject> countyGeos = new ArrayList<JSONObject>();

        for (int i = 0; i < counties.size(); i++) {
            countyGeos.add((JSONObject) counties.get(i).get("geometry"));
        }

        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();

        for (int i = 0; i < countyGeos.size(); i++) {
            coordinatesJSON.add((JSONArray) countyGeos.get(0).get("coordinates"));
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


            // Object obj1 = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\PA_precincts_seawulf.json"));


            HashMap<String, Precinct> allprecinct = new HashMap<String, Precinct>();

            ArrayList<County> allcounty = new ArrayList<>();

            ArrayList<District> alldistrict = new ArrayList<>();

//            for( Precinct p : precinctRepository.findAll())
//            {
//                allprecinct.put(p.getPrecinctID(),p);
//            }

            int counter = 0;

            for (int i = 0; i < countyProperties.size(); i++) {

                JSONObject precinctINFO = countyProperties.get(i);

                String id = (String) precinctINFO.get("GEOID10");

                Precinct tempPrecinct = precinctRepository.findById(id).get();

                String districtID = (String) precinctINFO.get("CD_2011").toString();

                District tempDistrict = districtRepository.findById("PAX_" + districtID).get();

                String countyID = ((String) precinctINFO.get("COUNTYFP10"));

                County tempCounty = countyRepository.findById(countyID).get();

                tempPrecinct.setCountyID(tempCounty);
                tempCounty.getPrecincts().add(tempPrecinct);


                tempDistrict.getPrecincts().add(tempPrecinct);

                tempPrecinct.getDistrictCollection().add(tempDistrict);

                allprecinct.put(id, tempPrecinct);

                allcounty.add(tempCounty);

                alldistrict.add(tempDistrict);
                precinctRepository.save(tempPrecinct);
                countyRepository.save(tempCounty);
                districtRepository.save(tempDistrict);
            }

            //precinctRepository.saveAll(allprecinct.values());
//            countyRepository.saveAll(allcounty);
//            districtRepository.saveAll(alldistrict);
        };
    }
}