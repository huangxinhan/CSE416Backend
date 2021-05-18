package com.example.demo.entities;

import com.example.demo.entities.enums.RaceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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

    public Plot retrievePlotByType(RaceType raceType){
        ArrayList<Long> populationArray = this.getCurrentDistricting().retrievePopulationArrayByType(raceType);
        ArrayList<Long> totalPopulationArray = this.getCurrentDistricting().retrieveTotalPopulationArray();
        ArrayList<Double> populationPercentageArray = new ArrayList<Double>();
        for (int i = 0; i < populationArray.size(); i++){
            populationPercentageArray.add((double) populationArray.get(i)/totalPopulationArray.get(i));
        }
        Collections.sort(populationPercentageArray); //sort by population
        this.setSortedCurrentDistrictingData(populationPercentageArray);
        this.getPlot().setCurrentDistrictingData(populationPercentageArray);
        ArrayList<ArrayList<Double>> boxAndWhiskerData = new ArrayList<>();
        for (int i = 0; i < this.getPlot().getEnactedDistrictingData().size(); i++){
            boxAndWhiskerData.add(new ArrayList<Double>());
        }
        System.out.println("population percentages is : " + this.getPlot().getPopulationPercentages());
        for (int i = 0; i < this.getPlot().getPopulationPercentages().size(); i++){
            for (int j = 0; j < this.getPlot().getEnactedDistrictingData().size(); j++){ //0-18
                System.out.println(this.getPlot().getPopulationPercentages().get(i).get(j));
                boxAndWhiskerData.get(j).add(this.getPlot().getPopulationPercentages().get(i).get(j));
            }
        }
        this.getPlot().setBoxAndWhiskerPercentages(boxAndWhiskerData);
        return this.getPlot();
    }

    public ArrayList<Double> calculateEnactedPercentages(RaceType raceType){
        for (int i = 0; i < this.getEnactedDistricting().getDistricts().size(); i++){
            this.getEnactedDistricting().getDistricts().get(i).calculateAllPopulation();
        }
        ArrayList<Long> populationArray = this.getEnactedDistricting().retrievePopulationArrayByType(raceType);
        ArrayList<Long> totalPopulationArray = this.getEnactedDistricting().retrieveTotalPopulationArray();
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
            ArrayList<Long> populationArray = districting.retrievePopulationArrayByType(raceType);
            ArrayList<Long> totalPopulationArray = districting.retrieveTotalPopulationArray();
            for (int j = 0; j < populationArray.size(); j++){
                percentages.add((double) populationArray.get(j)/totalPopulationArray.get(j));
            }
            Collections.sort(percentages);
            percentageList.add(percentages);
        }
        //now here we have an arraylist of list of sorted percentages to calculate average districting
        this.calculateMeans(percentageList);
        System.out.println("means: " + this.getMeans());
        this.calculateClosestToAvgDistricting(this.getMeans(), raceType);
        this.getPlot().setMinorityType(raceType);
        this.getPlot().setPopulationPercentages(percentageList);
        this.getPlot().setEnactedDistrictingData(this.calculateEnactedPercentages(raceType));
        System.out.println("plot population Percentages: " + this.getPlot().getPopulationPercentages());
        System.out.println("plot enacted Districting Data: " + this.getPlot().getEnactedDistrictingData());
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

    public void calculateClosestToAvgDistricting(ArrayList<Double> means, RaceType raceType){
        //first sort the districtings
        for (int i = 0; i < this.getDistrictings().size(); i++){
            if (raceType == raceType.AFRICAN_AMERICAN) {
                this.getDistrictings().get(i).getDistricts().sort(Comparator.comparing(District::getAfricanAmericanPopulation));
            }
            else if (raceType == raceType.ASIAN){
                this.getDistrictings().get(i).getDistricts().sort(Comparator.comparing(District::getAsianPopulation));
            }
            else if (raceType == raceType.HISPANIC){
                this.getDistrictings().get(i).getDistricts().sort(Comparator.comparing(District::getHispanicPopulation));
            }
        }
        ArrayList<Double> differences = new ArrayList<>();
        for (int i = 0; i < this.getDistrictings().size(); i++){
            double difference = 0;
            for (int j = 0; j < this.getDistrictings().get(i).getDistricts().size(); j++){
                if (raceType == raceType.AFRICAN_AMERICAN) {
                    difference += Math.pow((means.get(i) - (double)this.getDistrictings().get(i).getDistricts().get(j).getAfricanAmericanPopulation())/(double) this.getDistrictings().get(i).getDistricts().get(j).getTotalPopulation(), 2);
                }
                if (raceType == raceType.ASIAN) {
                    difference += Math.pow((means.get(i) - (double)this.getDistrictings().get(i).getDistricts().get(j).getAsianPopulation())/(double) this.getDistrictings().get(i).getDistricts().get(j).getTotalPopulation(),2);
                }
                if (raceType == raceType.HISPANIC) {
                    difference += Math.pow((means.get(i) - (double)this.getDistrictings().get(i).getDistricts().get(j).getHispanicPopulation())/(double) this.getDistrictings().get(i).getDistricts().get(j).getTotalPopulation(),2);
                }
            }
            double final_difference = Math.sqrt(difference);
            this.getDistrictings().get(i).setDeviationFromAverage(final_difference);
            differences.add(final_difference);
        }

        int minimumDistrictingIndex = differences.indexOf(Collections.min(differences));
        this.setClosesestDistrictingToTheAverage(this.getDistrictings().get(minimumDistrictingIndex));
        System.out.println("min districting index: " + minimumDistrictingIndex);
    }
}
