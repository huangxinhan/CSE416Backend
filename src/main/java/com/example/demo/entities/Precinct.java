package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Precinct implements Serializable{

    //private District districtID;
    private String precinctID;
    private Long population;


    private County countyID;
    @ElementCollection
    private ArrayList<String> neighbours;

    private double compactness;
    @ElementCollection
    private ArrayList< ArrayList<Double> > coordinates;



    public Precinct() {
    }

    public Precinct(String precinctID, Long population, ArrayList<String> neighbours, ArrayList<ArrayList<Double>> coordinates) {
        this.precinctID = precinctID;
        this.population = population;
        this.neighbours = neighbours;
        this.coordinates = coordinates;
    }

    public Precinct(String precinctID, Long population, County countyID, ArrayList<String> neighbours, ArrayList<ArrayList<Double>> coordinates) {
        this.precinctID = precinctID;
        this.population = population;
        this.countyID = countyID;
        this.neighbours = neighbours;
        this.coordinates = coordinates;
    }

    //    @SequenceGenerator(
//            name ="precinct_sequence",
//            sequenceName = "precinct_sequence",
//            allocationSize = 1
//
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "precinct_sequence"
//    )


    @Id
    public String getprecinctID() {
        return precinctID;
    }

    public void setprecinctID(String precinctID) {
        this.precinctID = precinctID;
    }

    @ManyToOne
    public County getCountyID() {
        return countyID;
    }

    public void setCountyID(County countyID) {
        this.countyID = countyID;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

//    public District getDistrictID() {
//        return districtID;
//    }
//
//    public void setDistrictID(District districtID) {
//        this.districtID = districtID;
//    }


    public ArrayList<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<String> neighbours) {
        this.neighbours = neighbours;
    }

    @Transient
    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }


    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

}
