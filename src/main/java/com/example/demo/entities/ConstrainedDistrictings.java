package com.example.demo.entities;

import java.util.ArrayList;

public class ConstrainedDistrictings {
    private ArrayList<Districting> districtings;
    private Districting currentDistricting;
    private Districting enactedDistricting;
    private ArrayList<Districting> sortedDistricting;
    private Plot plot;
    private ArrayList<Double> means;
    private Districting closesestDistrictingToTheAverage;

    public ConstrainedDistrictings(){

    }

    public ConstrainedDistrictings(ArrayList<Districting> districtings, Districting currentDistricting, Districting enactedDistricting, ArrayList<Districting> sortedDistricting, Plot plot, ArrayList<Double> means, Districting closesestDistrictingToTheAverage) {
        this.districtings = districtings;
        this.currentDistricting = currentDistricting;
        this.enactedDistricting = enactedDistricting;
        this.sortedDistricting = sortedDistricting;
        this.plot = plot;
        this.means = means;
        this.closesestDistrictingToTheAverage = closesestDistrictingToTheAverage;
    }

    public ArrayList<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(ArrayList<Districting> districtings) {
        this.districtings = districtings;
    }

    public Districting getCurrentDistricting() {
        return currentDistricting;
    }

    public void setCurrentDistricting(Districting currentDistricting) {
        this.currentDistricting = currentDistricting;
    }

    public Districting getEnactedDistricting() {
        return enactedDistricting;
    }

    public void setEnactedDistricting(Districting enactedDistricting) {
        this.enactedDistricting = enactedDistricting;
    }

    public ArrayList<Districting> getSortedDistricting() {
        return sortedDistricting;
    }

    public void setSortedDistricting(ArrayList<Districting> sortedDistricting) {
        this.sortedDistricting = sortedDistricting;
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public ArrayList<Double> getMeans() {
        return means;
    }

    public void setMeans(ArrayList<Double> means) {
        this.means = means;
    }

    public Districting getClosesestDistrictingToTheAverage() {
        return closesestDistrictingToTheAverage;
    }

    public void setClosesestDistrictingToTheAverage(Districting closesestDistrictingToTheAverage) {
        this.closesestDistrictingToTheAverage = closesestDistrictingToTheAverage;
    }
}
