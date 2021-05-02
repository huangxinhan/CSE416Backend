package com.example.demo.entities;
import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Entity
public class Job implements Serializable{
    private String jobID;
    private Constraints constraints;
    private List<Districting> districtings;
    private List<Districting> tempConstrainedDistrictings;
    private ConstrainedDistrictings constrainedDistrictings;
    private HashMap<Measures, Double> weights;
    private ArrayList<Districting> topDistrictingsByOFScore;
    private ArrayList<Districting> topDistrictingsByEnacted;
    private ArrayList<Districting> topDistrictingsByHighScoreMajMinDistricts;
    private ArrayList<Districting> topDistrictingsBySigmaAvg;
    private ArrayList<Districting> topDistrictingsBySigmaEnacted;
    private ArrayList<Districting> topDistrictingsByCompactness;
    private ArrayList<Districting> topDistrictingsByAreaPairDeviation;
    private ArrayList<Districting> topDistrictingsBySimilarity;
    @ElementCollection
    private ArrayList<String> mgggParams;
    private String stateName;
    private Enum<RaceType> raceType;
    private int filteredByMMDCount;
    private int filteredByPopConstraintCount;
    private int filteredByCompactnessCount;
    private int filteredByIncumbentCount;

    public Job(){

    }

    public Job(String JobID, List<Districting> districtings, ArrayList<String> mgggParams, String stateName){
        this.jobID = jobID;
        this.districtings = districtings;
        this.mgggParams = mgggParams;
        this.stateName = stateName;
    }

