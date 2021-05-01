package com.example.demo.entities;

import com.example.demo.entities.enums.RaceType;

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

    @OneToMany(cascade = CascadeType.ALL)
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

                    }
                }
                else if (raceType == raceType.ASIAN){

                }
                else if (raceType == raceType.HISPANIC){

                }

            }
        return numberOfMajorMinorDistricts;
    }
}
