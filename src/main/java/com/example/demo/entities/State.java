package com.example.demo.entities;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany()

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

    public void setStateBoundary(Geometry stateBoundary) {
        this.stateBoundary = stateBoundary;
    }
}
