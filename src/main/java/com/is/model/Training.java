package com.is.model;

//from sql to util
import java.util.Date;

/**
 * Created by aaldea on 8/3/2016.
 */
public class Training {

    private int trainingId;
    private String trainingName;
    private String trainerName;
    private Date startDate;
    private Date stopDate;
    private String grade;
    private String technology;
    private int numberPeople;

    public Training(int trainingId, String trainingName, String trainerName, Date startDate, Date stopDate, String grade, String technology, int numberPeople) {
        this.trainingId = trainingId;
        this.trainingName = trainingName;
        this.trainerName = trainerName;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.grade = grade;
        this.technology = technology;
        this.numberPeople = numberPeople;
    }

    public Training() {

    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public int getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(int numberPeople) {
        this.numberPeople = numberPeople;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", trainingName='" + trainingName + '\'' +
                ", trainerName='" + trainerName + '\'' +
                ", startDate=" + startDate +
                ", stopDate=" + stopDate +
                ", grade='" + grade + '\'' +
                ", technology='" + technology + '\'' +
                ", numberPeople=" + numberPeople +
                '}';
    }
}
