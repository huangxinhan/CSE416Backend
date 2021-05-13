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
////        GeoJsonReader gReader = new GeoJsonReader();
////
////        Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts_with_incumbents.json"));
////
////        JSONObject jo = (JSONObject) obj;
////
////        JSONArray pArray = (JSONArray) jo.get("features");
////
////        ArrayList<JSONObject> precincts = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < pArray.size(); i++) {
////            precincts.add((JSONObject) pArray.get(i));
////        }
////        ArrayList<JSONObject> precinctProperties = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < precincts.size(); i++) {
////            precinctProperties.add((JSONObject) precincts.get(i).get("properties"));
////        }
////
////
////        ArrayList<JSONObject> precinctGeos = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < precincts.size(); i++) {
////            precinctGeos.add((JSONObject) precincts.get(i).get("geometry"));
////        }
////
////        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();
////
////        ArrayList<String> typies = new ArrayList<String>();
////        for (int i = 0; i < precinctGeos.size(); i++) {
////            coordinatesJSON.add((JSONArray) precinctGeos.get(i).get("coordinates"));
////            typies.add(precinctGeos.get(i).get("type").toString());
////        }
////
////        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion = new ArrayList<ArrayList<ArrayList<Double>>>();
////
//////
//////        for (int k = 0; k < coordinatesJSON.size(); k++) {
//////            JSONArray realList = (JSONArray) coordinatesJSON.get(k).get(0);
//////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
//////            for (int i = 0; i < realList.size(); i++) {
//////                JSONArray realPair = (JSONArray) realList.get(i);
//////
//////                ArrayList<Double> inner = new ArrayList<Double>();
//////
//////                for (int j = 0; j < realPair.size(); j++) {
//////                    inner.add((Double) realPair.get(j));
//////                }
//////
//////                coordinates.add(inner);
//////            }
//////
//////            coordinatesColletion.add(coordinates);
//////
//////        }
////
///////////////////district////////////////
////        Object obj2 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_cd.json"));
////
////        JSONObject jo2 = (JSONObject) obj2;
////
////        JSONArray pArray2 = (JSONArray) jo2.get("features");
////
////        ArrayList<JSONObject> districts = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < pArray2.size(); i++) {
////            districts.add((JSONObject) pArray2.get(i));
////        }
////        ArrayList<JSONObject> districtProperties = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < districts.size(); i++) {
////            districtProperties.add((JSONObject) districts.get(i).get("properties"));
////        }
////
////
////        ArrayList<JSONObject> districtGeos = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < districts.size(); i++) {
////            districtGeos.add((JSONObject) districts.get(i).get("geometry"));
////        }
////
////        ArrayList<JSONArray> coordinatesJSON2 = new ArrayList<JSONArray>();
////
////        ArrayList<String> typies2 = new ArrayList<String>();
////        for (int i = 0; i < districtGeos.size(); i++) {
////            coordinatesJSON2.add((JSONArray) districtGeos.get(i).get("coordinates"));
////            typies2.add(districtGeos.get(i).get("type").toString());
////        }
////
////        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion2 = new ArrayList<ArrayList<ArrayList<Double>>>();
////
//////
//////        for (int k = 0; k < coordinatesJSON2.size(); k++) {
//////            JSONArray realList = (JSONArray) coordinatesJSON2.get(k).get(0);
//////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
//////            for (int i = 0; i < realList.size(); i++) {
//////                JSONArray realPair = (JSONArray) realList.get(i);
//////
//////                ArrayList<Double> inner = new ArrayList<Double>();
//////
//////                for (int j = 0; j < realPair.size(); j++) {
//////                    inner.add((Double) realPair.get(j));
//////                }
//////
//////                coordinates.add(inner);
//////            }
//////
//////            coordinatesColletion2.add(coordinates);
//////
//////        }
///////////////////district////////////////
////
///////////////////county////////////////
////        Object obj3 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/pa_county.json"));
////
////        JSONObject jo3 = (JSONObject) obj3;
////
////        JSONArray pArray3 = (JSONArray) jo3.get("features");
////
////        ArrayList<JSONObject> counties = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < pArray3.size(); i++) {
////            counties.add((JSONObject) pArray3.get(i));
////        }
////        ArrayList<JSONObject> countyProperties = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < counties.size(); i++) {
////            countyProperties.add((JSONObject) counties.get(i).get("properties"));
////        }
////
////
////        ArrayList<JSONObject> countyGeos = new ArrayList<JSONObject>();
////
////        for (int i = 0; i < counties.size(); i++) {
////            countyGeos.add((JSONObject) counties.get(i).get("geometry"));
////        }
////
////        ArrayList<JSONArray> coordinatesJSON3 = new ArrayList<JSONArray>();
////
////        ArrayList<String> typies3 = new ArrayList<String>();
////        for (int i = 0; i < countyGeos.size(); i++) {
////            coordinatesJSON3.add((JSONArray) countyGeos.get(i).get("coordinates"));
////            typies3.add(countyGeos.get(i).get("type").toString());
////        }
////
////        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion3 = new ArrayList<ArrayList<ArrayList<Double>>>();
////
////
//////        for (int k = 0; k < coordinatesJSON3.size(); k++) {
//////            JSONArray realList = (JSONArray) coordinatesJSON3.get(k).get(0);
//////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
//////            for (int i = 0; i < realList.size(); i++) {
//////                JSONArray realPair = (JSONArray) realList.get(i);
//////
//////                ArrayList<Double> inner = new ArrayList<Double>();
//////
//////                for (int j = 0; j < realPair.size(); j++) {
//////                    inner.add((Double) realPair.get(j));
//////                }
//////
//////                coordinates.add(inner);
//////            }
//////
//////            coordinatesColletion3.add(coordinates);
//////
//////        }
////
///////////////////county////////////////
//
//        return args -> {
//
//            JobSummary PAJS1 = new JobSummary("PA_JOB1_SUM","PENNSYLVANIA","3","18","50","1");
//            JobSummary PAJS2 = new JobSummary("PA_JOB2_SUM","PENNSYLVANIA","3","18","60","2");
//            JobSummary PAJS3 = new JobSummary("PA_JOB3_SUM","PENNSYLVANIA","3","18","70","3");
//            ArrayList<JobSummary> PAJSColleciton = new ArrayList<JobSummary>();
//            PAJSColleciton.add(PAJS1);
//            PAJSColleciton.add(PAJS2);
//            PAJSColleciton.add(PAJS3);
//            // PA
//            State PA = stateRepository.findById("PENNSYLVANIA").get();
//            PA.setJobs(new ArrayList<Job>());
//            for (int k = 1; k < 4; k++) {
//                //System.out.println(k);
//                String jobName = "PA_JOB" + String.valueOf(k);
//                Job jobAdd = new Job(jobName);
//
//                jobAdd.setJobSummary(PAJSColleciton.get(k-1));
//                jobAdd.setDistrictings(new ArrayList<Districting>());
//                List<Precinct> PA_Precinct_Collection =  PA.getPrecincts();
//                File folder = new File("src/main/java/com/example/demo/orgJson/randomDistricting" + String.valueOf(k));
//                File[] listOfFiles = folder.listFiles();
//                List<Precinct> allPrecinct =  PA_Precinct_Collection;
//                HashMap<String, Precinct> newAllPrecint = new HashMap<>();
//                for (int i = 0; i < allPrecinct.size(); i++) {
//                    newAllPrecint.put(allPrecinct.get(i).getPrecinctID(), allPrecinct.get(i));
//                }
//                for (File file : listOfFiles) {
//                    if (file.isFile() && file.getName().startsWith("PA")) {
//
//                        Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/randomDistricting" + String.valueOf(k) +"/" + file.getName()));
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
//                        for (int i = 1; i < 19; i++) {
//
//                            String name = districtingName + "_" + Integer.toString(i);
//
//                            District toAddDistrict = new District(name);
//
//                            toAddDistrict.setDistrictingID(newDistricting);
//
//                            JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));
//
//                            //System.out.println(toAddDistrict.getPrecincts());
//
//                            for (int j = 0; j < ((JSONObject) dArray.get(0)).keySet().size(); j++) {
//
//                                String id = ((JSONObject) dArray.get(0)).keySet().toArray()[j].toString();
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
//             PA.getJobs().add(jobAdd);
//
//            }
//            System.out.println(PA.getJobs().size());
//            stateRepository.save(PA);
//            System.out.println("finish");
//
////            ArrayList<Districting> collection = new ArrayList<>();
////
////            for(int i =100000; i < 110000; i++)
////            {
////                Random rand = new Random();
////                Districting toAdd = new Districting(String.valueOf(i),rand.nextDouble(),rand.nextDouble());
////
////                ArrayList<District> Dlist = new ArrayList<>();
////                for(int j =0; j<18; j++)
////                {
////                    String dID = String.valueOf(i) + "_" +String.valueOf(j);
////                    District newD = new District(dID);
////                    Dlist.add(newD);
////                }
////                toAdd.setDistricts(Dlist);
////                collection.add(toAdd);
////
////            }
////
////            districtingRepository.saveAll(collection);
//
////            HashMap<String, Precinct> allprecinct = new HashMap<String, Precinct>();
////            HashMap<String, District> alldistrict = new HashMap<String, District>();
////            HashMap<String, County> allcounty = new HashMap<String, County>();
////
////            //district
////
////            for (int i = 0; i < districtProperties.size(); i++) {
////                JSONObject precinctINFO = districtProperties.get(i);
////
////                String districtId = precinctINFO.get("LEG_DISTRI").toString();
////
////                districtId = ("PAX_" + districtId);
////
////                District newDistrict = new District(districtId);
////
////                Geometry newG = gReader.read(districtGeos.get(i).toString());
////                newDistrict.setBorderGeometry(newG);
////
////                alldistrict.put(districtId, newDistrict);
////            }
////
////            ///district
////            //System.out.println(alldistrict);
////            //district
////
////            for (int i = 0; i < countyProperties.size(); i++) {
////                JSONObject precinctINFO = countyProperties.get(i);
////
////                String countyId = (String) precinctINFO.get("FIPS_COUNT");
////
////                County newCounty = new County(countyId);
////
////                Geometry newG = gReader.read(countyGeos.get(i).toString());
////                newCounty.setCoordinates(newG);
////
////                allcounty.put(countyId, newCounty);
////            }
////
////            //System.out.println(allcounty);
////
////            ///district
////
////
////            for (int i = 0; i < precinctProperties.size(); i++) {
////
////                JSONObject precinctINFO = precinctProperties.get(i);
////
////                String precintId = (String) precinctINFO.get("GEOID10");
////
////                Precinct newPrecinct = new Precinct(precintId);
////
////                Geometry newG = gReader.read(precinctGeos.get(i).toString());
////                newPrecinct.setCoordinates(newG);
////
////                newPrecinct.setIncumbentName((String) precinctINFO.get("incumbent_name"));
////
////                newPrecinct.setTotalPopulation((Long) precinctINFO.get("TOTPOP"));
////
////                newPrecinct.setAfricanAmericanPopulation((Long) precinctINFO.get("NH_BLACK"));
////
////                newPrecinct.setAsianPopulation((Long) precinctINFO.get("NH_ASIAN"));
////
////                newPrecinct.setHispanicPopulation((Long) precinctINFO.get("HISP"));
////
////                newPrecinct.setVotingAgePopulation((Long) precinctINFO.get("VAP"));
////
////                allprecinct.put(precintId, newPrecinct);
////
////                ////////////////////////////
////
////
////                String districtID = (String) "PAX_" + precinctINFO.get("CD_2011").toString();
////
////
////                District tempDistrict = alldistrict.get(districtID);
////
////                String countyID = ((String) precinctINFO.get("COUNTYFP10"));
////
////                County tempCounty = allcounty.get(countyID);
////
////                newPrecinct.setCountyID(tempCounty);
////                newPrecinct.getDistrictCollection().add(tempDistrict);
////                newPrecinct.setDefaultDistrictID(tempDistrict);
////
////                tempCounty.getPrecincts().add(newPrecinct);
////                tempDistrict.getPrecincts().add(newPrecinct);
////
////
////                //precinctRepository.save(tempPrecinct);
////                //countyRepository.save(tempCounty);
////                //districtRepository.save(tempDistrict);
////
////            }
////
////            countyRepository.saveAll(allcounty.values());
////            districtRepository.saveAll(alldistrict.values());
////             //precinctRepository.saveAll(allprecinct.values());
////
////            /////////////////////////////////////
////
////            Object obj4 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/PA_precincts_seawulf.json"));
////            ArrayList<Precinct> modifiedPrecincts = (ArrayList<Precinct>) precinctRepository.findAll();
////            HashMap<String,Precinct> newAllPrecinct = new HashMap<>();
////            for(int i =0 ;i < modifiedPrecincts.size() ;i++)
////            {
////                newAllPrecinct.put(modifiedPrecincts.get(i).getPrecinctID(),modifiedPrecincts.get(i));
////            }
////            for (String i : allprecinct.keySet()) {
////
////                Precinct toProcess = newAllPrecinct.get(i);
////                Precinct aa = allprecinct.get(i);
////
////                JSONObject jo4 = (JSONObject) obj4;
////
////                JSONObject seaWulfprecinct = (JSONObject) jo4.get(i);
////                System.out.println(i);
////                if (seaWulfprecinct == null) {
////                    System.out.println(i);
////                }
////                JSONArray adjacentNode = (JSONArray) seaWulfprecinct.get("adjacent_nodes");
////
////                ArrayList<Precinct> neighbours = new ArrayList<Precinct>();
////
////                for (int j = 0; j < adjacentNode.size(); j++) {
////                    String id = (String) adjacentNode.get(j);
////
////                    Precinct toAdd = allprecinct.get(id);
////
////                    neighbours.add(toAdd);
////                }
////
////                toProcess.setNeighbours(neighbours);
////                aa.setNeighbours(neighbours);
////                modifiedPrecincts.add(toProcess);
////            }
////
////
////
////
////
////            //System.out.println(alldistrict);
////            precinctRepository.saveAll(modifiedPrecincts);
////            //countyRepository.saveAll(allcounty.values());
////            //districtRepository.saveAll(alldistrict.values());
////
////
////            Districting de = new Districting("PAX");
////
////            List<District> result = districtRepository.findAll();
////            ArrayList<District> dDistrict = new ArrayList<>();
////            for(int i =0 ; i< result.size(); i++)
////            {
////                result.get(i).setDistrictingID(de);
////                dDistrict.add(result.get(i));
////
////            }
////
////            de.setDistricts(dDistrict);
////
////            districtRepository.saveAll(dDistrict);
////            districtingRepository.save(de);
////
////
////
////            Object obj5 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/pa_state_bound.json"));
////
////            JSONObject jo5 = (JSONObject) obj5;
////
////            JSONArray pArray5 = (JSONArray) jo5.get("features");
////
////            ArrayList<JSONObject> precincts5 = new ArrayList<JSONObject>();
////
////            for (int i = 0; i < pArray5.size(); i++) {
////                precincts5.add((JSONObject) pArray5.get(i));
////            }
////            ArrayList<JSONObject> precinctProperties5 = new ArrayList<JSONObject>();
////
////            for (int i = 0; i < precincts5.size(); i++) {
////                precinctProperties5.add((JSONObject) precincts5.get(i).get("properties"));
////            }
////
////
////            ArrayList<JSONObject> precinctGeos5 = new ArrayList<JSONObject>();
////
////            for (int i = 0; i < precincts5.size(); i++) {
////                precinctGeos5.add((JSONObject) precincts5.get(i).get("geometry"));
////            }
////
////
////
////            State newState = new State("PENNSYLVANIA");
////
////            Geometry newG = gReader.read(precinctGeos5.get(0).toString());
////            newState.setStateBoundary(newG);
////
////            Districting a = districtingRepository.findById("PAX").get();
////
////            newState.setEnactedDistricting(a);
////
////
////
////            List<County> result3 = countyRepository.findAll();
////            ArrayList<County> dcounty = new ArrayList<>();
////            for(int i =0 ; i< result3.size(); i++)
////            {
////
////                dcounty.add(result3.get(i));
////
////            }
////
////            newState.setCounties(dcounty);
////
////            List<Precinct> result1 = precinctRepository.findAll();
////            ArrayList<Precinct> dcounty1 = new ArrayList<>();
////            for(int i =0 ; i< result1.size(); i++)
////            {
////
////                dcounty1.add(result1.get(i));
////
////            }
////
////            newState.setPrecincts(result1);
////
////
////            stateRepository.save(newState);
////
////
////            File folder = new File("src/main/java/com/example/demo/orgJson/randomDistricting");
////            File[] listOfFiles = folder.listFiles();
////            ArrayList<Precinct> allPrecinct = (ArrayList<Precinct>) result1;
////            HashMap<String,Precinct> newAllPrecint = new HashMap<>();
////            for(int i =0; i < allPrecinct.size(); i++)
////            {
////                newAllPrecint.put(allPrecinct.get(i).getPrecinctID(),allPrecinct.get(i));
////            }
////            for (File file : listOfFiles) {
////                if (file.isFile() && file.getName().startsWith("PA")) {
////
////                    Object obj6 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/randomDistricting/" + file.getName()));
////
////                    String districtingName = file.getName().substring(0,file.getName().indexOf(".json"));
////
////                    Districting newDistricting = new Districting(districtingName);
////
////                    newDistricting.setDistricts(new ArrayList<District>());
////
////                    JSONObject jo6 = (JSONObject) obj6;
////
////                    JSONObject mid = (JSONObject) jo6.get("districts");
////
////                    ArrayList<District> newDistrictCollection = new ArrayList<>();
////
////                    for (int i = 1; i < 19; i++) {
////
////                        String name = districtingName + "_" + Integer.toString(i);
////
////                        District toAddDistrict  = new District(name);
////
////                        toAddDistrict.setDistrictingID(newDistricting);
////
////                        JSONArray dArray = (JSONArray) mid.get(Integer.toString(i));
////
////                        //System.out.println(toAddDistrict.getPrecincts());
////
////                        for(int j =0; j < ((JSONObject)dArray.get(0)).keySet().size(); j++)
////                        {
////
////                            String id = ((JSONObject)dArray.get(0)).keySet().toArray()[j].toString();
////
////                            Precinct toAdd = newAllPrecint.get(id);
////
////                            //System.out.println(toAdd.getPrecinctID());
////                            toAddDistrict.getPrecincts().add(toAdd);
////                            toAdd.getDistrictCollection().add(toAddDistrict);
////
////
////                        }
////
////
////
////                        newDistricting.getDistricts().add(toAddDistrict);
////                        newDistrictCollection.add(toAddDistrict);
////
////                    }
////                    System.out.println("start save");
////                    System.out.println(newDistrictCollection);
////                    //districtRepository.saveAll(newDistrictCollection);
////                    districtingRepository.save(newDistricting);
////
////                }
////            }
////            precinctRepository.saveAll(newAllPrecinct.values());
////            System.out.println("finish");
////
////
//
//
//        };
//    }
//}
//
