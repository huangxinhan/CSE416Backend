package com.example.demo.entities;

import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.simple.JSONObject;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

@Entity
@JsonIgnoreProperties({ "precinctBoundaries", "counties" })
public class Districting implements Serializable{
    private String districtingID;

    private List<District> districts;
    private double deviationFromAverage;
    private double deviationFromEnactedPop;
    private double deviationFromEnactedArea;
    private double objectiveFunctionScore;
    private double similarityToEnactedScore;
    private ArrayList<District> majorityMinorityDistricts;
    private int numberOfMajorityMinorityDistricts;
    private double compactness;
    //incumbentDistribution: Map<district: District, List<incumbent>> Ignore
    private int majorityMinorityDistrictsNumber;
    private double populationEqualityDifference;
    private double splitCountyScore;
    private Long tempPopulationByType;
    private HashMap<County, Integer> splitCountyDetails;
    private ArrayList<JSONObject> districtBoundaries;
    private JSONObject precinctBoundaries;
    private double populationPercentDifference;
    private double populationPercentDifferenceVAP;
    private double graphCompactness;
    private ArrayList<County> counties;
    private JSONObject districtingBoundary; //this is the geo json that can be returned.


    public Districting(){

    }



    public Districting(String districtingID) {
        this.districtingID = districtingID;
    }

    public Districting(String districtingID, ArrayList<District> districts){
        this.districtingID = districtingID;
        this.districts = districts;
    }

    public Districting(String districtingID, double compactness, double populationPercentDifference) {
        this.districtingID = districtingID;
        this.compactness = compactness;
        this.populationPercentDifference = populationPercentDifference;
    }

    @Id
    public String getDistrictingID() {
        return districtingID;
    }

