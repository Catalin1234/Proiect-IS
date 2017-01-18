package com.is.model;

/**
 * Created by aaldea on 8/3/2016.
 */
public class Rating {
    private int trainingId;
    private int numberOfSubmits;
    private float overall;

    public Rating(int trainingId, int numberOfSubmits, float overall) {
        this.trainingId = trainingId;
        this.numberOfSubmits = numberOfSubmits;
        this.overall = overall;
    }

    public Rating() {
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getNumberOfSubmits() {
        return numberOfSubmits;
    }

    public void setNumberOfSubmits(int numberOfSubmits) {
        this.numberOfSubmits = numberOfSubmits;
    }

    public float getOverall() {
        return overall;
    }

    public void setOverall(float overall) {
        this.overall = overall;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "trainingId=" + trainingId +
                ", numberOfSubmits=" + numberOfSubmits +
                ", overall=" + overall +
                '}';
    }
}
