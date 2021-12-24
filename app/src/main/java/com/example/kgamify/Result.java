package com.example.kgamify;

public class Result {

    String ans_from_db;
    String selected_option;
    String string_of_selected_option;
    int coins_for_que;
    boolean result;

    public Result(String ans_from_db, String selected_option, String string_of_selected_option,int coins_for_que, boolean result) {
        this.ans_from_db = ans_from_db;
        this.selected_option = selected_option;
        this.string_of_selected_option = string_of_selected_option;
        this.coins_for_que=coins_for_que;
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

    public int getCoins_for_que() {
        return coins_for_que;
    }

    public void setCoins_for_que(int coins_for_que) {
        this.coins_for_que = coins_for_que;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
