package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Backend_Championship {

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("Label")
    @Expose
    private String Label;

    @SerializedName("championship")
    @Expose
    private String championship;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("qualification")
    @Expose
    private String qualification;

    @SerializedName("start_time")
    @Expose
    private String start_time;

    @SerializedName("end_time")
    @Expose
    private String end_time;

    @SerializedName("total_coins")
    @Expose
    private String total_coins;

    @SerializedName("isCategoryActive")
    @Expose
    private int isCategoryActive;

    @SerializedName("number_of_participants")
    @Expose
    private String number_of_participants;


    public Backend_Championship(String category, String label, String championship, String description, String qualification, String start_time, String end_time, String total_coins, int isCategoryActive, String number_of_participants) {
        this.category = category;
        Label = label;
        this.championship = championship;
        this.description = description;
        this.qualification = qualification;
        this.start_time = start_time;
        this.end_time = end_time;
        this.total_coins = total_coins;
        this.isCategoryActive = isCategoryActive;
        this.number_of_participants = number_of_participants;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getChampionship() {
        return championship;
    }

    public void setChampionship(String championship) {
        this.championship = championship;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTotal_coins() {
        return total_coins;
    }

    public void setTotal_coins(String total_coins) {
        this.total_coins = total_coins;
    }

    public int getIsCategoryActive() {
        return isCategoryActive;
    }

    public void setIsCategoryActive(int isCategoryActive) {
        this.isCategoryActive = isCategoryActive;
    }

    public String getNumber_of_participants() {
        return number_of_participants;
    }

    public void setNumber_of_participants(String number_of_participants) {
        this.number_of_participants = number_of_participants;
    }
}