    public void setDistrictingID(String districtingID) {
        this.districtingID = districtingID;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    @Transient
    public ArrayList<County> getCounties() {
        return counties;
    }

    public void setCounties(ArrayList<County> counties) {
        this.counties = counties;
    }

    @Transient
    public double getDeviationFromAverage() {
        return deviationFromAverage;
    }

    public void setDeviationFromAverage(double deviationFromAverage) {
        this.deviationFromAverage = deviationFromAverage;
    }
    @Transient
    public double getDeviationFromEnactedPop() {
        return deviationFromEnactedPop;
    }

    public void setDeviationFromEnactedPop(double deviationFromEnactedPop) {
        this.deviationFromEnactedPop = deviationFromEnactedPop;
    }
    @Transient
    public double getDeviationFromEnactedArea() {
        return deviationFromEnactedArea;
    }

    public void setDeviationFromEnactedArea(double deviationFromEnactedArea) {
        this.deviationFromEnactedArea = deviationFromEnactedArea;
    }
    @Transient
    public double getObjectiveFunctionScore() {
        return objectiveFunctionScore;
    }

    public void setObjectiveFunctionScore(double objectiveFunctionScore) {
        this.objectiveFunctionScore = objectiveFunctionScore;
    }
    @Transient
    public double getSimilarityToEnactedScore() {
        return similarityToEnactedScore;
    }

    public void setSimilarityToEnactedScore(double similarityToEnactedScore) {
        this.similarityToEnactedScore = similarityToEnactedScore;
    }
    @Transient
    public ArrayList<District> getMajorityMinorityDistricts() {
        return majorityMinorityDistricts;
    }

    public void setMajorityMinorityDistricts(ArrayList<District> majorityMinorityDistricts) {
        this.majorityMinorityDistricts = majorityMinorityDistricts;
    }
    @Transient
    public int getNumberOfMajorityMinorityDistricts() {
        return numberOfMajorityMinorityDistricts;
    }

    public void setNumberOfMajorityMinorityDistricts(int numberOfMajorityMinorityDistricts) {
        this.numberOfMajorityMinorityDistricts = numberOfMajorityMinorityDistricts;
    }
    @Transient
    public double getCompactness() {
        return compactness;
    }

    public void setCompactness(double compactness) {
        this.compactness = compactness;
    }
    @Transient
    public int getMajorityMinorityDistrictsNumber() {
        return majorityMinorityDistrictsNumber;
    }

    public void setMajorityMinorityDistrictsNumber(int majorityMinorityDistrictsNumber) {
        this.majorityMinorityDistrictsNumber = majorityMinorityDistrictsNumber;
    }
    @Transient
    public double getPopulationEqualityDifference() {
        return populationEqualityDifference;
    }

    public void setPopulationEqualityDifference(double populationEqualityDifference) {
        this.populationEqualityDifference = populationEqualityDifference;
    }
    @Transient
    public double getSplitCountyScore() {
        return splitCountyScore;
    }

    public void setSplitCountyScore(double splitCountyScore) {
        this.splitCountyScore = splitCountyScore;
    }
    @Transient
    public Long getTempPopulationByType() {
        return tempPopulationByType;
    }

    public void setTempPopulationByType(Long tempPopulationByType) {
        this.tempPopulationByType = tempPopulationByType;
    }
    @Transient
    public HashMap<County, Integer> getSplitCountyDetails() {
        return splitCountyDetails;
    }

    public void setSplitCountyDetails(HashMap<County, Integer> splitCountyDetails) {
        this.splitCountyDetails = splitCountyDetails;
    }

    @Transient
    public JSONObject getPrecinctBoundaries() {
        return precinctBoundaries;
    }

    public void setPrecinctBoundaries(JSONObject precinctBoundaries) {
        this.precinctBoundaries = precinctBoundaries;
    }

//    @Transient
    public double getPopulationPercentDifference() {
        return populationPercentDifference;
    }

//    @Transient
    public double getGraphCompactness() {
        return graphCompactness;
    }


    public double getPopulationPercentDifferenceVAP() {
        return populationPercentDifferenceVAP;
    }

    public void setPopulationPercentDifferenceVAP(double populationPercentDifferenceVAP) {
        this.populationPercentDifferenceVAP = populationPercentDifferenceVAP;
    }

    public void setGraphCompactness(double graphCompactness) {
        this.graphCompactness = graphCompactness;
    }

    public void setPopulationPercentDifference(double populationPercentDifference) {
        this.populationPercentDifference = populationPercentDifference;
    }

    @Transient
    public JSONObject getDistrictingBoundary() {
        return districtingBoundary;
    }

    public void setDistrictingBoundary(JSONObject districtingBoundary) {
        this.districtingBoundary = districtingBoundary;
    }

    public ArrayList<Long> retrieveTotalPopulationArray(){
        ArrayList<Long> tempPopulationArray = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            tempPopulationArray.add(this.getDistricts().get(i).getTotalPopulation());
        }
        return tempPopulationArray;
    }

