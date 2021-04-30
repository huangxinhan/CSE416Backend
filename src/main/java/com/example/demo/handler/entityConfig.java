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

        Object obj = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\PA_precincts_with_incumbents.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray pArray = (JSONArray) jo.get("features");

        ArrayList<JSONObject> precincts = new ArrayList<JSONObject>();

        for(int i =0; i< pArray.size(); i++)
        {
            precincts.add((JSONObject) pArray.get(i));
        }
        ArrayList<JSONObject> precinctProperties = new ArrayList<JSONObject>();

        for(int i =0; i< precincts.size(); i++)
        {
            precinctProperties.add( (JSONObject) precincts.get(i).get("properties") );
        }


        ArrayList<JSONObject> precinctGeos = new ArrayList<JSONObject>();

        for(int i =0; i< precincts.size(); i++)
        {
            precinctGeos.add( (JSONObject) precincts.get(i).get("geometry") );
        }

        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();

        for(int i =0; i< precinctGeos.size(); i++)
        {
            coordinatesJSON.add((JSONArray) precinctGeos.get(0).get("coordinates") );
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



            Object obj1 = new JSONParser().parse(new FileReader("C:\\Users\\huang\\Desktop\\demo\\src\\main\\java\\com\\example\\demo\\PA_precincts_seawulf.json"));

            HashMap<String,Precinct> allprecinct = new HashMap<String,Precinct>();

             for( Precinct p : precinctRepository.findAll())
             {
                 allprecinct.put(p.getPrecinctID(),p);
             }

            for( int i=0; i < precinctProperties.size(); i++)
            {

                JSONObject precinctINFO = precinctProperties.get(i);

                String id = (String) precinctINFO.get("GEOID10");

                Precinct toProcess = allprecinct.get(id);


                toProcess.setCoordinates(coordinatesColletion.get(i));

                toProcess.setIncumbentName((String) precinctINFO.get("incumbent_name"));

                toProcess.setTotalPopulation((Long) precinctINFO.get("TOTPOP"));

                toProcess.setAfricanAmericanPopulation((Long) precinctINFO.get("NH_BLACK"));

                toProcess.setAsianPopulation((Long) precinctINFO.get("NH_ASIAN"));

                toProcess.setHispanicPopulation((Long) precinctINFO.get("HISP"));




            }

            precinctRepository.saveAll(allprecinct.values());
        };





    }
}