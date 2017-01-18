package com.is.model;

/**
 * Created by ctimbus on 8/3/2016.
 */
public class Enrollment {
    private int trainingId;
    private int userId;
    private boolean hasVoted;

    public Enrollment(int trainingId, int userId, boolean hasVoted) {
        this.trainingId = trainingId;
        this.userId = userId;
        this.hasVoted = hasVoted;
    }

    public Enrollment() {

    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "trainingId=" + trainingId +
                ", userId=" + userId +
                ", hasVoted=" + hasVoted +
                '}';
    }
}
