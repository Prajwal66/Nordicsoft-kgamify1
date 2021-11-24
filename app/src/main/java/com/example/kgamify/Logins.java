package com.example.kgamify;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logins {

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("country_code")
    @Expose
    private String country_code;

    @SerializedName("location_lattitude")
    @Expose
    private Double location_lattitude;

    @SerializedName("location_longitude")
    @Expose
    private Double location_longitude;

    @SerializedName("location_country")
    @Expose
    private String location_country;

    @SerializedName("location_locality")
    @Expose
    private String location_locality;

    @SerializedName("location_address")
    @Expose
    private String location_address;


    public Logins(String phone, String country_code,Double location_latitute,Double location_longitude,String location_country,String location_locality,String location_address) {
        this.phone = phone;
        this.country_code = country_code;
        this.location_lattitude=location_latitute;
        this.location_longitude=location_longitude;
        this.location_country=location_country;
        this.location_locality=location_locality;
        this.location_address=location_address;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public Double getLocation_lattitude() {
        return location_lattitude;
    }

    public void setLocation_lattitude(Double location_lattitude) {
        this.location_lattitude = location_lattitude;
    }

    public Double getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(Double location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getLocation_country() {
        return location_country;
    }

    public void setLocation_country(String location_country) {
        this.location_country = location_country;
    }

    public String getLocation_locality() {
        return location_locality;
    }

    public void setLocation_locality(String location_locality) {
        this.location_locality = location_locality;
    }

    public String getLocation_address() {
        return location_address;
    }

    public void setLocation_address(String location_address) {
        this.location_address = location_address;
    }
}
