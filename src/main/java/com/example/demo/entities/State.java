package com.example.demo.entities;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.geojson.GeoJsonWriter;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



//////////don't forget to change cascade when dealing with sea_wulf
@Entity
public class State implements Serializable{
    private String stateID;
    private List<Precinct> precincts;
    private List<Job> jobs;
    private Job currentJob;
    private Districting enactedDistricting;
    private Districting averageDistricting;
    private Districting sortedEnactedDistricting;
    private Districting sortedCurrentDistricting;
    private Districting currentDistricting;
    private List<County> counties;
    @ElementCollection
    private Geometry stateBoundary;
    private JSONObject stateBoundaryJson;

    public State(){

    }

    public State(String stateID) {
        this.stateID = stateID;
    }

    public State(String stateID, List<Precinct> precincts, List<Job> jobs, Districting enactedDistricting, List<County> counties, Geometry stateBoundary){
        this.stateID = stateID;
        this.precincts = precincts;
        this.jobs = jobs;
        this.enactedDistricting = enactedDistricting;
        this.counties = counties;
        this.stateBoundary = stateBoundary;
    }
    @Id
    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(List<Precinct> precincts) {
        this.precincts = precincts;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
    @Transient
    public Job getCurrentJob() {
        return currentJob;
    }

    public void setCurrentJob(Job currentJob) {
        this.currentJob = currentJob;
    }

    @OneToOne(fetch = FetchType.EAGER)

    public Districting getEnactedDistricting() {
        return enactedDistricting;
    }

    public void setEnactedDistricting(Districting enactedDistricting) {
        this.enactedDistricting = enactedDistricting;
    }
    @Transient
    public Districting getAverageDistricting() {
        return averageDistricting;
    }

    public void setAverageDistricting(Districting averageDistricting) {
        this.averageDistricting = averageDistricting;
    }
    @Transient
    public Districting getSortedEnactedDistricting() {
        return sortedEnactedDistricting;
    }

    public void setSortedEnactedDistricting(Districting sortedEnactedDistricting) {
        this.sortedEnactedDistricting = sortedEnactedDistricting;
    }
    @Transient
    public Districting getSortedCurrentDistricting() {
        return sortedCurrentDistricting;
    }

    public void setSortedCurrentDistricting(Districting sortedCurrentDistricting) {
        this.sortedCurrentDistricting = sortedCurrentDistricting;
    }
    @Transient
    public Districting getCurrentDistricting() {
        return currentDistricting;
    }

    public void setCurrentDistricting(Districting currentDistricting) {
        this.currentDistricting = currentDistricting;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<County> getCounties() {
        return counties;
    }

    public void setCounties(List<County> counties) {
        this.counties = counties;
    }

    public Geometry getStateBoundary() {
        return stateBoundary;
    }

    @Transient
    public JSONObject getStateBoundaryJson() throws ParseException {
        JSONParser parser = new JSONParser();
        GeoJsonWriter writer = new GeoJsonWriter();
        Geometry stateBoundary = this.getStateBoundary();
        JSONObject geometry = (JSONObject) parser.parse(writer.write(stateBoundary));
        JSONArray coordinates = (JSONArray) geometry.get("coordinates");
        String type = (String) geometry.get("type");
        JSONObject output = new JSONObject();
        output.put("coordinates", coordinates);
        output.put("type", type);
        JSONObject outputWrapper = new JSONObject();
        outputWrapper.put("type", "Feature");
        outputWrapper.put("geometry", output);
        ArrayList<JSONObject> features = new ArrayList<>();
        features.add(outputWrapper);
        JSONObject outerProperties = new JSONObject();
        outerProperties.put("type", "FeatureCollection");
        outerProperties.put("features", features);
        return outerProperties;
    }

    public void setStateBoundaryJson(JSONObject stateBoundaryJson) {
        this.stateBoundaryJson = stateBoundaryJson;
    }

    public void setStateBoundary(Geometry stateBoundary) {
        this.stateBoundary = stateBoundary;
    }


}
