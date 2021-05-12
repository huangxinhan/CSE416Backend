package com.example.demo.entities;
import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Entity
public class JobSummary implements Serializable{
    private String jobSummaryID;
    private String stateName;
    private String numOfDistrictings;
    private String numOfDistricts;
    private String coolingRounds;
    private String totalRounds;
    public JobSummary(){

    }

    public JobSummary(String jobSummaryID,String stateName, String numOfDistrictings, String numOfDistricts, String coolingRounds, String totalRounds) {
        this.jobSummaryID = jobSummaryID;
        this.stateName = stateName;
        this.numOfDistrictings = numOfDistrictings;
        this.numOfDistricts = numOfDistricts;
        this.coolingRounds = coolingRounds;
        this.totalRounds = totalRounds;
    }

    @Id
    public String getJobSummaryID(){
        return jobSummaryID;
    }

    public void setJobSummaryID(String jobSummaryID){
        this.jobSummaryID = jobSummaryID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getNumOfDistrictings() {
        return numOfDistrictings;
    }

    public void setNumOfDistrictings(String numOfDistrictings) {
        this.numOfDistrictings = numOfDistrictings;
    }

    public String getNumOfDistricts() {
        return numOfDistricts;
    }

    public void setNumOfDistricts(String numOfDistricts) {
        this.numOfDistricts = numOfDistricts;
    }

    public String getCoolingRounds() {
        return coolingRounds;
    }

    public void setCoolingRounds(String coolingRounds) {
        this.coolingRounds = coolingRounds;
    }

    public String getTotalRounds() {
        return totalRounds;
    }

    public void setTotalRounds(String totalRounds) {
        this.totalRounds = totalRounds;
    }
}
