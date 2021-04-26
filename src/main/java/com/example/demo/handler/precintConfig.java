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



        return args -> {



            ArrayList<precint> allPrecint = new ArrayList<precint>();

            for( int i=0; i < 10; i++)
            {
                JSONObject precintINFO = precintProperties.get(i);
                precint newPrecint = new precint(
                        (String) precintINFO.get("GEOID10"),
                        (String) precintINFO.get("VTDI10"),
                        (Long) precintINFO.get("TOTPOP"),
                        (String) "123"
                );

                allPrecint.add(newPrecint);
            }


            precintRepository.saveAll(allPrecint);
        };



    }
}
