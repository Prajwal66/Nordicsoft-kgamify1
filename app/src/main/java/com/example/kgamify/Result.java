package com.example.kgamify;

public class Result {

    String ans_from_db;
    String selected_option;
    String string_of_selected_option;
    boolean result;

    public Result(String ans_from_db, String selected_option, String string_of_selected_option, boolean result) {
        this.ans_from_db = ans_from_db;
        this.selected_option = selected_option;
        this.string_of_selected_option = string_of_selected_option;
        this.result = result;
    }

    public String getAns_from_db() {
        return ans_from_db;
    }

    public void setAns_from_db(String ans_from_db) {
        this.ans_from_db = ans_from_db;
    }

    public String getSelected_option() {
        return selected_option;
    }

    public void setSelected_option(String selected_option) {
        this.selected_option = selected_option;
    }

    public String getString_of_selected_option() {
        return string_of_selected_option;
    }

    public void setString_of_selected_option(String string_of_selected_option) {
        this.string_of_selected_option = string_of_selected_option;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
