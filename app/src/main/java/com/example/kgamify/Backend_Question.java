package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Backend_Question {

    @SerializedName("coins")
    @Expose
    private String coins;

    @SerializedName("Label")
    @Expose
    private String Label;

    @SerializedName("questionID")
    @Expose
    private String questionID;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("option4")
    @Expose
    private String option4;

    @SerializedName("option3")
    @Expose
    private String option3;

    @SerializedName("option2")
    @Expose
    private String option2;

    @SerializedName("option1")
    @Expose
    private String option1;

    @SerializedName("answer")
    @Expose
    private String answer;

    public Backend_Question(String coins, String Label, String questionID, String question, String option4, String option3, String option2, String option1, String answer) {
        this.coins = coins;
        this.Label = Label;
        this.questionID = questionID;
        this.question = question;
        this.option4 = option4;
        this.option3 = option3;
        this.option2 = option2;
        this.option1 = option1;
        this.answer = answer;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        this.Label = Label;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
