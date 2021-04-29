package com.example.demo.handler;

import java.beans.Transient;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import com.example.demo.entities.Precinct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.io.FileNotFoundException;


@Configuration
public class precintConfig {

    @Bean
    CommandLineRunner commandLineRunner( precintRepository precinctRepository) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts.json"));

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

//            HashMap<String,Precinct> allprecinct = new HashMap<String,Precinct>();
//            for( int i=0; i < precinctProperties.size(); i++)
//            {
//
//                JSONObject precinctINFO = precinctProperties.get(i);
//
//                String id = (String) precinctINFO.get("GEOID10");
//                System.out.println(id);
//
//                Precinct newprecinct = new Precinct(
//                        (String) precinctINFO.get("CD_2011").toString(),
//                        (String) precinctINFO.get("GEOID10"),
//                        (Long) precinctINFO.get("TOTPOP"),
//                        ((String) precinctINFO.get("COUNTYFP10")).substring(1),
//                        coordinatesColletion.get(i)
//
//                );
//
//                allprecinct.put(id,newprecinct);
//            }
            int counter = 0;

            HashMap<String,Precinct> allprecinct = new HashMap<String,Precinct>();

            List<Precinct> stored = precinctRepository.findAll();
            for(int i =0; i < stored.size(); i++)
            {
                allprecinct.put(stored.get(i).getprecinctID(), stored.get(i));
            }
            for( String i : allprecinct.keySet())
            {
                counter++;
                System.out.println(counter);
                Precinct toProcess = allprecinct.get(i);

                Object obj1 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts_seawulf.json"));

                JSONObject jo1 = (JSONObject) obj1;

                JSONObject seaWulfprecinct = (JSONObject) jo1.get(i);

                if( seaWulfprecinct == null)
                {
                    System.out.println(i);
                }
                JSONArray adjacentNode = (JSONArray) seaWulfprecinct.get("adjacent_nodes");

                ArrayList<String> neighbours = new ArrayList<String>();

                for(int j =0; j < adjacentNode.size(); j++)
                {
                    String id = (String) adjacentNode.get(j);

                    //Precinct toAdd  = allprecinct.get(id);

                    neighbours.add(id);
                }
                System.out.println(neighbours);
                toProcess.setNeighbours(neighbours);
                precinctRepository.save(toProcess);

            }

//            //System.out.println(allprecinct);
//
//        ArrayList<Precinct> k = new ArrayList<Precinct>();
//
//            Precinct haha = new Precinct(
//                    "1",
//                    "1",
//                    1234L,
//                    "1",
//                    coordinatesColletion.get(0)
//
//
//            );
//
//            haha.setNeighbours(k);
//
//            ArrayList<Precinct> a = new ArrayList<Precinct>();
//            a.add(haha);
//
//            Precinct papa = new Precinct(
//                    "2",
//                    "2",
//                    3333L,
//                    "2",
//                    coordinatesColletion.get(0)
//
//
//            );
//
//            papa.setNeighbours(a);
//            System.out.println(papa);
//
//            k.add(papa);
//            k.add(haha);




            //System.out.println(allprecinct.values().toArray()[0]);
            //precinct dd = (precinct) precinctRepository.findById("2").get();

            //System.out.println(dd.getNeighbours().get(0).getprecinctID());

        //      precinctRepository.deleteById("2");
//           precinctRepository.save(papa);
            //precinctRepository.save(haha);
            //precinctRepository.save((precinct) allprecinct.values().toArray()[0]);
            //precinctRepository.saveAll(allprecinct.values());
           //precinctRepository.deleteAll();
        };



    }
}
