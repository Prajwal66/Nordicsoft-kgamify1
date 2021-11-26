package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Backend_Championship {

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("isCategoryActive")
    @Expose
    private int isCategoryActive;

    @SerializedName("Label")
    @Expose
    private String Label;

    @SerializedName("championship")
    @Expose
    private String championship;

    @SerializedName("description")
    @Expose
    private String description;



    public Backend_Championship(String category, int isCategoryActive, String label, String championship, String description) {
        this.category = category;
        this.isCategoryActive = isCategoryActive;
        Label = label;
        this.championship = championship;
        this.description = description;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIsCategoryActive() {
        return isCategoryActive;
    }

    public void setIsCategoryActive(int isCategoryActive) {
        this.isCategoryActive = isCategoryActive;
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
}
