package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District {

    private String districtID;
    private int districtNumber;
    @OneToMany
    private List<String> precincts;
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
    private Long population;

    public District(){

    }

    public District(String districtID, int districtNumber, List<String> precincts, ArrayList<ArrayList<Double>> borderGeometry, Long population) {
        this.districtID = districtID;
        this.districtNumber = districtNumber;
        this.precincts = precincts;
        this.borderGeometry = borderGeometry;

        for (int i = 0; i < this.precincts.size(); i++){

        }
        this.population = population;
    }


}
