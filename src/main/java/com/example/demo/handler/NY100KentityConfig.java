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
//public class NY100KentityConfig {
//
//
//    @Bean
//    CommandLineRunner commandLineRaunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository, StateRepository stateRepository,JobRepository jobRepository) throws IOException, ParseException {
//
//
//        return args -> {
//
//            JobSummary MDJS1 = new JobSummary("NY_JOB1_SUM","MARYLAND","3","18","50","1");
//            JobSummary MDJS2 = new JobSummary("NY_JOB2_SUM","MARYLAND","3","18","60","2");
//            JobSummary MDJS3 = new JobSummary("NY_JOB3_SUM","MARYLAND","3","18","70","3");
//            ArrayList<JobSummary> MDJSColleciton = new ArrayList<JobSummary>();
//            MDJSColleciton.add(MDJS1);
//            MDJSColleciton.add(MDJS2);
//            MDJSColleciton.add(MDJS3);
//            // PA
//            State PA = stateRepository.findById("NEWYORK").get();
//            PA.setJobs(new ArrayList<Job>());
//
//            //System.out.println(k);
//
//
//            int k=2;
//            String jobName = "NY_JOB" + String.valueOf(k);
//            if(PA.getJobs().size() ==0)
//            {
//                Job job1 = new Job("NY_JOB1");
//                job1.setJobSummary(MDJSColleciton.get(0));
//                job1.setDistrictings(new ArrayList<Districting>());
//                PA.setJobs(new ArrayList<Job>());
//                PA.getJobs().add(job1);
//                Job job2 = new Job(jobName);
//                job2.setJobSummary(MDJSColleciton.get(1));
//                job2.setDistrictings(new ArrayList<Districting>());
//                PA.getJobs().add(job2);
//
//                Job job3 = new Job("NY_JOB3");
//                job3.setJobSummary(MDJSColleciton.get(2));
//                job3.setDistrictings(new ArrayList<Districting>());
//                PA.getJobs().add(job3);
//                stateRepository.save(PA);
//            }
//            Job jobAdd = PA.getJobs().get(k-1);
//
//            //jobAdd.setJobSummary(MDJSColleciton.get(k-1));
//            //jobAdd.setDistrictings(new ArrayList<Districting>());
//            List<Precinct> PA_Precinct_Collection =  PA.getPrecincts();
//            File folder = new File("src/main/java/com/example/demo/orgJson/MDrandomDistricting" + String.valueOf(k));
//            File[] listOfFiles = folder.listFiles();
//            List<Precinct> allPrecinct =  PA_Precinct_Collection;
//            HashMap<String, Precinct> newAllPrecint = new HashMap<>();
//            for (int i = 0; i < allPrecinct.size(); i++) {
//                newAllPrecint.put(allPrecinct.get(i).getPrecinctID(), allPrecinct.get(i));
//            }
//            int fn =jobAdd.getDistrictingCollectionNumber()+1;
//            int counter =0;
//            ArrayList<Districting> dHolder = new ArrayList<>();
//            for (; fn< listOfFiles.length; fn++) {
//
//                File file = listOfFiles[fn];
//                if (file.isFile() && (file.getName().startsWith("PA") | file.getName().startsWith("NY") | file.getName().startsWith("MD") )) {
//
//                    Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/MDrandomDistricting" + String.valueOf(k) +"/" + file.getName()));
//
//                    String districtingName = file.getName().substring(0, file.getName().indexOf(".json"));
//
//                    Districting newDistricting = new Districting(districtingName);
//
//                    newDistricting.setDistricts(new ArrayList<District>());
//
//                    JSONObject jo6 = (JSONObject) obj6;
//
//                    JSONObject mid = (JSONObject) jo6.get("districts");
//
//                    ArrayList<District> newDistrictCollection = new ArrayList<>();
//
//                    ArrayList<District> ToSaveNewDistrictCollection = new ArrayList<>();
//
//                    for (int i = 1; i < 9; i++) {
//
//                        String name = districtingName + "_" + Integer.toString(i);
//
//                        District toAddDistrict = new District(name);
//
//                        toAddDistrict.setDistrictingID(newDistricting);
//
//                        JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));
//
//                        int number = 0;
//
//                        //System.out.println(toAddDistrict.getPrecincts());
//
//                        for (int j = 0; j < dArray.size(); j++) {
//
//                            if(j == (dArray.size() -1 ))
//                            {
//                                number = Integer.parseInt( dArray.get(j).toString());
//                                break;
//                            }
//
//                            String id = dArray.get(j).toString();
//
//                            Precinct toAdd = newAllPrecint.get("NY"+ id);
//
//                            toAdd.setCurrentDistrictId(toAddDistrict.getDistrictID());
//
//                            //System.out.println(toAdd.getPrecinctID())
//                            toAddDistrict.getPrecincts().add(toAdd);
//                            toAdd.getDistrictCollection().add(toAddDistrict);
//
//                        }
//
//                        toAddDistrict.setDistrictNumber(number);
//
//
//                        newDistricting.getDistricts().add(toAddDistrict);
//
//                    }
////                    System.out.println("start save");
////                    System.out.println(newDistrictCollection);
//                    //districtRepository.saveAll(newDistrictCollection);
//
//                    newDistricting.calculateGraphCompactness();
//                    for( District i : newDistricting.getDistricts())
//                    {
//                        i.calculateAllPopulation();
//                    }
//                    newDistricting.calculatePopulationConstraintAll();
//
//                    for( District i : newDistricting.getDistricts())
//                    {
//                        i.setPrecincts(new ArrayList<Precinct>());
//                    }
//
//                    newDistricting.setJob(jobAdd.getJobID());
//                    dHolder.add(newDistricting);
//
//                }
//                counter ++;
//
//                if(counter ==10000)
//                {
//                    jobAdd.setDistrictingCollectionNumber(jobAdd.getDistrictingCollectionNumber() + dHolder.size());
//                    jobRepository.save(jobAdd);
//                    districtingRepository.saveAll(dHolder);
//                    dHolder = new ArrayList<>();
//                }
//            }
////            precintRepository.saveAll(newAllPrecint.values());
////
//
//
//
//
//
//
//
//        };
//    }
//}
//
