package com.example.demo.entities;

import com.example.demo.entities.enums.CompactnessType;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;

public class Constraints {
    private CompactnessType compactnessType;
    private PopulationType populationType;
    private double compactnessValue;
    private RaceType minorityType;
    private double majorMinorThres;
    private double populationEqualityThres;
    private Long populationValue;
    private ArrayList<Boolean> incumbentValue;

    public Constraints(){

    }
    public Constraints(CompactnessType compactnessType, PopulationType populationType, double compactnessValue, RaceType minorityType, double majorMinorThres, double populationEqualityThres, Long populationValue, ArrayList<Boolean> incumbentValue) {
        this.compactnessType = compactnessType;
        this.populationType = populationType;
        this.compactnessValue = compactnessValue;
        this.minorityType = minorityType;
        this.majorMinorThres = majorMinorThres;
        this.populationEqualityThres = populationEqualityThres;
        this.populationValue = populationValue;
        this.incumbentValue = incumbentValue;
    }

    public CompactnessType getCompactnessType() {
        return compactnessType;
    }

    public void setCompactnessType(CompactnessType compactnessType) {
        this.compactnessType = compactnessType;
    }

    public PopulationType getPopulationType() {
        return populationType;
    }

    public void setPopulationType(PopulationType populationType) {
        this.populationType = populationType;
    }

    public double getCompactnessValue() {
        return compactnessValue;
    }

    public void setCompactnessValue(double compactnessValue) {
        this.compactnessValue = compactnessValue;
    }

    public RaceType getMinorityType() {
        return minorityType;
    }

    public void setMinorityType(RaceType minorityType) {
        this.minorityType = minorityType;
    }

    public double getMajorMinorThres() {
        return majorMinorThres;
    }

    public void setMajorMinorThres(double majorMinorThres) {
        majorMinorThres = majorMinorThres;
    }

    public double getPopulationEqualityThres() {
        return populationEqualityThres;
    }

    public void setPopulationEqualityThres(double populationEqualityThres) {
        this.populationEqualityThres = populationEqualityThres;
    }

    public Long getPopulationValue() {
        return populationValue;
    }

    public void setPopulationValue(Long populationValue) {
        this.populationValue = populationValue;
    }

    public ArrayList<Boolean> getIncumbentValue() {
        return incumbentValue;
    }

    public void setIncumbentValue(ArrayList<Boolean> incumbentValue) {
        this.incumbentValue = incumbentValue;
    }
}