    @Id
    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    @Transient
    public Constraints getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraints constraints) {
        this.constraints = constraints;
    }
    @OneToMany(cascade = CascadeType.ALL)
    public List<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(List<Districting> districtings) {
        this.districtings = districtings;
    }
    @Transient
    public List<Districting> getTempConstrainedDistrictings() {
        return tempConstrainedDistrictings;
    }

    public void setTempConstrainedDistrictings(ArrayList<Districting> tempConstrainedDistrictings) {
        this.tempConstrainedDistrictings = tempConstrainedDistrictings;
    }
    @Transient
    public ConstrainedDistrictings getConstrainedDistrictings() {
        return constrainedDistrictings;
    }

    public void setConstrainedDistrictings(ConstrainedDistrictings constrainedDistrictings) {
        this.constrainedDistrictings = constrainedDistrictings;
    }
    @Transient
    public HashMap<Measures, Double> getWeights() {
        return weights;
    }

    public void setWeights(HashMap<Measures, Double> weights) {
        this.weights = weights;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByOFScore() {
        return topDistrictingsByOFScore;
    }

    public void setTopDistrictingsByOFScore(ArrayList<Districting> topDistrictingsByOFScore) {
        this.topDistrictingsByOFScore = topDistrictingsByOFScore;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByEnacted() {
        return topDistrictingsByEnacted;
    }

    public void setTopDistrictingsByEnacted(ArrayList<Districting> topDistrictingsByEnacted) {
        this.topDistrictingsByEnacted = topDistrictingsByEnacted;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByHighScoreMajMinDistricts() {
        return topDistrictingsByHighScoreMajMinDistricts;
    }

    public void setTopDistrictingsByHighScoreMajMinDistricts(ArrayList<Districting> topDistrictingsByHighScoreMajMinDistricts) {
        this.topDistrictingsByHighScoreMajMinDistricts = topDistrictingsByHighScoreMajMinDistricts;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySigmaAvg() {
        return topDistrictingsBySigmaAvg;
    }

    public void setTopDistrictingsBySigmaAvg(ArrayList<Districting> topDistrictingsBySigmaAvg) {
        this.topDistrictingsBySigmaAvg = topDistrictingsBySigmaAvg;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySigmaEnacted() {
        return topDistrictingsBySigmaEnacted;
    }

    public void setTopDistrictingsBySigmaEnacted(ArrayList<Districting> topDistrictingsBySigmaEnacted) {
        this.topDistrictingsBySigmaEnacted = topDistrictingsBySigmaEnacted;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByCompactness() {
        return topDistrictingsByCompactness;
    }

    public void setTopDistrictingsByCompactness(ArrayList<Districting> topDistrictingsByCompactness) {
        this.topDistrictingsByCompactness = topDistrictingsByCompactness;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsByAreaPairDeviation() {
        return topDistrictingsByAreaPairDeviation;
    }

    public void setTopDistrictingsByAreaPairDeviation(ArrayList<Districting> topDistrictingsByAreaPairDeviation) {
        this.topDistrictingsByAreaPairDeviation = topDistrictingsByAreaPairDeviation;
    }
    @Transient
    public ArrayList<Districting> getTopDistrictingsBySimilarity() {
        return topDistrictingsBySimilarity;
    }

    public void setTopDistrictingsBySimilarity(ArrayList<Districting> topDistrictingsBySimilarity) {
        this.topDistrictingsBySimilarity = topDistrictingsBySimilarity;
    }

    public ArrayList< String> getMgggParams() {
        return mgggParams;
    }

    public void setMgggParams(ArrayList<String> mgggParams) {
        this.mgggParams = mgggParams;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Transient
    public Enum<RaceType> getRaceType() {
        return raceType;
    }

    public void setRaceType(Enum<RaceType> raceType) {
        this.raceType = raceType;
    }

    @Transient
    public int getFilteredByMMDCount() {
        return filteredByMMDCount;
    }

    public void setFilteredByMMDCount(int filteredByMMDCount) {
        this.filteredByMMDCount = filteredByMMDCount;
    }
    @Transient
    public int getFilteredByPopConstraintCount() {
        return filteredByPopConstraintCount;
    }

    public void setFilteredByPopConstraintCount(int filteredByPopConstraintCount) {
        this.filteredByPopConstraintCount = filteredByPopConstraintCount;
    }

    @Transient
    public int getFilteredByCompactnessCount() {
        return filteredByCompactnessCount;
    }

    public void setFilteredByCompactnessCount(int filteredByCompactnessCount) {
        this.filteredByCompactnessCount = filteredByCompactnessCount;
    }

    @Transient
    public int getFilteredByIncumbentCount() {
        return filteredByIncumbentCount;
    }

    public void setFilteredByIncumbentCount(int filteredByIncumbentCount) {
        this.filteredByIncumbentCount = filteredByIncumbentCount;
    }

    public void calculateTopDistrictingsByOF(HashMap<Measures, Double> measures, Enum<RaceType> raceType) {

    }

    public void sortDistrictingByOF(){
        this.getTopDistrictingsByOFScore().sort(Comparator.comparing(Districting::getObjectiveFunctionScore));
    }

    public void sortDistrictingByEnacted(){
        this.getTopDistrictingsByEnacted().sort(Comparator.comparing(Districting::getDeviationFromEnactedPop));
    }

    public ArrayList<Districting> retrieveTopDistrictingsByOF(){
        ArrayList<Districting> topTenDistrictings = new ArrayList<Districting>();
        this.sortDistrictingByOF();
        for (int i = 0; i < 10; i++){
            topTenDistrictings.add(this.getTopDistrictingsByOFScore().get(i));
        }
        return topTenDistrictings;
    }

    //public void createConstrainedObject{}

    //public void calculateDistrictGeometry{}

    //get box and whisker data based on the type that is passed
    public Plot retrieveBoxAndWhiskerData(RaceType raceType){
        return this.getConstrainedDistrictings().retrievePlotByType(raceType);
    }

    //will calculate average districting AND generate the graph object
    public void calculateAverageDistricting(RaceType raceType){
        this.getConstrainedDistrictings().calculateAverageDistricting(raceType);
    }

    public void filterMajorityMinorityDistrictings(){
        int counter = 0;
        RaceType race = this.getConstraints().getMinorityType();
        double majorMinorThres = this.getConstraints().getMajorMinorThres();
        int numberOfMMDistricts = this.getConstraints().getNumberOfMajorityMinorityDistricts();
        for (int i = 0; i < this.getDistrictings().size(); i++){
            int number = this.getDistrictings().get(i).calculateNumberOfMajorityMinorityDistricts(race, majorMinorThres);
            if (number >= numberOfMMDistricts){
                this.getConstrainedDistrictings().getDistrictings().add(this.getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        this.setFilteredByMMDCount(counter);
    }

    public void filterPopulationEqualityDistrictings(){
        int counter = 0;
        PopulationType populationType = this.getConstraints().getPopulationType();
        double populationEqualityThres = this.getConstraints().getPopulationEqualityThres();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            if (this.getConstrainedDistrictings().getDistrictings().get(i).calculatePopulationConstraint(populationType, populationEqualityThres) == true){
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        this.setFilteredByPopConstraintCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    public void filterIncumbentProtectionDistrictings(){
        int counter = 0;
        ArrayList<String> protectedIncumbents = this.getConstraints().getProtectedIncumbents();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            if (this.getConstrainedDistrictings().getDistrictings().get(i).calculateIncumbentDistricts(protectedIncumbents) == false){
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        this.setFilteredByIncumbentCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    public void filterGraphCompactness(){
        int counter = 0;
        double compactnessValue = this.getConstraints().getCompactnessValue();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        //we calculate graph compactness, which is the ratio of edge nodes in a graph over total nodes in a graph
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            double compactness = this.getConstrainedDistrictings().getDistrictings().get(i).calculateGraphCompactness();
            if (compactness >= compactnessValue){
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        this.setFilteredByCompactnessCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    public void calculateDistrictingGeometry(Districting districting){
        for (int i = 0; i < districting.getDistricts().size(); i++){
            districting.getDistricts().get(i).calculateDistrictGeometry();
        }
    }



//    filterMajorityMinorityDistrictings(districtings: Districtings, constraints.minorityRace, constraints.MajorMinorThres, constraints.populationType): List<Districting>
//    filterPopulationEqualityDistrictings(districtings: Districtings, constraints.minorityRace, constraints.populationEqualityThres, constraints.populationType): List<Districting>
//    filterIncumbentProtectionDistrictings(constrainedDistrictings: ConstrainedDistricting, constraints.incumbentProtectionThres) : List <Districting>
//    filterCompactnessDistrictings(constrainedDistrictings: ConstrainedDistricting, constraints.incumbentProtectionThres) : List<Districting>
//    getBoxAndWhiskerData(constraints.populationType : PopulationType, .constraints.minorityType: minorityType, constrainedDistrictings: ConstrainedDistrictings): Plot
//    calculateSplitCountyScore(constrainedDistrictings: constrainedDistrictings)
//    calculateDeviationFromAverageDistricting(constrainedDistricting: ConstrainedDistricting, constraints.populationType, constraints.minorityType): void
//    getSplitCountyGeometry(disticting: Districting): List<geometry>
//    getEqualPopulationDistricts(districting: Districting): List<District>





}
