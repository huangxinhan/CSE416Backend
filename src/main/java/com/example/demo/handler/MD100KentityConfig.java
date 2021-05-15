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
//public class MD100KentityConfig {
//
//
//    @Bean
//    CommandLineRunner commandLineRaunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository, StateRepository stateRepository) throws IOException, ParseException {
//
//
//        return args -> {
//
//            JobSummary MDJS1 = new JobSummary("MD_JOB1_SUM","MARYLAND","3","18","50","1");
//            JobSummary MDJS2 = new JobSummary("MD_JOB2_SUM","MARYLAND","3","18","60","2");
//            JobSummary MDJS3 = new JobSummary("MD_JOB3_SUM","MARYLAND","3","18","70","3");
//            ArrayList<JobSummary> MDJSColleciton = new ArrayList<JobSummary>();
//            MDJSColleciton.add(MDJS1);
//            MDJSColleciton.add(MDJS2);
//            MDJSColleciton.add(MDJS3);
//            // PA
//            State PA = stateRepository.findById("MARYLAND").get();
//            PA.setJobs(new ArrayList<Job>());
//            for (int k = 1; k < 4; k++) {
//                //System.out.println(k);
//                String jobName = "MD_JOB" + String.valueOf(k);
//                Job jobAdd = new Job(jobName);
//
//                jobAdd.setJobSummary(MDJSColleciton.get(k-1));
//                jobAdd.setDistrictings(new ArrayList<Districting>());
//                List<Precinct> PA_Precinct_Collection =  PA.getPrecincts();
//                File folder = new File("src/main/java/com/example/demo/orgJson/MDrandomDistricting" + String.valueOf(k));
//                File[] listOfFiles = folder.listFiles();
//                List<Precinct> allPrecinct =  PA_Precinct_Collection;
//                HashMap<String, Precinct> newAllPrecint = new HashMap<>();
//                for (int i = 0; i < allPrecinct.size(); i++) {
//                    newAllPrecint.put(allPrecinct.get(i).getPrecinctID(), allPrecinct.get(i));
//                }
//                for (File file : listOfFiles) {
//                    if (file.isFile() && (file.getName().startsWith("PA") | file.getName().startsWith("NY") | file.getName().startsWith("MD") )) {
//
//                        Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/MDrandomDistricting" + String.valueOf(k) +"/" + file.getName()));
//
//                        String districtingName = file.getName().substring(0, file.getName().indexOf(".json"));
//
//                        Districting newDistricting = new Districting(districtingName);
//
//                        newDistricting.setDistricts(new ArrayList<District>());
//
//                        JSONObject jo6 = (JSONObject) obj6;
//
//                        JSONObject mid = (JSONObject) jo6.get("districts");
//
//                        ArrayList<District> newDistrictCollection = new ArrayList<>();
//
//                        ArrayList<District> ToSaveNewDistrictCollection = new ArrayList<>();
//
//                        for (int i = 1; i < 9; i++) {
//
//                            String name = districtingName + "_" + Integer.toString(i);
//
//                            District toAddDistrict = new District(name);
//
//                            toAddDistrict.setDistrictingID(newDistricting);
//
//                            JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));
//
//                            int number = 0;
//
//                            //System.out.println(toAddDistrict.getPrecincts());
//
//                            for (int j = 0; j < dArray.size(); j++) {
//
//                                if(j == (dArray.size() -1 ))
//                                {
//                                    number = Integer.parseInt( dArray.get(j).toString());
//                                    break;
//                                }
//
//                                String id = dArray.get(j).toString();
//
//                                Precinct toAdd = newAllPrecint.get(id);
//
//                                toAdd.setCurrentDistrictId(toAddDistrict.getDistrictID());
//
//                                //System.out.println(toAdd.getPrecinctID())
//                                toAddDistrict.getPrecincts().add(toAdd);
//                                toAdd.getDistrictCollection().add(toAddDistrict);
//
//                            }
//
//                            toAddDistrict.setDistrictNumber(number);
//
//
//                            newDistricting.getDistricts().add(toAddDistrict);
//
//                        }
////                    System.out.println("start save");
////                    System.out.println(newDistrictCollection);
//                        //districtRepository.saveAll(newDistrictCollection);
//
//                        newDistricting.calculateGraphCompactness();
//                        for( District i : newDistricting.getDistricts())
//                        {
//                            i.calculateAllPopulation();
//                        }
//                        newDistricting.calculatePopulationConstraintAll();
//
//                        for( District i : newDistricting.getDistricts())
//                        {
//                            i.setPrecincts(new ArrayList<Precinct>());
//                        }
//
//
//                        jobAdd.getDistrictings().add(newDistricting);
//
//                    }
//                }
////            precintRepository.saveAll(newAllPrecint.values());
////
//                PA.getJobs().add(jobAdd);
//
//            }
//            System.out.println(PA.getJobs().size());
//            stateRepository.save(PA);
//            System.out.println("finish");
//
//
//
//        };
//    }
//}
//
