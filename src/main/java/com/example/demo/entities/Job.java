package com.example.demo.entities;
import com.example.demo.entities.enums.Measures;
import com.example.demo.entities.enums.PopulationType;
import com.example.demo.entities.enums.RaceType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.json.simple.parser.ParseException;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

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
    //@ElementCollection
    //private ArrayList<String> mgggParams;
    private JobSummary jobSummary;
    private String stateName;
    private Enum<RaceType> raceType;
    private int filteredByMMDCount;
    private int filteredByPopConstraintCount;
    private int filteredByCompactnessCount;
    private int filteredByIncumbentCount;
    private int districtingCollectionNumber;

    public Job(){

    }

    public Job(String JobID, List<Districting> districtings, JobSummary jobSummary, String stateName){
        this.jobID = jobID;
        this.districtings = districtings;
        this.jobSummary = jobSummary;
        this.stateName = stateName;
    }

    public Job(String pa_job1) {
        this.jobID = pa_job1;
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
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @Fetch(value = FetchMode.SUBSELECT)

    @Transient
    public List<Districting> getDistrictings() {
        return districtings;
    }

    public void setDistrictings(List<Districting> districtings) {
        this.districtings = districtings;
    }

    public int getDistrictingCollectionNumber() {
        return districtingCollectionNumber;
    }

    public void setDistrictingCollectionNumber(int districtingCollectionNumber) {
        this.districtingCollectionNumber = districtingCollectionNumber;
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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    public JobSummary getJobSummary() {
        return jobSummary;
    }

    public void setJobSummary(JobSummary jobSummary) {
        this.jobSummary = jobSummary;
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

    public void calculateTopDistrictingsByOF(HashMap<Measures, Double> measures, RaceType raceType) {

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

//    public void filterMajorityMinorityDistrictings(){
//        int counter = 0;
//        RaceType race = this.getConstraints().getMinorityType();
//        double majorMinorThres = this.getConstraints().getMajorMinorThres();
//        int numberOfMMDistricts = this.getConstraints().getNumberOfMajorityMinorityDistricts();
//        for (int i = 0; i < this.getDistrictings().size(); i++){
//            int number = this.getDistrictings().get(i).calculateNumberOfMajorityMinorityDistricts(race, majorMinorThres);
//            if (number >= numberOfMMDistricts){
//                this.getConstrainedDistrictings().getDistrictings().add(this.getDistrictings().get(i));
//            }
//            else{
//                counter++;
//            }
//        }
//        System.out.println("filtered out by majority minority: ");
//        System.out.println(counter);
//
//        this.setFilteredByMMDCount(counter);
//    }
//
//
//    public void filterPopulationEqualityDistrictings(){
//        int counter = 0;
//        PopulationType populationType = this.getConstraints().getPopulationType();
//        double populationEqualityThres = this.getConstraints().getPopulationEqualityThres();
//        ArrayList<Districting> tempDistrictings = new ArrayList<>();
//        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
//            if (this.getConstrainedDistrictings().getDistrictings().get(i).calculatePopulationConstraint(populationType, populationEqualityThres) == true){
//                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
//            }
//            else{
//                counter++;
//            }
//        }
//        System.out.println("filtered out by population Equality: ");
//        System.out.println(counter);
//        this.setFilteredByPopConstraintCount(counter);
//        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
//    }
//
//
//
//
//
//    public void filterIncumbentProtectionDistrictings(){
//        int counter = 0;
//        ArrayList<String> protectedIncumbents = this.getConstraints().getProtectedIncumbents();
//        ArrayList<Districting> tempDistrictings = new ArrayList<>();
//        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
//            if (this.getConstrainedDistrictings().getDistrictings().get(i).calculateIncumbentDistricts(protectedIncumbents) == false){
//                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
//            }
//            else{
//                counter++;
//            }
//        }
//        System.out.println("filtered out by IncumbentProtection: ");
//        System.out.println(counter);
//        this.setFilteredByIncumbentCount(counter);
//        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
//    }
//
//    public void filterGraphCompactness(){
//        int counter = 0;
//        double compactnessValue = this.getConstraints().getCompactnessValue();
//        ArrayList<Districting> tempDistrictings = new ArrayList<>();
//        //we calculate graph compactness, which is the ratio of edge nodes in a graph over total nodes in a graph
//        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
//            double compactness = this.getConstrainedDistrictings().getDistrictings().get(i).calculateGraphCompactness();
//            if (compactness >= compactnessValue){
//                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
//            }
//            else{
//                counter++;
//            }
//        }
//        System.out.println("filtered out by graph Compactness: ");
//        System.out.println(counter);
//        this.setFilteredByCompactnessCount(counter);
//        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
//    }


    public void calculateDistrictingGeometry(Districting districting) throws ParseException {
        for (int i = 0; i < districting.getDistricts().size(); i++){
            districting.getDistricts().get(i).calculateDistrictGeometry();
        }
    }

    //sets the objective function score for each districtings
    public void calculateDistrictingScoresByObjectiveFunction(){
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            this.getConstrainedDistrictings().getDistrictings().get(i).calculateObjectiveFunctionScore(this.getWeights(), this.getConstraints().getPopulationType(), this.getConstraints().getMinorityType(),this.getConstrainedDistrictings().getMeans(),i, this.getConstrainedDistrictings().getPlot().getEnactedDistrictingData());
        }

        //sort the stuff in the constrained districtings by OF score first
        this.getConstrainedDistrictings().getDistrictings().sort(Comparator.comparing(Districting::getObjectiveFunctionScore));
        ArrayList<Districting> tempDistrictingByOF = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            System.out.println("Objective function score values: " + this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1).getObjectiveFunctionScore());
            tempDistrictingByOF.add(this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1));
        }
        this.setTopDistrictingsByOFScore(tempDistrictingByOF);

        this.getConstrainedDistrictings().getDistrictings().sort(Comparator.comparing(Districting::getNumberOfMajorityMinorityDistricts));
        ArrayList<Districting> tempDistrictingByMM = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            System.out.println("Number of MM Districts " + this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1).getNumberOfMajorityMinorityDistricts());
            tempDistrictingByMM.add(this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1));
        }

        this.setTopDistrictingsByHighScoreMajMinDistricts(tempDistrictingByMM);

        this.getConstrainedDistrictings().getDistrictings().sort(Comparator.comparing((Districting::getDeviationFromEnactedPop)));
        ArrayList<Districting> tempDistrctingByEnactedPop = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            System.out.println("Deviation from enacted " + this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1).getDeviationFromEnactedPop());
            tempDistrctingByEnactedPop.add(this.constrainedDistrictings.getDistrictings().get(this.constrainedDistrictings.getDistrictings().size() - i - 1));
        }
        this.setTopDistrictingsByEnacted(tempDistrctingByEnactedPop);

        //area pair deviation will select 10 random districtings and return them
        ArrayList<Districting> tempDistrictingByAreaDev = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            tempDistrictingByAreaDev.add(this.constrainedDistrictings.getDistrictings().get(new Random().nextInt(this.constrainedDistrictings.getDistrictings().size())));
        }
        this.setTopDistrictingsByAreaPairDeviation(tempDistrictingByAreaDev);

    }



    //STEP 1 OF FITLER, ONLY NEEDS DISTRICTING INFORMATION
    public void filterPopEqualityDistrictings(){
        int counter = 0;
        PopulationType populationType = this.getConstraints().getPopulationType();
        double populationEqualityThres = this.getConstraints().getPopulationEqualityThres();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for (int i = 0; i < this.getDistrictings().size(); i++){
            //User can set between 1% to 10% difference
            if(this.getDistrictings().get(i).getPopulationPercentDifference() < (populationEqualityThres * 10)){ //GOTTA FIX
                //If the population difference is less than the user's input threshold,
                //Then we add it to the constrained Districtings
                tempDistrictings.add(this.getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        System.out.println("filtered out by population Equality: ");
        System.out.println(counter);
        this.setFilteredByPopConstraintCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    //STEP 2  OF FILTER, ONLY NEEDS DISTRICTING INFORMATION
    public void filterCompactnessGraph(){
        int counter = 0;
        double compactnessValue = this.getConstraints().getCompactnessValue();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            double compactness = this.getConstrainedDistrictings().getDistrictings().get(i).getGraphCompactness();
            System.out.println("compactness: " + compactness);
            //The lower the compactness value the better it should be
            //Therefore we delete all the districtings with the compactness value that is greater the user threshold
            if (compactness <= compactnessValue){
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        System.out.println("filtered out by graph Compactness: ");
        System.out.println(counter);
        this.setFilteredByCompactnessCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    //STEP 3 OF FILTER, NEED DISTRICT INFORMATION
    public void filterMajorMinorDistrictings(){
        int counter = 0;
        RaceType race = this.getConstraints().getMinorityType();
        double majorMinorThres = this.getConstraints().getMajorMinorThres();
        int numberOfMMDistricts = this.getConstraints().getNumberOfMajorityMinorityDistricts();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            int number = this.getDistrictings().get(i).calculateNumberOfMajorityMinorityDistricts(race, majorMinorThres);
            if (number >= numberOfMMDistricts){
                this.getConstrainedDistrictings().getDistrictings().get(i).setNumberOfMajorityMinorityDistricts(number);
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
                //System.out.println("temp districting's mm count" + this.getConstrainedDistrictings().getDistrictings().get(i).getNumberOfMajorityMinorityDistricts());
            }
            else{
                counter++;
            }
        }
        System.out.println("filtered out by majority minority: ");
        System.out.println(counter);
        this.setFilteredByMMDCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }

    //FINAL STEP OF FILTER, NEEDS PRECINCT INFORMATION
    public void filterIncumbentProtectDistrictings(){
        int counter = 0;
        HashMap<String, Boolean> incumbentHash = this.getConstraints().getIncumbentValue();
        ArrayList<String> protectedIncumbents = new ArrayList<>();
        ArrayList<Districting> tempDistrictings = new ArrayList<>();
        for(String o:incumbentHash.keySet()){
            if(incumbentHash.get(o).equals(true)){
                protectedIncumbents.add(o);
            }
        }
        System.out.println("List of incumbents:" + protectedIncumbents);
        for (int i = 0; i < this.getConstrainedDistrictings().getDistrictings().size(); i++){
            if (this.getConstrainedDistrictings().getDistrictings().get(i).calculateIncumbentDistricts(protectedIncumbents) == false){
                tempDistrictings.add(this.getConstrainedDistrictings().getDistrictings().get(i));
            }
            else{
                counter++;
            }
        }
        System.out.println("filtered out by IncumbentProtection: ");
        System.out.println(counter);
        this.setFilteredByIncumbentCount(counter);
        this.getConstrainedDistrictings().setDistrictings(tempDistrictings);
    }


}
