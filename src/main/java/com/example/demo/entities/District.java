/*package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District {

    private String districtID;
    private int districtNumber;
    private List<precint> precincts;
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

    public District(String districtID, int districtNumber, List<precint> precincts, double compactness, double deviationEnacted, double politicalFairness, int numberOfEdgeNodes, boolean isHigherThanPopThreshold, ArrayList<ArrayList<Double>> borderGeometry, Long population) {
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


}*/
