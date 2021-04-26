package com.example.demo.entities;

import javax.persistence.*;

@Entity

public class precint {
    private String districtID;
    private String precintID;
    private Long population;
    private String countyID;



    public precint(String districtID, String precintID, Long population, String countyID) {
        this.districtID = districtID;
        this.precintID = precintID;
        this.population = population;
        this.countyID = countyID;
    }



    public precint(String districtID, String precintID, String countyID) {
        this.districtID = districtID;
        this.precintID = precintID;
        this.countyID = countyID;
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
