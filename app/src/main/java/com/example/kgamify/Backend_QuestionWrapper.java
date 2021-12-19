package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Backend_QuestionWrapper {

    @SerializedName("Items")
    @Expose
    private List<Backend_Question> questions=null;

    @SerializedName("Count")
    @Expose
    private String Count;

    @SerializedName("ScannedCount")
    @Expose
    private String ScannedCount;

    public Backend_QuestionWrapper(List<Backend_Question> questions, String count, String scannedCount) {
        this.questions = questions;
        Count = count;
        ScannedCount = scannedCount;
    }

    public List<Backend_Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Backend_Question> questions) {
        this.questions = questions;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getScannedCount() {
        return ScannedCount;
    }

    public void setScannedCount(String scannedCount) {
        ScannedCount = scannedCount;
    }
}
