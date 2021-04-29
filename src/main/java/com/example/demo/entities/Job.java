package com.example.demo.entities;
import com.example.demo.entities.enums.Measures;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Districting> topDistrictingsByOFScore;
    private List<Districting> topDistrictingsByEnacted;
    private List<Districting> topDistrictingsByHighScoreMajMinDistricts;
    private List<Districting> topDistrictingsBySigmaAvg;
    private List<Districting> topDistrictingsBySigmaEnacted;
    private List<Districting> topDistrictingsByCompactness;
    private List<Districting> topDistrictingsByAreaPairDeviation;
    private List<Districting> topDistrictingsBySimilarity;
    //private HashMap<Param, Long> mgggParams;
    private String stateName;

    public Job(){

    }

    public Job(String JobID, List<Districting> districtings, /*HashMap<Param, Long> mgggParams,*/ String stateName){
        this.jobID = jobID;
        this.districtings = districtings;
        //this.mgggParams = mgggParams;
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

    public void setTempConstrainedDistrictings(List<Districting> tempConstrainedDistrictings) {
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
    public List<Districting> getTopDistrictingsByOFScore() {
        return topDistrictingsByOFScore;
    }

    public void setTopDistrictingsByOFScore(List<Districting> topDistrictingsByOFScore) {
        this.topDistrictingsByOFScore = topDistrictingsByOFScore;
    }
    @Transient
    public List<Districting> getTopDistrictingsByEnacted() {
        return topDistrictingsByEnacted;
    }

    public void setTopDistrictingsByEnacted(List<Districting> topDistrictingsByEnacted) {
        this.topDistrictingsByEnacted = topDistrictingsByEnacted;
    }
    @Transient
    public List<Districting> getTopDistrictingsByHighScoreMajMinDistricts() {
        return topDistrictingsByHighScoreMajMinDistricts;
    }

    public void setTopDistrictingsByHighScoreMajMinDistricts(List<Districting> topDistrictingsByHighScoreMajMinDistricts) {
        this.topDistrictingsByHighScoreMajMinDistricts = topDistrictingsByHighScoreMajMinDistricts;
    }
    @Transient
    public List<Districting> getTopDistrictingsBySigmaAvg() {
        return topDistrictingsBySigmaAvg;
    }

    public void setTopDistrictingsBySigmaAvg(List<Districting> topDistrictingsBySigmaAvg) {
        this.topDistrictingsBySigmaAvg = topDistrictingsBySigmaAvg;
    }
    @Transient
    public List<Districting> getTopDistrictingsBySigmaEnacted() {
        return topDistrictingsBySigmaEnacted;
    }

    public void setTopDistrictingsBySigmaEnacted(List<Districting> topDistrictingsBySigmaEnacted) {
        this.topDistrictingsBySigmaEnacted = topDistrictingsBySigmaEnacted;
    }
    @Transient
    public List<Districting> getTopDistrictingsByCompactness() {
        return topDistrictingsByCompactness;
    }

    public void setTopDistrictingsByCompactness(List<Districting> topDistrictingsByCompactness) {
        this.topDistrictingsByCompactness = topDistrictingsByCompactness;
    }
    @Transient
    public List<Districting> getTopDistrictingsByAreaPairDeviation() {
        return topDistrictingsByAreaPairDeviation;
    }

    public void setTopDistrictingsByAreaPairDeviation(List<Districting> topDistrictingsByAreaPairDeviation) {
        this.topDistrictingsByAreaPairDeviation = topDistrictingsByAreaPairDeviation;
    }
    @Transient
    public List<Districting> getTopDistrictingsBySimilarity() {
        return topDistrictingsBySimilarity;
    }

    public void setTopDistrictingsBySimilarity(List<Districting> topDistrictingsBySimilarity) {
        this.topDistrictingsBySimilarity = topDistrictingsBySimilarity;
    }

//    public HashMap<Param, Long> getMgggParams() {
//        return mgggParams;
//    }
//
//    public void setMgggParams(HashMap<Param, Long> mgggParams) {
//        this.mgggParams = mgggParams;
//    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }



}