    public ArrayList<Long> retrievePopulationArrayByType(RaceType raceType){
        ArrayList<Long> tempPopulationArray = new ArrayList<Long>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            if(raceType == RaceType.AFRICAN_AMERICAN){
                tempPopulationArray.add(this.getDistricts().get(i).getAfricanAmericanPopulation());
            }
            else if(raceType == RaceType.ASIAN){
                tempPopulationArray.add(this.getDistricts().get(i).getAsianPopulation());
            }
            else if(raceType == RaceType.HISPANIC){
                tempPopulationArray.add(this.getDistricts().get(i).getHispanicPopulation());
            }
        }
        return tempPopulationArray;
    }

    public Long getTotalMinorityPopulationByType(RaceType raceType){
        Long totalPopulation = 0l;
        if (raceType == raceType.AFRICAN_AMERICAN){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getAfricanAmericanPopulation();
            }
        }
        else if (raceType == raceType.ASIAN){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getAsianPopulation();
            }
        }
        else if (raceType == raceType.HISPANIC){
            for (int i = 0; i < this.getDistricts().size(); i++){
                totalPopulation += this.getDistricts().get(i).getHispanicPopulation();
            }
        }
        return totalPopulation;
    }

    public int calculateNumberOfMajorityMinorityDistricts(RaceType raceType, double threshold){
        int numberOfMajorMinorDistricts = 0;
            for (int i = 0; i < this.getDistricts().size(); i++){
                if (raceType == raceType.AFRICAN_AMERICAN){
                    //System.out.println("african american");
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = (double)this.getDistricts().get(i).getAfricanAmericanPopulation()/(double)totalMinorityPopulation;
                    //System.out.println("Current Threshold: " + currentThreshold);
                    //System.out.println("Threshold: " + threshold);
                    if (currentThreshold >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
                else if (raceType == raceType.ASIAN){
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = (double)this.getDistricts().get(i).getAsianPopulation()/(double)totalMinorityPopulation;
                    if (currentThreshold  >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
                else if (raceType == raceType.HISPANIC){
                    Long totalMinorityPopulation = this.getTotalMinorityPopulationByType(raceType);
                    double currentThreshold = (double)this.getDistricts().get(i).getHispanicPopulation()/(double)totalMinorityPopulation;
                    if (currentThreshold  >= threshold){
                        numberOfMajorMinorDistricts += 1;
                    }
                }
            }
            //set the number of majority minority districts
            //System.out.println("number of mm districts is " + numberOfMajorMinorDistricts);
            this.setNumberOfMajorityMinorityDistricts(numberOfMajorMinorDistricts);
        System.out.println("number of mm districts got " + this.getNumberOfMajorityMinorityDistricts());
        return numberOfMajorMinorDistricts;
    }

    public void calculatePopulationConstraintAll(){
        //iterate through all the districts to find the most populous and least populous districts
        long mostPopulousPopulationTotal = 0l;
        long leastPopulousPopulationTotal = 9999999999l;
        long mostPopulousPopulationVAP = 0l;
        long leastPopulousPopulationVAP = 9999999999l;
        for (int i = 0; i < this.getDistricts().size(); i++){

                if (this.getDistricts().get(i).getTotalPopulation() > mostPopulousPopulationTotal){
                    mostPopulousPopulationTotal = this.getDistricts().get(i).getTotalPopulation();
                }
                else if (this.getDistricts().get(i).getTotalPopulation() < leastPopulousPopulationTotal){
                    leastPopulousPopulationTotal = this.getDistricts().get(i).getTotalPopulation();
                }

                if (this.getDistricts().get(i).getVotingAgePopulation() > mostPopulousPopulationVAP){
                    mostPopulousPopulationVAP = this.getDistricts().get(i).getVotingAgePopulation();
                }
                else if (this.getDistricts().get(i).getVotingAgePopulation() < leastPopulousPopulationVAP){
                    leastPopulousPopulationVAP = this.getDistricts().get(i).getVotingAgePopulation();
                }

        }
        System.out.println("mostPopulousTotal: " + mostPopulousPopulationTotal);
        System.out.println("leastPopulousTotal: " + leastPopulousPopulationTotal);
        System.out.println("mostPopulousVAP: " + mostPopulousPopulationVAP);
        System.out.println("leastPopulousVAP " + leastPopulousPopulationVAP);

        //the percent difference in this case is 100 x abs(A-B)/((A+B)/2))
        double percentDifferenceTotal = 100 * Math.abs(((double)mostPopulousPopulationTotal - (double)leastPopulousPopulationTotal)/(((double)mostPopulousPopulationTotal + (double)leastPopulousPopulationTotal)/2));
        double percentDifferenceVAP = 100 * Math.abs(((double)mostPopulousPopulationVAP - (double)leastPopulousPopulationVAP)/(((double)mostPopulousPopulationVAP + (double)leastPopulousPopulationVAP)/2));
        //Here we store the percent difference to use in the objective function later
        this.setPopulationPercentDifference(percentDifferenceTotal);
        this.setPopulationPercentDifferenceVAP(percentDifferenceVAP);
    }

    public boolean calculatePopulationConstraint(PopulationType popType, double threshold){
        //iterate through all the districts to find the most populous and least populous districts
        Long mostPopulousPopulation = 0l;
        Long leastPopulousPopulation = 0l;
        for (int i = 0; i < this.getDistricts().size(); i++){
            if (popType == PopulationType.TOTAL){
                if (this.getDistricts().get(i).getTotalPopulation() > mostPopulousPopulation){
                    mostPopulousPopulation = this.getDistricts().get(i).getTotalPopulation();
                }
                if (this.getDistricts().get(i).getTotalPopulation() < leastPopulousPopulation){
                    leastPopulousPopulation = this.getDistricts().get(i).getTotalPopulation();
                }
            }
            else if (popType == PopulationType.VAP){
                if (this.getDistricts().get(i).getVotingAgePopulation() > mostPopulousPopulation){
                    mostPopulousPopulation = this.getDistricts().get(i).getVotingAgePopulation();
                }
                if (this.getDistricts().get(i).getVotingAgePopulation() < leastPopulousPopulation){
                    leastPopulousPopulation = this.getDistricts().get(i).getVotingAgePopulation();
                }
            }
        }
        //the percent difference in this case is 100 x abs(A-B)/((A+B)/2))
        double percentDifference = 100 * Math.abs((mostPopulousPopulation - leastPopulousPopulation)/((mostPopulousPopulation + leastPopulousPopulation)/2));
        //Here we store the percent difference to use in the objective function later
        this.setPopulationPercentDifference(percentDifference);
        if (percentDifference < threshold){
            return true;
        }
        return false;
    }

    public boolean calculateIncumbentDistricts(ArrayList<String> protectedIncumbents){
        //Iterate through all the district's precincts to see if there are two protected incumbents in the same district
        for (int i = 0; i < this.getDistricts().size(); i++){
            if (this.getDistricts().get(i).hasMultipleIncumbents(protectedIncumbents) == true){
                return true;
            }
        }
        return false;
    }

    public double calculateGraphCompactness(){
        ArrayList<Double> compactnessArray = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            ArrayList<Precinct> edgeNodes = this.getDistricts().get(i).calculateEdgeNodes();
            double compactness = (double)edgeNodes.size()/(double)this.getDistricts().get(i).getPrecincts().size();
            compactnessArray.add(compactness);
        }
        //now that we have the compactness, we add all of them together then divide by the array size
        double totalCompactness = 0;
        for (int i = 0; i < compactnessArray.size(); i++){
            totalCompactness += compactnessArray.get(i);
        }
        double graphCompactness = totalCompactness/(double)compactnessArray.size();
        //store the graph compactness measure for easier calculation for the objective function
        this.setGraphCompactness(graphCompactness);
        System.out.println("GRAPH COMPACTNESS IS " + graphCompactness);
        return graphCompactness;
    }

    @Transient
    public ArrayList<JSONObject> getDistrictBoundaries() {
        return districtBoundaries;
    }

    public void setDistrictBoundaries(ArrayList<JSONObject> districtBoundaries) {
        this.districtBoundaries = districtBoundaries;
    }

    public void setDistrictBoundaryJSON(){
        ArrayList<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            jsonObjects.add(this.getDistricts().get(i).getBorderGeometryJson());
        }
        this.setDistrictBoundaries(jsonObjects);
    }

    public void setPrecinctBoundaryJSON(){
        ArrayList<ArrayList<JSONObject>> jsonObjects = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            jsonObjects.add(this.getDistricts().get(i).getPrecinctBoundariesJson());
        }
        ArrayList<JSONObject> innerFeatures = new ArrayList<>();
        for (int i = 0; i < jsonObjects.size(); i++){
            for (int j = 0; j < jsonObjects.get(i).size(); j++){
                innerFeatures.add(jsonObjects.get(i).get(j));
            }
        }
        JSONObject outerProperties = new JSONObject();
        outerProperties.put("type", "FeatureCollection");
        outerProperties.put("features", innerFeatures);
        this.setPrecinctBoundaries(outerProperties);
    }

    public void calculateObjectiveFunctionScore(HashMap<Measures, Double> weights, PopulationType populationType, RaceType minorityType, ArrayList<Double> means, int index, ArrayList<Double> enactedDistrictingData){
        //now this method here will call various other methods to calculate the objective function score.
        double objectiveFunctionScore = 0;
        objectiveFunctionScore += this.calculateOFScoreByPopulationEquality(populationType, weights);
        objectiveFunctionScore += this.calculateOFScoreByAverageDistricting(minorityType, weights, means, index);
        objectiveFunctionScore += this.calculateOFScoryBySplitCounty(weights);
        objectiveFunctionScore += this.calculateOFScoreByDeviationFromEnactedPlan(minorityType, weights, means, index, enactedDistrictingData);
        System.out.println("current OF Score" + objectiveFunctionScore);
        this.setObjectiveFunctionScore(objectiveFunctionScore);
    }


    public double calculateOFScoryBySplitCounty(HashMap<Measures, Double> weights)
    {
        double score =0;

        HashMap<String, Integer> countyCounter = new HashMap<>();
        ArrayList<County> countyList = this.getCounties();
        for(County county : countyList)
        {
            countyCounter.put(county.getCountyID(), 0);
        }

        for(District d: this.getDistricts())
        {
            ArrayList<String> appearCounty  = new ArrayList<>();

            for( Precinct p : d.getPrecincts())
            {
                if( ! appearCounty.contains(p.getCountyID().getCountyID()))
                {
                    appearCounty.add(p.getCountyID().getCountyID());
                }

            }

            for(String name : appearCounty)
            {
                countyCounter.put(name,countyCounter.get(name) +1);
            }

        }

        int threeCounter = 0;

        int twoCountr = 0;

        for( int time : countyCounter.values())
        {
            if(time >=3)
            {
                threeCounter += 1;

                continue;
            }

            if(time >=2)
            {
                twoCountr +=1;
            }
        }

        score = (twoCountr + 10 * threeCounter)/300.0;
        System.out.println(weights.get(Measures.SPLIT_COUNTIES) * score);
        this.setSplitCountyScore(weights.get(Measures.SPLIT_COUNTIES) * score);
        return weights.get(Measures.SPLIT_COUNTIES) * score;
    }

    //Need to change so that the population type can change.
    public double calculateOFScoreByPopulationEquality(PopulationType populationType, HashMap<Measures, Double> weights){
        int numberOfCDs = this.getDistricts().size();
        double sum = 0;
        long total_population = 0;
        if (populationType == PopulationType.TOTAL) {
            for (int i = 0; i < this.getDistricts().size(); i++) {
                total_population += (double)this.getDistricts().get(i).getTotalPopulation();
            }
            double idealPopulation = (double)total_population / (double)this.getDistricts().size();
            for (int i = 0; i < this.getDistricts().size(); i++) {
                sum += Math.pow((((double) this.getDistricts().get(i).getTotalPopulation() / (double)idealPopulation))-1, 2);
                this.getDistricts().get(i).setPopulationEquality(sum);
            }
        }
        else if (populationType == PopulationType.VAP) {
            for (int i = 0; i < (double)this.getDistricts().size(); i++) {
                total_population += (double)this.getDistricts().get(i).getVotingAgePopulation();
            }
            double idealPopulation = (double)total_population / (double)this.getDistricts().size();
            for (int i = 0; i < this.getDistricts().size(); i++) {
                sum += Math.pow((((double)this.getDistricts().get(i).getVotingAgePopulation() / (double)idealPopulation))-1, 2);
            }
        }
        double weight = weights.get(Measures.POPULATION_EQUALITY);
        double final_score = weight * Math.sqrt(sum);
        System.out.println("objective function score for pop eq is: " + final_score);
        this.setPopulationEqualityDifference(final_score);
        return final_score;
    }

    public double calculateOFScoreByAverageDistricting(RaceType minorityType, HashMap<Measures, Double> weights, ArrayList<Double> means, int index){
        double sum = 0;
        System.out.println("Got the dev from avg " + weights.get(Measures.DEVIATION_FROM_AVERAGE));
        //deviation from the means
        if (minorityType == RaceType.AFRICAN_AMERICAN) {
            for (int i = 0; i < this.getDistricts().size(); i++) {
                sum += Math.pow((((double)this.getDistricts().get(i).getAfricanAmericanPopulation() /(double) this.getDistricts().get(i).getTotalPopulation()) - means.get(index)), 2);
                this.getDistricts().get(i).setDeviationAverage(sum);
            }
            //then normalize it or something here...
        }
        else if (minorityType == RaceType.ASIAN) {
            for (int i = 0; i < this.getDistricts().size(); i++) {
                sum += Math.pow((((double)this.getDistricts().get(i).getAsianPopulation() / (double)this.getDistricts().get(i).getTotalPopulation()) - means.get(index)), 2);
                this.getDistricts().get(i).setDeviationAverage(sum);
            }
        }
        else if (minorityType == RaceType.HISPANIC) {
            for (int i = 0; i < this.getDistricts().size(); i++) {
                sum += Math.pow((((double)this.getDistricts().get(i).getHispanicPopulation() / (double)this.getDistricts().get(i).getTotalPopulation()) - means.get(index)), 2);
                this.getDistricts().get(i).setDeviationAverage(sum);
            }
        }
        System.out.println("Objective Function Score By Average Districting is " + weights.get(Measures.DEVIATION_FROM_AVERAGE) * Math.sqrt(sum));

        return weights.get(Measures.DEVIATION_FROM_AVERAGE) * Math.sqrt(sum);
    }

    public double calculateOFScoreByDeviationFromEnactedPlan(RaceType minorityType, HashMap<Measures, Double> weights, ArrayList<Double> means, int index, ArrayList<Double> enactedDistrictingData){
        double sum = 0;
        ArrayList<District> tempDistricts = new ArrayList<>();
        for (int i = 0; i < this.getDistricts().size(); i++){
            tempDistricts.add(this.getDistricts().get(i));
        }
        if (minorityType == RaceType.AFRICAN_AMERICAN){
            //now we have a temp districts of the districts
            tempDistricts.sort(Comparator.comparing((District::getDistrictNumber)));
            for (int i = 0; i < tempDistricts.size(); i++){
                sum += Math.pow(((double)tempDistricts.get(i).getAfricanAmericanPopulation() / (double)tempDistricts.get(i).getTotalPopulation())-enactedDistrictingData.get(i), 2);
                tempDistricts.get(i).setDeviationEnacted(sum);
            }
        }
        else if (minorityType == RaceType.HISPANIC){
            //now we have a temp districts of the districts
            tempDistricts.sort(Comparator.comparing((District::getDistrictNumber)));
            for (int i = 0; i < tempDistricts.size(); i++){
                sum += Math.pow(((double)tempDistricts.get(i).getHispanicPopulation() / (double)tempDistricts.get(i).getTotalPopulation())-enactedDistrictingData.get(i), 2);
                tempDistricts.get(i).setDeviationEnacted(sum);
            }
        }

        else if (minorityType == RaceType.ASIAN){
            //now we have a temp districts of the districts
            tempDistricts.sort(Comparator.comparing((District::getDistrictNumber)));
            for (int i = 0; i < tempDistricts.size(); i++){
                sum += Math.pow(((double)tempDistricts.get(i).getAsianPopulation() / (double)tempDistricts.get(i).getTotalPopulation())-enactedDistrictingData.get(i), 2);
                tempDistricts.get(i).setDeviationEnacted(sum);
            }
        }
        System.out.println("Objective Function score by deviation from enacted: " + Math.sqrt(weights.get(Measures.DEVIATION_FROM_ENACTEDPOP) * sum));
        this.setDeviationFromEnactedPop(weights.get(Measures.DEVIATION_FROM_ENACTEDPOP) * Math.sqrt(sum));
        return Math.sqrt(weights.get(Measures.DEVIATION_FROM_ENACTEDPOP) * sum);
    }
}
