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
    private Long totalPopulation;
    private Long AfricanAmericanPopulation;
    private Long AsianPopulation;
    private Long HispanicPopulation;

    private County countyID;

    private List<Precinct> neighbours;

    private double compactness;
    @ElementCollection
    private ArrayList< ArrayList<Double> > coordinates;

    private String IncumbentName;

    public Precinct() {
    }

    public Precinct(String precinctID) {
        this.precinctID = precinctID;
    }

    public Precinct(String precinctID, List<Precinct> neighbours) {
        this.precinctID = precinctID;
        this.neighbours = neighbours;
    }

//    public Precinct(String precinctID, Long totalPopulation, Long AfricanAmericanPopulation, Long AsianPopulation, Long HispanicPopulation, List<S> neighbours, ArrayList<ArrayList<Double>> coordinates) {
//        this.precinctID = precinctID;
//        this.totalPopulation = totalPopulation;
//        this.AfricanAmericanPopulation = AfricanAmericanPopulation;
//        this.AsianPopulation = AsianPopulation;
//        this.HispanicPopulation = HispanicPopulation;
//        this.neighbours = neighbours;
//        this.coordinates = coordinates;
//    }
//
//    public Precinct(String precinctID, Long totalPopulation, Long AfricanAmericanPopulation, Long AsianPopulation, Long HispanicPopulation, County countyID, ArrayList<String> neighbours, ArrayList<ArrayList<Double>> coordinates) {
//        this.precinctID = precinctID;
//        this.totalPopulation = totalPopulation;
//        this.AfricanAmericanPopulation = AfricanAmericanPopulation;
//        this.AsianPopulation = AsianPopulation;
//        this.HispanicPopulation = HispanicPopulation;
//        this.countyID = countyID;
//        this.neighbours = neighbours;
//        this.coordinates = coordinates;
//    }

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

    @Transient
    public String getIncumbentName() {
        return IncumbentName;
    }

    public void setIncumbentName(String incumbentName) {
        IncumbentName = incumbentName;
    }

    public void setCountyID(County countyID) {
        this.countyID = countyID;
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

//    public District getDistrictID() {
//        return districtID;
//    }
//
//    public void setDistrictID(District districtID) {
//        this.districtID = districtID;
//    }

    @ManyToMany()
    public List<Precinct> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Precinct> neighbours) {
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
