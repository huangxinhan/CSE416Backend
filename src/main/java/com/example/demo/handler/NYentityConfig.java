//package com.example.demo.handler;
//import java.util.Random;
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
//import java.util.concurrent.TimeUnit;
//import java.util.stream.Collectors;
//
//
//@Configuration
//public class NYentityConfig {
//
//
//    @Bean
//    CommandLineRunner commandLineRaunner(precintRepository precinctRepository, countyRepository countyRepository, DistrictRepository districtRepository, DistrictingRepository districtingRepository, StateRepository stateRepository) throws IOException, ParseException {
//        //stateRepository.deleteById("NEWYORK");
//        GeoJsonReader gReader = new GeoJsonReader();
//
//        Object obj = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/ny_precincts_With_Neighbors_with_incumbents.json"));
//
//        JSONObject jo = (JSONObject) obj;
//
//        JSONArray pArray = (JSONArray) jo.get("features");
//
//        ArrayList<JSONObject> precincts = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < pArray.size(); i++) {
//            precincts.add((JSONObject) pArray.get(i));
//        }
//        ArrayList<JSONObject> precinctProperties = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < precincts.size(); i++) {
//            precinctProperties.add((JSONObject) precincts.get(i).get("properties"));
//        }
//
//
//        ArrayList<JSONObject> precinctGeos = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < precincts.size(); i++) {
//            precinctGeos.add((JSONObject) precincts.get(i).get("geometry"));
//        }
//
//        ArrayList<JSONArray> coordinatesJSON = new ArrayList<JSONArray>();
//
//        ArrayList<String> typies = new ArrayList<String>();
//        for (int i = 0; i < precinctGeos.size(); i++) {
//            coordinatesJSON.add((JSONArray) precinctGeos.get(i).get("coordinates"));
//            typies.add(precinctGeos.get(i).get("type").toString());
//        }
//
//        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion = new ArrayList<ArrayList<ArrayList<Double>>>();
//
////
////        for (int k = 0; k < coordinatesJSON.size(); k++) {
////            JSONArray realList = (JSONArray) coordinatesJSON.get(k).get(0);
////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
////            for (int i = 0; i < realList.size(); i++) {
////                JSONArray realPair = (JSONArray) realList.get(i);
////
////                ArrayList<Double> inner = new ArrayList<Double>();
////
////                for (int j = 0; j < realPair.size(); j++) {
////                    inner.add((Double) realPair.get(j));
////                }
////
////                coordinates.add(inner);
////            }
////
////            coordinatesColletion.add(coordinates);
////
////        }
//
/////////////////district////////////////
////        Object obj2 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/ny_cd.json"));
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
//
////
////        for (int k = 0; k < coordinatesJSON2.size(); k++) {
////            JSONArray realList = (JSONArray) coordinatesJSON2.get(k).get(0);
////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
////            for (int i = 0; i < realList.size(); i++) {
////                JSONArray realPair = (JSONArray) realList.get(i);
////
////                ArrayList<Double> inner = new ArrayList<Double>();
////
////                for (int j = 0; j < realPair.size(); j++) {
////                    inner.add((Double) realPair.get(j));
////                }
////
////                coordinates.add(inner);
////            }
////
////            coordinatesColletion2.add(coordinates);
////
////        }
/////////////////district////////////////
//
/////////////////county////////////////
//        Object obj3 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/counties_ny.json"));
//
//        JSONObject jo3 = (JSONObject) obj3;
//
//        JSONArray pArray3 = (JSONArray) jo3.get("features");
//
//        ArrayList<JSONObject> counties = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < pArray3.size(); i++) {
//            counties.add((JSONObject) pArray3.get(i));
//        }
//        ArrayList<JSONObject> countyProperties = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < counties.size(); i++) {
//            countyProperties.add((JSONObject) counties.get(i).get("properties"));
//        }
//
//
//        ArrayList<JSONObject> countyGeos = new ArrayList<JSONObject>();
//
//        for (int i = 0; i < counties.size(); i++) {
//            countyGeos.add((JSONObject) counties.get(i).get("geometry"));
//        }
//
//        ArrayList<JSONArray> coordinatesJSON3 = new ArrayList<JSONArray>();
//
//        ArrayList<String> typies3 = new ArrayList<String>();
//        for (int i = 0; i < countyGeos.size(); i++) {
//            coordinatesJSON3.add((JSONArray) countyGeos.get(i).get("coordinates"));
//            typies3.add(countyGeos.get(i).get("type").toString());
//        }
//
//        ArrayList<ArrayList<ArrayList<Double>>> coordinatesColletion3 = new ArrayList<ArrayList<ArrayList<Double>>>();
//
//
////        for (int k = 0; k < coordinatesJSON3.size(); k++) {
////            JSONArray realList = (JSONArray) coordinatesJSON3.get(k).get(0);
////            ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
////            for (int i = 0; i < realList.size(); i++) {
////                JSONArray realPair = (JSONArray) realList.get(i);
////
////                ArrayList<Double> inner = new ArrayList<Double>();
////
////                for (int j = 0; j < realPair.size(); j++) {
////                    inner.add((Double) realPair.get(j));
////                }
////
////                coordinates.add(inner);
////            }
////
////            coordinatesColletion3.add(coordinates);
////
////        }
//
/////////////////county////////////////
//
//        //////// NYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY//////////////
//
//        return args -> {
//
//            HashMap<String, Precinct> allprecinct = new HashMap<String, Precinct>();
//            HashMap<String, District> alldistrict = new HashMap<String, District>();
//            HashMap<String, County> allcounty = new HashMap<String, County>();
//
//            //district
//            /////////// file///
//            for (int i = 0; i < precinctProperties.size(); i++) {
//
//                JSONObject precinctINFO = precinctProperties.get(i);
//
//                if (precinctINFO.get("cd_2011") == null){
//                    continue;
//                }
//                String districtId = precinctINFO.get("cd_2011").toString();
//
//                if(alldistrict.containsKey(districtId))
//                    continue;
//
//                districtId = ("NYX_" + districtId);
//
//                District newDistrict = new District(districtId);
//
//                alldistrict.put(districtId, newDistrict);
//            }
//
//            ///district
//            //System.out.println(alldistrict);
//            //district
//            ///////MD_county
//            for (int i = 0; i < countyProperties.size(); i++) {
//                JSONObject precinctINFO = countyProperties.get(i);
//
//                String tempString =  precinctINFO.get("county_fip").toString();
//                String countyId = "NY" + tempString;
//
//                County newCounty = new County(countyId);
//
//                Geometry newG = gReader.read(countyGeos.get(i).toString());
//                newCounty.setCoordinates(newG);
//
//                allcounty.put(countyId, newCounty);
//                System.out.println(countyId);
//            }
//
//            System.out.println(allcounty.keySet());
//
//            ///district
//
//            ///with_incumbent//
//            for (int i = 0; i < precinctProperties.size(); i++) {
//
//                JSONObject precinctINFO = precinctProperties.get(i);
//
//                String precintId = "NY" + (String) precinctINFO.get("GEOID10");
//
//                Precinct newPrecinct = new Precinct(precintId);
//
//                Geometry newG = gReader.read(precinctGeos.get(i).toString());
//                newPrecinct.setCoordinates(newG);
//
//                newPrecinct.setIncumbentName((String) precinctINFO.get("incumbent_name"));
//
//                newPrecinct.setTotalPopulation((Long) precinctINFO.get("POP100"));
//
//                // total minortiy 6% - 30%
//
//                class RandomGaussian {
//
//                    private Random fRandom = new Random();
//
//                    private double getGaussian(double aMean, double aVariance){
//                        return aMean + fRandom.nextGaussian() * aVariance;
//                    }
//
//                    }
//                RandomGaussian gaussian = new RandomGaussian();
//                double MEAN = 18.3f;
//                double VARIANCE = 5.0f;
//
//                double result;
//
//                while(true)
//                {
//                    result = gaussian.getGaussian(MEAN, VARIANCE) ;
//
//                    if(result >=1 && result <=35)
//                        break;
//
//                }
//
//                int totalMinority =  (int) (newPrecinct.getTotalPopulation() * result/100.0);
//
//                double hisPR = gaussian.getGaussian(45, 15)/100.0;
//
//                long hisP = (int)(hisPR * totalMinority);
//
//                Random fRandom = new Random();
//
//                long blackP = (int)((totalMinority - hisP) * (fRandom.nextDouble()));
//
//
//
//
//                newPrecinct.setAfricanAmericanPopulation(blackP);
//
//                newPrecinct.setAsianPopulation((totalMinority - blackP - hisP));
//
//                newPrecinct.setHispanicPopulation(hisP);
//
//                newPrecinct.setVotingAgePopulation((Long) precinctINFO.get("VAP"));
//
//                allprecinct.put(precintId, newPrecinct);
//
//                ////////////////////////////
//
//                if (precinctINFO.get("cd_2011") == null){
//                    continue;
//                }
//
//                String districtID = (String) "NYX_" + precinctINFO.get("cd_2011").toString();
//
//
//                District tempDistrict = alldistrict.get(districtID);
//System.out.println("all district keyset" + alldistrict.keySet());
//System.out.println("the district id" + districtID);
//System.out.println("temp district" + tempDistrict);
//                //String.valueOf((Integer.parseInt((String) precinctINFO.get("COUNTYFP10"))));
//                String countyID = "NY" + String.valueOf((Integer.parseInt((String) precinctINFO.get("COUNTYFP10"))));
//                System.out.println(precintId);
//                System.out.println(countyID);
//                System.out.println(allcounty.keySet());
//                County tempCounty = allcounty.get(countyID);
//
//                newPrecinct.setCountyID(tempCounty);
//                newPrecinct.getDistrictCollection().add(tempDistrict);
//                newPrecinct.setDefaultDistrictID(tempDistrict);
//
//                tempCounty.getPrecincts().add(newPrecinct);
//                tempDistrict.getPrecincts().add(newPrecinct);
//
//
//                //precinctRepository.save(tempPrecinct);
//                //countyRepository.save(tempCounty);
//                //districtRepository.save(tempDistrict);
//
//            }
//
//            countyRepository.saveAll(allcounty.values());
//            districtRepository.saveAll(alldistrict.values());
//            //precinctRepository.saveAll(allprecinct.values());
//
//            /////////////////////////////////////
//            System.out.println("got here");
//            //TimeUnit.SECONDS.sleep(7);
//            Object obj4 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/NY_precincts_seawulf_ver2.json"));
//            List<Precinct> modifiedPrecincts = (List<Precinct>) precinctRepository.findByprecinctIDContaining("NY").stream().collect(Collectors.toList());
//            HashMap<String,Precinct> newAllPrecinct = new HashMap<>();
//            System.out.println("modified precinct size: " + modifiedPrecincts.size());
//            //TimeUnit.SECONDS.sleep(5);
//            for(int i =0 ;i < modifiedPrecincts.size() ;i++)
//            {
//                if(!modifiedPrecincts.get(i).getPrecinctID().substring(0,2).equals("NY")){
//                    System.out.println(modifiedPrecincts.get(i).getPrecinctID().substring(0,2));
//                    continue;
//                }
//                newAllPrecinct.put(modifiedPrecincts.get(i).getPrecinctID(),modifiedPrecincts.get(i));
//            }
//            for (String i : allprecinct.keySet()) {
//
//                Precinct toProcess = newAllPrecinct.get(i);
//                Precinct aa = allprecinct.get(i);
//
//                JSONObject jo4 = (JSONObject) obj4;
//
//                JSONObject seaWulfprecinct = (JSONObject) jo4.get(i.substring(2));
//                System.out.println(i);
//                if (seaWulfprecinct == null) {
//                    System.out.println(i);
//                    continue;
//                }
//                JSONArray adjacentNode = (JSONArray) seaWulfprecinct.get("adjacent_nodes");
//
//                ArrayList<Precinct> neighbours = new ArrayList<Precinct>();
//
//                for (int j = 0; j < adjacentNode.size(); j++) {
//                    String id = (String) adjacentNode.get(j);
//
//                    Precinct toAdd = allprecinct.get("NY"+id);
//
//                    neighbours.add(toAdd);
//                }
//
//                toProcess.setNeighbours(neighbours);
//                aa.setNeighbours(neighbours);
//                modifiedPrecincts.add(toProcess);
//            }
//
//
//
//
//
//            //System.out.println(alldistrict);
//            precinctRepository.saveAll(modifiedPrecincts);
//            //countyRepository.saveAll(allcounty.values());
//            //districtRepository.saveAll(alldistrict.values());
//
//
//            Districting de = new Districting("NYX");
//
//            List<District> result = districtRepository.findBydistrictIDContaining("NY").stream().collect(Collectors.toList());
//
//            ArrayList<District> dDistrict = new ArrayList<>();
//            for(int i =0 ; i< result.size(); i++)
//            {
//                if (!result.get(i).getDistrictID().substring(0,2).equals("NY")){
//                    System.out.println(result.get(i).getDistrictID());
//                    continue;
//                }
//                result.get(i).setDistrictingID(de);
//                dDistrict.add(result.get(i));
//            }
//
//
//            de.setDistricts(dDistrict);
//
//            districtRepository.saveAll(dDistrict);
//            districtingRepository.save(de);
//            System.out.println("check");
//
//
//            Object obj5 = new JSONParser().parse(new FileReader("src/main/java/com/example/demo/orgJson/ny_state_bound.json"));
//
//            JSONObject jo5 = (JSONObject) obj5;
//
//            JSONArray pArray5 = (JSONArray) jo5.get("features");
//
//            ArrayList<JSONObject> precincts5 = new ArrayList<JSONObject>();
//
//            for (int i = 0; i < pArray5.size(); i++) {
//                precincts5.add((JSONObject) pArray5.get(i));
//            }
//            ArrayList<JSONObject> precinctProperties5 = new ArrayList<JSONObject>();
//
//            for (int i = 0; i < precincts5.size(); i++) {
//                precinctProperties5.add((JSONObject) precincts5.get(i).get("properties"));
//            }
//
//
//            ArrayList<JSONObject> precinctGeos5 = new ArrayList<JSONObject>();
//
//            for (int i = 0; i < precincts5.size(); i++) {
//                precinctGeos5.add((JSONObject) precincts5.get(i).get("geometry"));
//            }
//
//
//
//            State newState = new State("NEWYORK");
//
//            Geometry newG = gReader.read(precinctGeos5.get(0).toString());
//            newState.setStateBoundary(newG);
//
//            Districting a = districtingRepository.findById("NYX").get();
//
//            newState.setEnactedDistricting(a);
//
//
//
//            List<County> result3 = countyRepository.findBycountyIDContaining("NY").stream().collect(Collectors.toList());
//            ArrayList<County> dcounty = new ArrayList<>();
//
//            for(int i =2 ; i< result3.size(); i++)
//            {
//                if (!result3.get(i).getCountyID().substring(0,2).equals("NY")){
//                    continue;
//            }
//                System.out.println(result3.get(i).getCountyID());
//                dcounty.add(result3.get(i));
//            }
//            //TimeUnit.MINUTES.sleep(15);
//            newState.setCounties(dcounty);
//
//            List<Precinct> result1 = precinctRepository.findByprecinctIDContaining("NY").stream().collect(Collectors.toList());;
//            ArrayList<Precinct> dcounty1 = new ArrayList<>();
//            for(int i =0 ; i< result1.size(); i++)
//            {
//                if (!result1.get(i).getPrecinctID().substring(0,2).equals("NY")){
//                    continue;
//                }
//                dcounty1.add(result1.get(i));
//
//            }
//
//            newState.setPrecincts(dcounty1);
//
//
//            stateRepository.save(newState);
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
