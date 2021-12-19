package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Backend_Category {

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("count")
    @Expose
    private int count;

    public Backend_Category(String category,int count) {
        this.category = category;
        this.count=count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
