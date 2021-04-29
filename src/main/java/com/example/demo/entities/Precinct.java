package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Precinct implements Serializable{
    private String districtID;
    private String precinctID;
    private Long population;


    private String countyID;
    @JsonIgnore
    @ManyToMany
    private ArrayList<String> neighbours;
    @Transient
    private double compactness;
    @ElementCollection
    private ArrayList< ArrayList<Double> > coordinates;



    public Precinct() {
    }

    public Precinct(String districtID, String precinctID, Long population, String countyID, ArrayList<ArrayList<Double>> coordinates) {
        this.districtID = districtID;
        this.precinctID = precinctID;
        this.population = population;
        this.countyID = countyID;
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

    public String getCountyID() {
        return countyID;
    }

    public void setCountyID(String countyID) {
        this.countyID = countyID;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public String getDistrictID() {
        return districtID;
    }

    public void setDistrictID(String districtID) {
        this.districtID = districtID;
    }
    public ArrayList<String> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<String> neighbours) {
        this.neighbours = neighbours;
    }


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
