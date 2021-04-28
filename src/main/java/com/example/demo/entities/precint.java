package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class precint  {
    private String districtID;
    private String precintID;
    private Long population;


    private String countyID;
    @ManyToMany
    private ArrayList<precint> neighbours;
    @Transient
    private double compactness;
    @ElementCollection
    private ArrayList< ArrayList<Double> > coordinates;



    public precint() {
    }

    public precint(String districtID, String precintID, Long population, String countyID, ArrayList<ArrayList<Double>> coordinates) {
        this.districtID = districtID;
        this.precintID = precintID;
        this.population = population;
        this.countyID = countyID;
        this.coordinates = coordinates;
    }

    public precint(String districtID, String precintID, String countyID, ArrayList<ArrayList<Double>> coordinates) {
        this.districtID = districtID;
        this.precintID = precintID;
        this.countyID = countyID;
        this.coordinates = coordinates;
    }



    @Id
//    @SequenceGenerator(
//            name ="precint_sequence",
//            sequenceName = "precint_sequence",
//            allocationSize = 1
//
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "precint_sequence"
//    )

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

    public String getPrecintID() {
        return precintID;
    }

    public void setPrecintID(String precintID) {
        this.precintID = precintID;
    }

    public String getCountyID() {
        return countyID;
    }

    public void setCountyID(String countyID) {
        this.countyID = countyID;
    }


    public ArrayList<precint> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<precint> neighbours) {
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
