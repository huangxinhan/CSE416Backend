package com.example.demo.entities;

import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;

public class Plot {
    private PopulationType populationType;
    private RaceType minorityType;
    private ArrayList<ArrayList<Double>> populationPercentages;
    private ArrayList<Double> enactedDistrictingData;
    private ArrayList<Double> currentDistrictingData;
    private ArrayList<ArrayList<Double>> boxAndWhiskerPercentages;

    public Plot(){

    }

    public Plot(PopulationType populationType, RaceType minorityType, ArrayList<ArrayList<Double>> populationPercentages, ArrayList<Double> enactedDistrictingData, ArrayList<Double> currentDistrictingData) {
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

    public ArrayList<ArrayList<Double>> getPopulationPercentages() {
        return populationPercentages;
    }

    public void setPopulationPercentages(ArrayList<ArrayList<Double>> populationPercentages) {
        this.populationPercentages = populationPercentages;
    }

    public ArrayList<Double> getEnactedDistrictingData() {
        return enactedDistrictingData;
    }

    public void setEnactedDistrictingData(ArrayList<Double> enactedDistrictingData) {
        this.enactedDistrictingData = enactedDistrictingData;
    }

    public ArrayList<Double> getCurrentDistrictingData() {
        return currentDistrictingData;
    }

    public void setCurrentDistrictingData(ArrayList<Double> currentDistrictingData) {
        this.currentDistrictingData = currentDistrictingData;
    }

    public ArrayList<ArrayList<Double>> getBoxAndWhiskerPercentages() {
        return boxAndWhiskerPercentages;
    }

    public void setBoxAndWhiskerPercentages(ArrayList<ArrayList<Double>> boxAndWhiskerPercentages) {
        this.boxAndWhiskerPercentages = boxAndWhiskerPercentages;
    }
}
