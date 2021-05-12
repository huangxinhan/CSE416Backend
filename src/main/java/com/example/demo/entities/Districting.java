package com.example.demo.entities;

import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.simple.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Districting implements Serializable{
    private String districtingID;

    private List<District> districts;
    private double deviationFromAverage;
    private double deviationFromEnactedPop;
    private double deviationFromEnactedArea;
    private double objectiveFunctionScore;
    private double similarityToEnactedScore;
    private ArrayList<District> majorityMinorityDistricts;
    private int numberOfMajorityMinorityDistricts;
    private double compactness;
    //incumbentDistribution: Map<district: District, List<incumbent>> Ignore
    private int majorityMinorityDistrictsNumber;
    private int populationEqualityDifference;
    private double splitCountyScore;
    private Long tempPopulationByType;
    private HashMap<County, Integer> splitCountyDetails;
    private ArrayList<JSONObject> districtBoundaries;
    private JSONObject precinctBoundaries;
    private double populationPercentDifference;
    private double graphCompactness;


    public Districting(){

    }

    public Districting(String districtingID) {
        this.districtingID = districtingID;
    }

    public Districting(String districtingID, ArrayList<District> districts){
        this.districtingID = districtingID;
        this.districts = districts;
    }
    @Id
    public String getDistrictingID() {
        return districtingID;
    }

    public void setDistrictingID(String districtingID) {
        this.districtingID = districtingID;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @Transient
    public double getDeviationFromAverage() {
        return deviationFromAverage;
    }

    public void setDeviationFromAverage(double deviationFromAverage) {
        this.deviationFromAverage = deviationFromAverage;
    }
    @Transient
    public double getDeviationFromEnactedPop() {
        return deviationFromEnactedPop;
    }

    public void setDeviationFromEnactedPop(double deviationFromEnactedPop) {
        this.deviationFromEnactedPop = deviationFromEnactedPop;
    }
    @Transient
    public double getDeviationFromEnactedArea() {
        return deviationFromEnactedArea;
    }

    public void setDeviationFromEnactedArea(double deviationFromEnactedArea) {
        this.deviationFromEnactedArea = deviationFromEnactedArea;
    }
    @Transient
    public double getObjectiveFunctionScore() {
        return objectiveFunctionScore;
    }

    public void setObjectiveFunctionScore(double objectiveFunctionScore) {
        this.objectiveFunctionScore = objectiveFunctionScore;
    }
    @Transient
    public double getSimilarityToEnactedScore() {
        return similarityToEnactedScore;
    }

    public void setSimilarityToEnactedScore(double similarityToEnactedScore) {
        this.similarityToEnactedScore = similarityToEnactedScore;
    }
    @Transient
    public ArrayList<District> getMajorityMinorityDistricts() {
        return majorityMinorityDistricts;
    }

    public void setMajorityMinorityDistricts(ArrayList<District> majorityMinorityDistricts) {
        this.majorityMinorityDistricts = majorityMinorityDistricts;
    }
    @Transient
    public int getNumberOfMajorityMinorityDistricts() {
        return numberOfMajorityMinorityDistricts;
    }

    public void setNumberOfMajorityMinorityDistricts(int numberOfMajorityMinorityDistricts) {
        this.numberOfMajorityMinorityDistricts = numberOfMajorityMinorityDistricts;
    }
    @Transient
    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }
    @Transient
    public int getMajorityMinorityDistrictsNumber() {
        return majorityMinorityDistrictsNumber;
    }

    public void setMajorityMinorityDistrictsNumber(int majorityMinorityDistrictsNumber) {
        this.majorityMinorityDistrictsNumber = majorityMinorityDistrictsNumber;
    }
    @Transient
    public int getPopulationEqualityDifference() {
        return populationEqualityDifference;
    }

    public void setPopulationEqualityDifference(int populationEqualityDifference) {
        this.populationEqualityDifference = populationEqualityDifference;
    }
    @Transient
    public double getSplitCountyScore() {
        return splitCountyScore;
    }

    public void setSplitCountyScore(double splitCountyScore) {
        this.splitCountyScore = splitCountyScore;
    }
    @Transient
    public Long getTempPopulationByType() {
        return tempPopulationByType;
    }

    public void setTempPopulationByType(Long tempPopulationByType) {
        this.tempPopulationByType = tempPopulationByType;
    }
    @Transient
    public HashMap<County, Integer> getSplitCountyDetails() {
        return splitCountyDetails;
    }

    public void setSplitCountyDetails(HashMap<County, Integer> splitCountyDetails) {
        this.splitCountyDetails = splitCountyDetails;
    }

    @Transient
    public JSONObject getPrecinctBoundaries() {
        return precinctBoundaries;
    }

    public void setPrecinctBoundaries(JSONObject precinctBoundaries) {
        this.precinctBoundaries = precinctBoundaries;
    }

    @Transient
    public double getPopulationPercentDifference() {
        return populationPercentDifference;
    }

    @Transient
    public double getGraphCompactness() {
        return graphCompactness;
    }

    public void setGraphCompactness(double graphCompactness) {
        this.graphCompactness = graphCompactness;
    }

    public void setPopulationPercentDifference(double populationPercentDifference) {
        this.populationPercentDifference = populationPercentDifference;
    }

    public ArrayList<Long> retrieveTotalPopulationArray(){
        ArrayList<Long> tempPopulationArray = new ArrayList<Long>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            tempPopulationArray.add(this.getDistricts().get(i).getTotalPopulation());
        }
        return tempPopulationArray;
    }

    public ArrayList<Long> retrievePopulationArrayByType(Enum<RaceType> raceType){
        ArrayList<Long> tempPopulationArray = new ArrayList<Long>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            if(raceType == RaceType.AFRICAN_AMERICAN){
                tempPopulationArray.add(this.getDistricts().get(i).getAfricanAmericanPopulation());
            }
            else if(raceType == RaceType.ASIAN){
                tempPopulationArray.add(this.getDistricts().get(i).getAsianPopulation());
            }
            else if(raceType == RaceType.HISPANIC){
                tempPopulationArray.add(this.getDistricts().get(i).getHispanicPopulation());
            }
        }
        return tempPopulationArray;
    }

    public Long getTotalMinorityPopulationByType(RaceType raceType){
        Long totalPopulation = 0l;
        if (raceType == raceType.AFRICAN_AMERICAN){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getAfricanAmericanPopulation();
            }
        }
        else if (raceType == raceType.ASIAN){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getAsianPopulation();
            }
        }
        else if (raceType == raceType.HISPANIC){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getHispanicPopulation();
            }
        }
        return totalPopulation;
    }

    public int calculateNumberOfMajorityMinorityDistricts(RaceType raceType, double threshold){
        int numberOfMajorMinorDistricts = 0;
            for (int i = 0; i < this.getDistricts().size(); i++){
                if (raceType == raceType.AFRICAN_AMERICAN){
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = this.getDistricts().get(i).getAfricanAmericanPopulation()/totalMinorityPopulation;
                    if (currentThreshold >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
                else if (raceType == raceType.ASIAN){
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = this.getDistricts().get(i).getAsianPopulation()/totalMinorityPopulation;
                    if (currentThreshold >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
                else if (raceType == raceType.HISPANIC){
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = this.getDistricts().get(i).getHispanicPopulation()/totalMinorityPopulation;
                    if (currentThreshold >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
            }
        return numberOfMajorMinorDistricts;
    }

    public boolean calculatePopulationConstraint(PopulationType popType, double threshold){
        //iterate through all the districts to find the most populous and least populous districts
        Long mostPopulousPopulation = 0l;
        Long leastPopulousPopulation = 0l;
        for (int i = 0; i < this.getDistricts().size(); i++){
            if (popType == PopulationType.TOTAL){
                if (this.getDistricts().get(i).getTotalPopulation() > mostPopulousPopulation){
                    mostPopulousPopulation = this.getDistricts().get(i).getTotalPopulation();
                }
                if (this.getDistricts().get(i).getTotalPopulation() < leastPopulousPopulation){
                    leastPopulousPopulation = this.getDistricts().get(i).getTotalPopulation();
                }
            }
            else if (popType == PopulationType.VAP){
                if (this.getDistricts().get(i).getVotingAgePopulation() > mostPopulousPopulation){
                    mostPopulousPopulation = this.getDistricts().get(i).getVotingAgePopulation();
                }
                if (this.getDistricts().get(i).getVotingAgePopulation() < leastPopulousPopulation){
                    leastPopulousPopulation = this.getDistricts().get(i).getVotingAgePopulation();
                }
            }
        }
        //the percent difference in this case is 100 x abs(A-B)/((A+B)/2))
        double percentDifference = 100 * Math.abs((mostPopulousPopulation - leastPopulousPopulation)/((mostPopulousPopulation + leastPopulousPopulation)/2));
        //Here we store the percent difference to use in the objective function later
        this.setPopulationPercentDifference(percentDifference);
        if (percentDifference < threshold){
            return true;
        }
        return false;
    }

    public boolean calculateIncumbentDistricts(ArrayList<String> protectedIncumbents){
        //Iterate through all the district's precincts to see if there are two protected incumbents in the same district
        for (int i = 0; i < this.getDistricts().size(); i++){
            if (this.getDistricts().get(i).hasMultipleIncumbents(protectedIncumbents) == true){
                return true;
            }
        }
        return false;
    }

    public double calculateGraphCompactness(){
        ArrayList<Double> compactnessArray = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            ArrayList<Precinct> edgeNodes = this.getDistricts().get(i).calculateEdgeNodes();
            double compactness = edgeNodes.size()/this.getDistricts().get(i).getPrecincts().size();
            compactnessArray.add(compactness);
        }
        //now that we have the compactness, we add all of them together then divide by the array size
        double totalCompactness = 0;
        for (int i = 0; i < compactnessArray.size(); i++){
            totalCompactness += compactnessArray.get(i);
        }
        double graphCompactness = totalCompactness/compactnessArray.size();
        //store the graph compactness measure for easier calculation for the objective function
        this.setGraphCompactness(graphCompactness);
        return graphCompactness;
    }

    @Transient
    public ArrayList<JSONObject> getDistrictBoundaries() {
        return districtBoundaries;
    }

    public void setDistrictBoundaries(ArrayList<JSONObject> districtBoundaries) {
        this.districtBoundaries = districtBoundaries;
    }

    public void setDistrictBoundaryJSON(){
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            jsonObjects.add(this.getDistricts().get(i).getBorderGeometryJson());
        }
        this.setDistrictBoundaries(jsonObjects);
    }

    public void setPrecinctBoundaryJSON(){
        ArrayList<ArrayList<JSONObject>> jsonObjects = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            jsonObjects.add(this.getDistricts().get(i).getPrecinctBoundariesJson());
        }
        ArrayList<JSONObject> innerFeatures = new ArrayList<>();
        for (int i = 0; i < jsonObjects.size(); i++){
            for (int j = 0; j < jsonObjects.get(i).size(); j++){
                innerFeatures.add(jsonObjects.get(i).get(j));
            }
        }
        JSONObject outerProperties = new JSONObject();
        outerProperties.put("type", "FeatureCollection");
        outerProperties.put("features", innerFeatures);
        this.setPrecinctBoundaries(outerProperties);
    }
}
