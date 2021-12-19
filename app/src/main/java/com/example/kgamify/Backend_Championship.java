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

    @SerializedName("total_coins")
    @Expose
    private int total_coins;

    @SerializedName("isCategoryActive")
    @Expose
    private int isCategoryActive;

    @SerializedName("number_of_participants")
    @Expose
    private int number_of_participants;

    @SerializedName("number_of_questions")
    @Expose
    private int number_of_questions;

    @SerializedName("total_time")
    @Expose
    private int total_time;


    public Backend_Championship(String category, String label, String championship, String description, String qualification, String start_time, String end_time, int total_coins, int isCategoryActive, int number_of_participants,int number_of_questions,int total_time) {
        this.category = category;
        this.Label = label;
        this.championship = championship;
        this.description = description;
        this.qualification = qualification;
        this.start_time = start_time;
        this.total_coins = total_coins;
        this.isCategoryActive = isCategoryActive;
        this.number_of_participants = number_of_participants;
        this.number_of_questions=number_of_questions;
        this.total_time=total_time;
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


    public int getTotal_coins() {
        return total_coins;
    }

    public void setTotal_coins(int total_coins) {
        this.total_coins = total_coins;
    }

    public int getIsCategoryActive() {
        return isCategoryActive;
    }

    public void setIsCategoryActive(int isCategoryActive) {
        this.isCategoryActive = isCategoryActive;
    }

    public int getNumber_of_participants() {
        return number_of_participants;
    }

    public void setNumber_of_participants(int number_of_participants) {
        this.number_of_participants = number_of_participants;
    }

    public int getNumber_of_questions() {
        return number_of_questions;
    }

    public void setNumber_of_questions(int number_of_questions) {
        this.number_of_questions = number_of_questions;
    }

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }
}
