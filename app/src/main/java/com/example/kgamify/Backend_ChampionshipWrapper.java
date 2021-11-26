package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Backend_ChampionshipWrapper {
    @SerializedName("Items")
    @Expose
    private List<Backend_Championship> Items=null;

    @SerializedName("Count")
    @Expose
    private int Count;

    @SerializedName("ScannedCount")
    @Expose
    private int ScannedCount;


    public Backend_ChampionshipWrapper(List<Backend_Championship> items, int count, int scannedCount) {
        Items = items;
        Count = count;
        ScannedCount = scannedCount;
    }

    public List<Backend_Championship> getItems() {
        return Items;
    }

    public void setItems(List<Backend_Championship> items) {
        Items = items;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getScannedCount() {
        return ScannedCount;
    }

    public void setScannedCount(int scannedCount) {
        ScannedCount = scannedCount;
    }
}
