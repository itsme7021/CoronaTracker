package io.springProject.CoronaVirus_Tracker.model;

public class LocationStats {
    private String state;
    private String country;
    private int recordstillnow;
    private int diffFromLastDay;
    private int newCases;

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getRecordstillnow() {
        return recordstillnow;
    }

    public void setRecordstillnow(int recordstillnow) {
        this.recordstillnow = recordstillnow;
    }

    public int getDiffFromLastDay() {
        return diffFromLastDay;
    }

    public void setDiffFromLastDay(int diffFromLastDay) {
        this.diffFromLastDay = diffFromLastDay;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", recordstillnow='" + recordstillnow + '\'' +
                '}';
    }
}
