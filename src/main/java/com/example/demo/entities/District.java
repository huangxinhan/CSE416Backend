package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District {

    private String districtID;
    private int districtNumber;
    private List<Precinct> precincts;
    @Transient
    private double compactness;
    @Transient
    private double deviationEnacted;
    @Transient
    private double politicalFairness;
    @Transient
    private int numberOfEdgeNodes;
    @Transient
    private boolean isHigherThanPopThreshold;
    private ArrayList< ArrayList<Double> > borderGeometry;
    //incumbents List<incumbents>
    //involvedCounties List<County>
    private Long population;

    public District(){

    }

    public District(String districtID, int districtNumber, List<Precinct> precincts, double compactness, double deviationEnacted, double politicalFairness, int numberOfEdgeNodes, boolean isHigherThanPopThreshold, ArrayList<ArrayList<Double>> borderGeometry, Long population) {
        this.districtID = districtID;
        this.districtNumber = districtNumber;
        this.precincts = precincts;
        this.compactness = compactness;
        this.deviationEnacted = deviationEnacted;
        this.politicalFairness = politicalFairness;
        this.numberOfEdgeNodes = numberOfEdgeNodes;
        this.isHigherThanPopThreshold = isHigherThanPopThreshold;
        this.borderGeometry = borderGeometry;
        this.population = population;
    }

    @Id
    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(int districtNumber) {
        this.districtNumber = districtNumber;
    }

    @OneToMany
    public List<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(List<Precinct> precincts) {
        this.precincts = precincts;
    }

    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }

    public double getDeviationEnacted() {
        return deviationEnacted;
    }

    public void setDeviationEnacted(double deviationEnacted) {
        this.deviationEnacted = deviationEnacted;
    }

    public double getPoliticalFairness() {
        return politicalFairness;
    }

    public void setPoliticalFairness(double politicalFairness) {
        this.politicalFairness = politicalFairness;
    }

    public int getNumberOfEdgeNodes() {
        return numberOfEdgeNodes;
    }

    public void setNumberOfEdgeNodes(int numberOfEdgeNodes) {
        this.numberOfEdgeNodes = numberOfEdgeNodes;
    }

    public boolean isHigherThanPopThreshold() {
        return isHigherThanPopThreshold;
    }

    public void setHigherThanPopThreshold(boolean higherThanPopThreshold) {
        isHigherThanPopThreshold = higherThanPopThreshold;
    }

    public ArrayList<ArrayList<Double>> getBorderGeometry() {
        return borderGeometry;
    }

    public void setBorderGeometry(ArrayList<ArrayList<Double>> borderGeometry) {
        this.borderGeometry = borderGeometry;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
}
