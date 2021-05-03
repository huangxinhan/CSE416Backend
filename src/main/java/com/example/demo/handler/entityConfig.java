//package com.example.demo.handler;
//
//import java.beans.Transient;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.util.*;
//
//import com.example.demo.entities.*;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.*;
//import org.locationtech.jts.geom.Geometry;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.locationtech.jts.io.geojson.GeoJsonReader;
//import java.io.FileNotFoundException;
//
//
//@Configuration
//public class entityConfig {
//
//
//    @Bean
//    CommandLineRunner commandLineRaunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository, StateRepository stateRepository) throws IOException, ParseException {
//
//
//
//        return args -> {
//            ArrayList<Districting> districtings = new ArrayList<>();
//            File folder = new File("src/main/java/com/example/demo/orgJson/randomDistricting");
//            File[] listOfFiles = folder.listFiles();
//            ArrayList<Precinct> allPrecinct = (ArrayList<Precinct>) precinctRepository.findAll();
//            HashMap<String,Precinct> newAllPrecint = new HashMap<>();
//            for(int i =0; i < allPrecinct.size(); i++)
//            {
//                newAllPrecint.put(allPrecinct.get(i).getPrecinctID(),allPrecinct.get(i));
//            }
//            for (File file : listOfFiles) {
//                if (file.isFile() && file.getName().startsWith("PA")) {
//
//                    Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/randomDistricting/" + file.getName()));
//
//                    String districtingName = file.getName().substring(0,file.getName().indexOf(".json"));
//
//                    Districting a = new Districting(districtingName);
//
//                    a.setDistricts(new ArrayList<District>());
//
//                    JSONObject jo = (JSONObject) obj;
//
//                    JSONObject mid = (JSONObject) jo.get("districts");
//
//
//                    for (int i = 1; i < 19; i++) {
//
//                        String name = districtingName + "_" + Integer.toString(i);
//
//                        District toAddDistrict  = new District(name);
//
//                        JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));
//
//                        //System.out.println(toAddDistrict.getPrecincts());
//
//                        for(int j =0; j < ((JSONObject)dArray.get(0)).keySet().size(); j++)
//                        {
//
//                            String id = ((JSONObject)dArray.get(0)).keySet().toArray()[j].toString();
//
//                            Precinct toAdd = newAllPrecint.get(id);
//
//                            //System.out.println(toAdd.getPrecinctID());
//
//                            toAddDistrict.getPrecincts().add(toAdd);
//                            //toAdd.getDistrictCollection().add(toAddDistrict);
//
//                        }
//
//
//
//                        a.getDistricts().add(toAddDistrict);
//
//
//                    }
//                    districtings.add(a);
//                    //districtingRepository.save(a);
//
//                }
//            }
//            //districtingRepository.saveAll(districtings);
//            precinctRepository.saveAll(newAllPrecint.values());
//            System.out.println("finished running");
//        };
//    }
//
//
//}
