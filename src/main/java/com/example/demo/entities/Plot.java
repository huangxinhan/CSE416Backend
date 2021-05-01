package com.example.demo.entities;

import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;

public class Plot {
    private PopulationType populationType;
    private RaceType minorityType;
    private ArrayList<ArrayList<Long>> populationPercentages;
    private ArrayList<Long> enactedDistrictingData;
    private ArrayList<Long> currentDistrictingData;

    public Plot(){

    }

    public Plot(PopulationType populationType, RaceType minorityType, ArrayList<ArrayList<Long>> populationPercentages, ArrayList<Long> enactedDistrictingData, ArrayList<Long> currentDistrictingData) {
        this.populationType = populationType;
        this.minorityType = minorityType;
        this.populationPercentages = populationPercentages;
        this.enactedDistrictingData = enactedDistrictingData;
        this.currentDistrictingData = currentDistrictingData;
    }

    public PopulationType getPopulationType() {
        return populationType;
    }

    public void setPopulationType(PopulationType populationType) {
        this.populationType = populationType;
    }

    public RaceType getMinorityType() {
        return minorityType;
    }

    public void setMinorityType(RaceType minorityType) {
        this.minorityType = minorityType;
    }

    public ArrayList<ArrayList<Long>> getPopulationPercentages() {
        return populationPercentages;
    }

    public void setPopulationPercentages(ArrayList<ArrayList<Long>> populationPercentages) {
        this.populationPercentages = populationPercentages;
    }

    public ArrayList<Long> getEnactedDistrictingData() {
        return enactedDistrictingData;
    }

    public void setEnactedDistrictingData(ArrayList<Long> enactedDistrictingData) {
        this.enactedDistrictingData = enactedDistrictingData;
    }

    public ArrayList<Long> getCurrentDistrictingData() {
        return currentDistrictingData;
    }

    public void setCurrentDistrictingData(ArrayList<Long> currentDistrictingData) {
        this.currentDistrictingData = currentDistrictingData;
    }
}
