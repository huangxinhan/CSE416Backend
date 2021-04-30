package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District implements Serializable{

    private String districtID;
    private int districtNumber;

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
    private Long totalPopulation;
    private Long AfricanAmericanPopulation;
    private Long AsianPopulation;
    private Long HispanicPopulation;

    private String districtingID;

    private double objectiveFunctionScore;

    private List<Precinct> edgeNodes;

    private ArrayList<County> involvedCounties;

    private double OFScore;

    public District(){

    }

    public District(String districtID) {
        this.districtID = districtID;
    }

    public District(String districtID, int districtNumber, List<Precinct> precincts, ArrayList<ArrayList<Double>> borderGeometry, Long population, String districtingID) {
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
            tempTotalPop += this.precincts.get(i).getAsianPopulation();
            tempTotalPop += this.precincts.get(i).getHispanicPopulation();
        }
        this.totalPopulation = tempTotalPop;
        this.AfricanAmericanPopulation = tempAfricanAmericanPop;
        this.AsianPopulation = tempAsianPop;
        this.HispanicPopulation = tempHispanicPop;

        String[] split = this.districtID.split("_");
        String firstSubString = split[0];
        this.districtingID = firstSubString;
    }

    @Id
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

    @ManyToMany(cascade = CascadeType.ALL)
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

    public String getDistrictingID() {
        return districtingID;
    }

    public void setDistrictingID(String districtingID) {
        this.districtingID = districtingID;
    }

    @Transient
    public double getObjectiveFunctionScore() {
        return objectiveFunctionScore;
    }

    public void setObjectiveFunctionScore(double objectiveFunctionScore) {
        this.objectiveFunctionScore = objectiveFunctionScore;
    }

    @Transient
    public List<Precinct> getEdgeNodes() {
        return edgeNodes;
    }

    public void setEdgeNodes(List<Precinct> edgeNodes) {
        this.edgeNodes = edgeNodes;
    }

    @Transient
    public ArrayList<County> getInvolvedCounties() {
        return involvedCounties;
    }

    public void setInvolvedCounties(ArrayList<County> involvedCounties) {
        this.involvedCounties = involvedCounties;
    }

    @Transient
    public double getOFScore() {
        return OFScore;
    }

    public void setOFScore(double OFScore) {
        this.OFScore = OFScore;
    }

    public void calculateEdgeNodes(){
        ArrayList<Precinct> edgeNodes = new ArrayList<Precinct>();
        for (int i = 0; i < this.getPrecincts().size(); i++){
            if(this.getPrecincts().get(i).getOnEdge() == true){
                edgeNodes.add(this.getPrecincts().get(i));
            }
        }
        this.setEdgeNodes(edgeNodes);
    }

    public void calculateInvolvedCounties(){
        //loop through every precinct in the district and get a list of counties from it
        ArrayList<County> counties = new ArrayList<County>();
        for (int i = 0; i < this.getPrecincts().size(); i++){
            if(!counties.contains(this.getPrecincts().get(i).getCountyID())){
                counties.add(this.getPrecincts().get(i).getCountyID());
            }
        }
        this.setInvolvedCounties(counties);
    }
}
