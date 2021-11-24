package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginsWrapper {
    public LoginsWrapper(List<Logins> logins) {
        this.logins = logins;
    }

    @SerializedName("logins")
    @Expose

    private List<Logins> logins=null;

    public List<Logins> getLogins(){
        return logins;
    }

    public void setLogins(List<Logins> logins){
        this.logins=logins;
    }
}