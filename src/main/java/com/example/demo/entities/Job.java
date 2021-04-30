package com.example.demo.entities;
import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.RaceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Entity
public class Job implements Serializable{
    private String jobID;
    private Constraints constraints;
    private List<Districting> districtings;
    private List<Districting> tempConstrainedDistrictings;
    private ConstrainedDistrictings constrainedDistrictings;
    private HashMap<Measures, Double> weights;
    private ArrayList<Districting> topDistrictingsByOFScore;
    private ArrayList<Districting> topDistrictingsByEnacted;
    private ArrayList<Districting> topDistrictingsByHighScoreMajMinDistricts;
    private ArrayList<Districting> topDistrictingsBySigmaAvg;
    private ArrayList<Districting> topDistrictingsBySigmaEnacted;
    private ArrayList<Districting> topDistrictingsByCompactness;
    private ArrayList<Districting> topDistrictingsByAreaPairDeviation;
    private ArrayList<Districting> topDistrictingsBySimilarity;
    private HashMap<String, String> mgggParams;
    private String stateName;
    private Enum<RaceType> raceType;

    public Job(){

    }

    public Job(String JobID, List<Districting> districtings, HashMap<String, String> mgggParams, String stateName){
        this.jobID = jobID;
        this.districtings = districtings;
        this.mgggParams = mgggParams;
        this.stateName = stateName;
    }

    @Id
    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    @Transient
    public Constraints getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
    @OneToMany(cascade = CascadeType.ALL)
    public List<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(List<Districting> districtings) {
        this.districtings = districtings;
    }
    @Transient
    public List<Districting> getTempConstrainedDistrictings() {
        return tempConstrainedDistrictings;
    }

    public void setTempConstrainedDistrictings(ArrayList<Districting> tempConstrainedDistrictings) {
        this.tempConstrainedDistrictings = tempConstrainedDistrictings;
    }
    @Transient
    public ConstrainedDistrictings getConstrainedDistrictings() {
        return constrainedDistrictings;
    }

    public void setConstrainedDistrictings(ConstrainedDistrictings constrainedDistrictings) {
        this.constrainedDistrictings = constrainedDistrictings;
    }
    @Transient
    public HashMap<Measures, Double> getWeights() {
        return weights;
    }

    public void setWeights(HashMap<Measures, Double> weights) {
        this.weights = weights;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByOFScore() {
        return topDistrictingsByOFScore;
    }

    public void setTopDistrictingsByOFScore(ArrayList<Districting> topDistrictingsByOFScore) {
        this.topDistrictingsByOFScore = topDistrictingsByOFScore;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByEnacted() {
        return topDistrictingsByEnacted;
    }

    public void setTopDistrictingsByEnacted(ArrayList<Districting> topDistrictingsByEnacted) {
        this.topDistrictingsByEnacted = topDistrictingsByEnacted;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByHighScoreMajMinDistricts() {
        return topDistrictingsByHighScoreMajMinDistricts;
    }

    public void setTopDistrictingsByHighScoreMajMinDistricts(ArrayList<Districting> topDistrictingsByHighScoreMajMinDistricts) {
        this.topDistrictingsByHighScoreMajMinDistricts = topDistrictingsByHighScoreMajMinDistricts;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySigmaAvg() {
        return topDistrictingsBySigmaAvg;
    }

    public void setTopDistrictingsBySigmaAvg(ArrayList<Districting> topDistrictingsBySigmaAvg) {
        this.topDistrictingsBySigmaAvg = topDistrictingsBySigmaAvg;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySigmaEnacted() {
        return topDistrictingsBySigmaEnacted;
    }

    public void setTopDistrictingsBySigmaEnacted(ArrayList<Districting> topDistrictingsBySigmaEnacted) {
        this.topDistrictingsBySigmaEnacted = topDistrictingsBySigmaEnacted;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByCompactness() {
        return topDistrictingsByCompactness;
    }

    public void setTopDistrictingsByCompactness(ArrayList<Districting> topDistrictingsByCompactness) {
        this.topDistrictingsByCompactness = topDistrictingsByCompactness;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByAreaPairDeviation() {
        return topDistrictingsByAreaPairDeviation;
    }

    public void setTopDistrictingsByAreaPairDeviation(ArrayList<Districting> topDistrictingsByAreaPairDeviation) {
        this.topDistrictingsByAreaPairDeviation = topDistrictingsByAreaPairDeviation;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySimilarity() {
        return topDistrictingsBySimilarity;
    }

    public void setTopDistrictingsBySimilarity(ArrayList<Districting> topDistrictingsBySimilarity) {
        this.topDistrictingsBySimilarity = topDistrictingsBySimilarity;
    }

    public HashMap<String, String> getMgggParams() {
        return mgggParams;
    }

    public void setMgggParams(HashMap<String, String> mgggParams) {
        this.mgggParams = mgggParams;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Transient
    public Enum<RaceType> getRaceType() {
        return raceType;
    }

    public void setRaceType(Enum<RaceType> raceType) {
        this.raceType = raceType;
    }

    public void calculateTopDistrictingsByOF(HashMap<Measures, Double> measures, Enum<RaceType> raceType) {

    }

    public void sortDistrictingByOF(){
        this.getTopDistrictingsByOFScore().sort(Comparator.comparing(Districting::getObjectiveFunctionScore));
    }

    public void sortDistrictingByEnacted(){
        this.getTopDistrictingsByEnacted().sort(Comparator.comparing(Districting::getDeviationFromEnactedPop));
    }

    public ArrayList<Districting> retrieveTopDistrictingsByOF(){
        ArrayList<Districting> topTenDistrictings = new ArrayList<Districting>();
        this.sortDistrictingByOF();
        for (int i = 0; i < 10; i++){
            topTenDistrictings.add(this.getTopDistrictingsByOFScore().get(i));
        }
        return topTenDistrictings;
    }




}
