package com.example.demo.handler;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

        Object obj = new JSONParser().parse(new FileReader("/Users/Zino/Downloads/CSE416Backend/src/main/java/com/example/demo/orgJson/PA_precincts.json"));

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

        //------------------------------------------------------------------------------------------

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

            //System.out.println(coordinatesColletion.get(0));





        return args -> {



//            ArrayList<precint> allPrecint = new ArrayList<precint>();
//
//            for( int i=0; i < precintProperties.size(); i++)
//            {
//                JSONObject precintINFO = precintProperties.get(i);
//                precint newPrecint = new precint(
//                        (String) precintINFO.get("GEOID10"),
//                        (String) precintINFO.get("VTDI10"),
//                        (Long) precintINFO.get("TOTPOP"),
//                        (String) "123"
//
//                );
//
//                allPrecint.add(newPrecint);
//            }





            precint test = new precint(
                    "123",
                    "123",
                    123L,
                    "123",
                    coordinatesColletion.get(0)




            );


            precintRepository.save(test);
//            precintRepository.deleteAll();
        };



    }
}
