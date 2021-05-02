package com.example.demo.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District implements Serializable{

    private String districtID;
    private int districtNumber;

    private List<Precinct> precincts = new ArrayList<Precinct>();
    private double compactness;
    private double deviationEnacted;
    private double politicalFairness;
    private int numberOfEdgeNodes;
    private boolean isHigherThanPopThreshold;
    private Geometry borderGeometry;
    private Long totalPopulation;
    private Long votingAgePopulation;
    private Long AfricanAmericanPopulation;
    private Long AsianPopulation;
    private Long HispanicPopulation;
    private Districting districtingID;

    private double objectiveFunctionScore;

    private List<Precinct> edgeNodes;

    private ArrayList<County> involvedCounties;

    private double OFScore;

    public District(){

    }

    public District(String districtID) {
        this.districtID = districtID;
    }

    public District(Districting districtingID) {
        this.districtingID = districtingID;
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

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(List<Precinct> precincts) {
        this.precincts = precincts;
    }
    @Transient
    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }
    @Transient
    public double getDeviationEnacted() {
        return deviationEnacted;
    }

    public void setDeviationEnacted(double deviationEnacted) {
        this.deviationEnacted = deviationEnacted;
    }
    @Transient
    public double getPoliticalFairness() {
        return politicalFairness;
    }

    public void setPoliticalFairness(double politicalFairness) {
        this.politicalFairness = politicalFairness;
    }
    @Transient
    public int getNumberOfEdgeNodes() {
        return numberOfEdgeNodes;
    }

    public void setNumberOfEdgeNodes(int numberOfEdgeNodes) {
        this.numberOfEdgeNodes = numberOfEdgeNodes;
    }
    @Transient
    public boolean isHigherThanPopThreshold() {
        return isHigherThanPopThreshold;
    }

    public void setHigherThanPopThreshold(boolean higherThanPopThreshold) {
        isHigherThanPopThreshold = higherThanPopThreshold;
    }
    @Transient
    public Geometry getBorderGeometry() {
        return borderGeometry;
    }

    public void setBorderGeometry(Geometry borderGeometry) {
        this.borderGeometry = borderGeometry;
    }
    @Transient
    public Long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(Long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    @Transient
    public Long getVotingAgePopulation(){return votingAgePopulation;}

    public void setVotingAgePopulation(Long votingAgePopulation){this.votingAgePopulation = votingAgePopulation;}

    @Transient
    public Long getAfricanAmericanPopulation() {
        return AfricanAmericanPopulation;
    }

    public void setAfricanAmericanPopulation(Long africanAmericanPopulation) {
        AfricanAmericanPopulation = africanAmericanPopulation;
    }
    @Transient
    public Long getAsianPopulation() {
        return AsianPopulation;
    }

    public void setAsianPopulation(Long asianPopulation) {
        AsianPopulation = asianPopulation;
    }
    @Transient
    public Long getHispanicPopulation() {
        return HispanicPopulation;
    }

    public void setHispanicPopulation(Long hispanicPopulation) {
        HispanicPopulation = hispanicPopulation;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    //@Fetch(value = FetchMode.SUBSELECT)
    public Districting getDistrictingID() {
        return districtingID;
    }

    public void setDistrictingID(Districting districtingID) {
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

    public void appendPrecinct(Precinct precinct){
        List<Precinct> temp = this.getPrecincts();
        temp.add(precinct);
        this.setPrecincts((temp));
    }

    public ArrayList<Precinct> calculateEdgeNodes(){
        ArrayList<Precinct> edgeNodes = new ArrayList<Precinct>();
        for (int i = 0; i < this.getPrecincts().size(); i++){
            if(this.getPrecincts().get(i).getOnEdge() == true){
                edgeNodes.add(this.getPrecincts().get(i));
            }
        }
        this.setEdgeNodes(edgeNodes);
        return edgeNodes;
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

    public boolean hasMultipleIncumbents(ArrayList<String> protectedIncumbents){
        int counter = 0;
        for (int i = 0; i < this.getPrecincts().size(); i++){
            if (protectedIncumbents.contains(this.getPrecincts().get(i).getIncumbentName())){
                counter++;
            }
            if (counter == 2){
                return true;
            }
        }
        return false;
    }

    //call this first
    public void calculateAllPopulation(){
        Long tempTotalPop = 0l;
        Long tempVotingAgePopulation = 0l;
        Long tempAfricanAmericanPop = 0l;
        Long tempAsianPop = 0l;
        Long tempHispanicPop = 0l;

        for (int i = 0; i < this.getPrecincts().size(); i++){
            tempTotalPop += this.precincts.get(i).getTotalPopulation();
            tempVotingAgePopulation += this.precincts.get(i).getVotingAgePopulation();
            tempAfricanAmericanPop += this.precincts.get(i).getAfricanAmericanPopulation();
            tempAsianPop  += this.precincts.get(i).getAsianPopulation();
            tempHispanicPop += this.precincts.get(i).getHispanicPopulation();
        }

        this.setTotalPopulation(tempTotalPop);
        this.setVotingAgePopulation(tempVotingAgePopulation);
        this.setAfricanAmericanPopulation(tempAfricanAmericanPop);
        this.setAsianPopulation(tempAsianPop);
        this.setHispanicPopulation(tempHispanicPop);
    }

    public void calculateDistrictGeometry(){
        //first we need to convert the coordinates into a geometry object
        Geometry[] precinctGeometries = new Geometry[this.getEdgeNodes().size()];
        for (int i = 0; i < this.getEdgeNodes().size(); i++){
            precinctGeometries[i] = this.getPrecincts().get(i).getCoordinates();
        }
        GeometryCollection geometryCollection = new GeometryCollection(precinctGeometries, new GeometryFactory());
        Geometry union = geometryCollection.union();
        //then set the geometry of the district as the union
        this.setBorderGeometry(union);
    }
}


