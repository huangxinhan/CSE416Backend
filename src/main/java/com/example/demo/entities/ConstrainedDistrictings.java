package com.example.demo.entities;

import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;
import java.util.Collections;

public class ConstrainedDistrictings {
    private ArrayList<Districting> districtings;
    private Districting currentDistricting;
    private Districting enactedDistricting;
    private ArrayList<Districting> sortedDistricting;
    private ArrayList<Double> sortedCurrentDistrictingData;
    private ArrayList<Double> enactedDistrictingData;
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

    public ArrayList<Double> getSortedCurrentDistrictingData() {
        return sortedCurrentDistrictingData;
    }

    public void setSortedCurrentDistrictingData(ArrayList<Double> sortedCurrentDistrictingData) {
        this.sortedCurrentDistrictingData = sortedCurrentDistrictingData;
    }

    public ArrayList<Double> getEnactedDistrictingData() {
        return enactedDistrictingData;
    }

    public void setEnactedDistrictingData(ArrayList<Double> enactedDistrictingData) {
        this.enactedDistrictingData = enactedDistrictingData;
    }

    public Plot getPlotByType(RaceType raceType){
        ArrayList<Long> populationArray = this.getCurrentDistricting().getPopulationArrayByType(raceType);
        ArrayList<Long> totalPopulationArray = this.getCurrentDistricting().getTotalPopulationArray();
        ArrayList<Double> populationPercentageArray = new ArrayList<Double>();
        for (int i = 0; i < populationArray.size(); i++){
            populationPercentageArray.add((double) populationArray.get(i)/totalPopulationArray.get(i));
        }
        Collections.sort(populationPercentageArray); //sort by population
        this.setSortedCurrentDistrictingData(populationPercentageArray);
        this.getPlot().setCurrentDistrictingData(populationPercentageArray);
        return this.getPlot();
    }

    public ArrayList<Double> calculateEnactedPercentages(RaceType raceType){
        ArrayList<Long> populationArray = this.getEnactedDistricting().getPopulationArrayByType(raceType);
        ArrayList<Long> totalPopulationArray = this.getEnactedDistricting().getTotalPopulationArray();
        ArrayList<Double> populationPercentageArray = new ArrayList<Double>();
        for (int i = 0; i < populationArray.size(); i++){
            populationPercentageArray.add((double) populationArray.get(i)/totalPopulationArray.get(i));
        }
        Collections.sort(populationPercentageArray); //sort by population
        this.setEnactedDistrictingData(populationPercentageArray);
        return populationPercentageArray;
    }

    public void calculateAverageDistricting(RaceType raceType){
        ArrayList<ArrayList<Double>> percentageList = new ArrayList<ArrayList<Double>>();
        for (int i = 0; i < this.getDistrictings().size(); i++){
            ArrayList<Double> percentages = new ArrayList<Double>();
            Districting districting = this.getDistrictings().get(i);
            ArrayList<Long> populationArray = districting.getPopulationArrayByType(raceType);
            ArrayList<Long> totalPopulationArray = districting.getTotalPopulationArray();
            for (int j = 0; j < populationArray.size(); j++){
                percentages.add((double) populationArray.get(j)/totalPopulationArray.get(j));
            }
            Collections.sort(percentages);
            percentageList.add(percentages);
        }
        //now here we have an arraylist of list of sorted percentages to calculate average districting
        this.getPlot().setMinorityType(raceType);
        this.getPlot().setPopulationPercentages(percentageList);
        //now calculate enacted districting then set to the plot
        this.getPlot().setEnactedDistrictingData(this.calculateEnactedPercentages(raceType));
    }

    public void calculateMeans(ArrayList<ArrayList<Double>> percentageList){
        ArrayList<Double> means = new ArrayList<>();
        for (int i = 0; i < percentageList.size(); i++){
            double sum = 0;
            for (int j = 0; j < percentageList.get(i).size(); j++){
                sum += percentageList.get(i).get(j);
            }
            double mean = sum / percentageList.get(i).size();
            means.add(mean);
        }
        this.setMeans(means);
    }

    public void calculateClosestToAvgDistricting(ArrayList<Double> means){
        //will need to sort districtings in here  as well
        //take sorted Districting's district's population by type - the mean
        //do a sum of squares of that and assign that to a particular districting
        //now take the districting with the lowest sum of squares as the cloest to average.
    }
}
