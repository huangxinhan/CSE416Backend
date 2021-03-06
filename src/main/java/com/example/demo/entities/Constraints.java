package com.example.demo.entities;

import com.example.demo.entities.enums.CompactnessType;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;
import java.util.HashMap;

public class Constraints {
    private CompactnessType compactnessType;
    private PopulationType populationType;
    private double compactnessValue;
    private RaceType minorityType;
    private double majorMinorThres;
    private int numberOfMajorityMinorityDistricts;
    private double populationEqualityThres;
    private Long populationValue;
    private HashMap<String, Boolean> incumbentValue;
    private ArrayList<String> protectedIncumbents;

    public Constraints(){

    }
    public Constraints(CompactnessType compactnessType, PopulationType populationType, double compactnessValue, RaceType minorityType, double majorMinorThres, int numberOfMajorityMinorityDistricts, double populationEqualityThres, Long populationValue, HashMap<String, Boolean> incumbentValue) {
        this.compactnessType = compactnessType;
        this.populationType = populationType;
        this.compactnessValue = compactnessValue;
        this.minorityType = minorityType;
        this.majorMinorThres = majorMinorThres;
        this.numberOfMajorityMinorityDistricts = numberOfMajorityMinorityDistricts;
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
        this.majorMinorThres = majorMinorThres;
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

    public HashMap<String, Boolean> getIncumbentValue() {
        return incumbentValue;
    }

    public void setIncumbentValue(HashMap<String, Boolean> incumbentValue) {
        this.incumbentValue = incumbentValue;
    }

    public int getNumberOfMajorityMinorityDistricts() {
        return numberOfMajorityMinorityDistricts;
    }

    public void setNumberOfMajorityMinorityDistricts(int numberOfMajorityMinorityDistricts) {
        this.numberOfMajorityMinorityDistricts = numberOfMajorityMinorityDistricts;
    }

    public ArrayList<String> getProtectedIncumbents() {
        return protectedIncumbents;
    }

    public void setProtectedIncumbents(ArrayList<String> protectedIncumbents) {
        this.protectedIncumbents = protectedIncumbents;
    }
}
