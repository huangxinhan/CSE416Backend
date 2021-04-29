package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class County implements Serializable {

    private String countyID;

    private List<Precinct> precincts;
    //private List<District> districts;

    private int splitCountyNumber;
    @ElementCollection
    private ArrayList<ArrayList<Double>> coordinates;

    @Id
    public String getCountyID() {
        return countyID;
    }

    public void setCountyID(String countyID) {
        this.countyID = countyID;
    }

    @OneToMany(mappedBy = "countyID")
    public List<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(List<Precinct> precincts) {
        this.precincts = precincts;
    }

    @Transient
    public int getSplitCountyNumber() {
        return splitCountyNumber;
    }

    public void setSplitCountyNumber(int splitCountyNumber) {
        this.splitCountyNumber = splitCountyNumber;
    }


    public ArrayList<ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public County() {
    }

    public County(String countyID, List<Precinct> precincts, ArrayList<ArrayList<Double>> coordinates) {
        this.countyID = countyID;
        this.precincts = precincts;
        this.coordinates = coordinates;
    }
}
