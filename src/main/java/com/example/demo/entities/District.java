package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District implements Serializable{

    private String districtID;
    private int districtNumber;
    @OneToMany
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
    @ElementCollection
    private ArrayList< ArrayList<Double> > borderGeometry;
    //incumbents List<incumbents>
    //involvedCounties List<County>
    private Long totalPopulation;
    private Long AfricanAmericanPopulation;
    private Long AsianPopulation;
    private Long HispanicPopulation;

    public District(){

    }

    public District(String districtID, int districtNumber, List<Precinct> precincts, ArrayList<ArrayList<Double>> borderGeometry, Long population) {
        this.districtID = districtID;
        this.districtNumber = districtNumber;
        this.precincts = precincts;
        this.borderGeometry = borderGeometry;
        Long tempTotalPop = 0l;
        Long tempAfricanAmericanPop = 0l;
        Long tempAsianPop = 0l;
        Long tempHispanicPop = 0l;
        for (int i = 0; i < this.precincts.size(); i++){
            tempTotalPop += this.precincts.get(i).getTotalPopulation();
            tempTotalPop += this.precincts.get(i).getAfricanAmericanPopulation();
            tempTotalPop += this.precincts.get(i).getAsiantalPopulation();
            tempTotalPop += this.precincts.get(i).getHispanicPopulation();
        }
        this.totalPopulation = tempTotalPop;
        this.AfricanAmericanPopulation = tempAfricanAmericanPop;
        this.AsianPopulation = tempAsianPop;
        this.HispanicPopulation = tempHispanicPop;
    }

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

    public Long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public Long getAfricanAmericanPopulation() {
        return AfricanAmericanPopulation;
    }

    public void setAfricanAmericanPopulation(Long africanAmericanPopulation) {
        AfricanAmericanPopulation = africanAmericanPopulation;
    }

    public Long getAsianPopulation() {
        return AsianPopulation;
    }

    public void setAsianPopulation(Long asianPopulation) {
        AsianPopulation = asianPopulation;
    }

    public Long getHispanicPopulation() {
        return HispanicPopulation;
    }

    public void setHispanicPopulation(Long hispanicPopulation) {
        HispanicPopulation = hispanicPopulation;
    }
}
