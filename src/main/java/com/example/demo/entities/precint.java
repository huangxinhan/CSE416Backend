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


    @ElementCollection
    private ArrayList< ArrayList<Double> > coordinates;
//    private String geoType;
//    private ArrayList<ArrayList<Float>> geometry;



    public precint(String districtID, String precintID, Long population, String countyID, ArrayList<ArrayList<Double>> coordinates) {
        this.districtID = districtID;
        this.precintID = precintID;
        this.population = population;
        this.countyID = countyID;
        this.coordinates = coordinates;
    }

    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public precint() {
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

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "precint{" +
                "districtID='" + districtID + '\'' +
                ", precintID='" + precintID + '\'' +
                ", population=" + population +
                ", countyID='" + countyID + '\'' +
                '}';
    }
}
