package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.example.demo.entities.precint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileNotFoundException;


@Configuration
public class precintConfig {

    @Bean
    CommandLineRunner commandLineRunner( precintRepository precintRepository) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts.json"));

        JSONObject jo = (JSONObject) obj;

        JSONArray pArray = (JSONArray) jo.get("features");

        ArrayList<JSONObject> precints = new ArrayList<JSONObject>();

        for(int i =0; i< pArray.size(); i++)
        {
            precints.add((JSONObject) pArray.get(i));
        }
        ArrayList<JSONObject> precintProperties = new ArrayList<JSONObject>();

        for(int i =0; i< precints.size(); i++)
        {
            precintProperties.add( (JSONObject) precints.get(i).get("properties") );
        }


        ArrayList<JSONObject> precintGeos = new ArrayList<JSONObject>();

        for(int i =0; i< precints.size(); i++)
        {
            precintGeos.add( (JSONObject) precints.get(i).get("geometry") );
        }

        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();

        for(int i =0; i< precintGeos.size(); i++)
        {
            coordinatesJSON.add((JSONArray) precintGeos.get(0).get("coordinates") );
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

//
//            HashMap<String,precint> allPrecint = new HashMap<String,precint>();
//
//            for( int i=0; i < precintProperties.size(); i++)
//            {
//
//                JSONObject precintINFO = precintProperties.get(i);
//
//                String id = (String) precintINFO.get("GEOID10");
//
//                precint newPrecint = new precint(
//                        (String) precintINFO.get("GEOID10"),
//                        (String) precintINFO.get("VTDST10"),
//                        ((String) precintINFO.get("COUNTYFP10")).substring(1),
//                        coordinatesColletion.get(i)
//
//                );
//
//                allPrecint.put(id,newPrecint);
//            }
//
//            for( String i : allPrecint.keySet())
//            {
//                precint toProcess = allPrecint.get(i);
//
//                Object obj1 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts_seawulf.json"));
//
//                JSONObject jo1 = (JSONObject) obj1;
//
//                JSONObject seaWulfPrecint = (JSONObject) jo1.get(i);
//
//                if( seaWulfPrecint == null)
//                {
//                    System.out.println(i);
//                }
//                JSONArray adjacentNode = (JSONArray) seaWulfPrecint.get("adjacent_nodes");
//
//                ArrayList<precint> neighbours = new ArrayList<precint>();
//
//                for(int j =0; j < adjacentNode.size(); j++)
//                {
//                    String id = (String) adjacentNode.get(j);
//
//                    precint toAdd  = allPrecint.get(id);
//
//                    neighbours.add(toAdd);
//                }
//
//                toProcess.setNeighbours(neighbours);
//            }

        ArrayList<precint> k = new ArrayList<precint>();

            precint haha = new precint(
                    "1",
                    "1",
                    1234L,
                    "1",
                    coordinatesColletion.get(0)


            );

            haha.setNeighbours(k);

            ArrayList<precint> a = new ArrayList<precint>();
            a.add(haha);

            precint papa = new precint(
                    "2",
                    "2",
                    3333L,
                    "2",
                    coordinatesColletion.get(0)


            );

            papa.setNeighbours(a);





            precint dd = (precint) precintRepository.findById("2").get();

            System.out.println(dd.getNeighbours().get(0).getPrecintID());

        //      precintRepository.deleteById("2");
//           precintRepository.save(papa);
//            precintRepository.save(haha);
           // precintRepository.saveAll(allPrecint.values());
//           precintRepository.deleteAll();
        };



    }
}
