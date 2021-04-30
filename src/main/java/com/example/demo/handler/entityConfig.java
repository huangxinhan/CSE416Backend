package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

import com.example.demo.entities.County;
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
    CommandLineRunner commandLineRunner( precintRepository precinctRepository, countyRepository countyRepository) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\pa_county.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray pArray = (JSONArray) jo.get("features");

        ArrayList<JSONObject> counties = new ArrayList<JSONObject>();

        for(int i =0; i< pArray.size(); i++)
        {
            counties.add((JSONObject) pArray.get(i));
        }
        ArrayList<JSONObject> countyProperties = new ArrayList<JSONObject>();

        for(int i =0; i< counties.size(); i++)
        {
            countyProperties.add( (JSONObject) counties.get(i).get("properties") );
        }


        ArrayList<JSONObject> countyGeos = new ArrayList<JSONObject>();

        for(int i =0; i< counties.size(); i++)
        {
            countyGeos.add( (JSONObject) counties.get(i).get("geometry") );
        }

        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();

        for(int i =0; i< countyGeos.size(); i++)
        {
            coordinatesJSON.add((JSONArray) countyGeos.get(0).get("coordinates") );
        }

        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion = new ArrayList<ArrayList<ArrayList<Double>>>();




        for(int k= 0; k< coordinatesJSON.size(); k++)
        {
            JSONArray realList = (JSONArray)coordinatesJSON.get(k).get(0);
            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
            for(int i =0; i< realList.size();i++)
            {
                JSONArray realPair = (JSONArray) realList.get(i);

                ArrayList<Double> inner = new ArrayList<Double>();

                for( int j =0; j< realPair.size(); j++)
                {
                    inner.add((Double) realPair.get(j));
                }

                coordinates.add(inner);
            }

            coordinatesColletion.add(coordinates);

        }



            return args -> {



                Object obj1 = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\pa_county.json"));

                HashMap<String,County> allcounty = new HashMap<String,County>();


                for( int i=0; i < countyProperties.size(); i++)
                {

                    JSONObject precinctINFO = countyProperties.get(i);

                    String id = (String) precinctINFO.get("COUNTY_COD");

                    County newCounty = new County(id);

                    newCounty.setCoordinates(coordinatesColletion.get(i));

                    allcounty.put(id,newCounty);

                }

                countyRepository.saveAll(allcounty.values());
            };



    };



    }

