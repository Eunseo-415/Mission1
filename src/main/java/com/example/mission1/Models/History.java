package com.example.mission1.Models;

public class History {
    public int id;
    public double lat;
    public double lnt;
    public String date_time;

    public History( double lat, double lnt, String date_time) {
        this.lat = lat;
        this.lnt = lnt;
        this.date_time = date_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLnt() {
        return lnt;
    }

    public void setLnt(double lnt) {
        this.lnt = lnt;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }
}
