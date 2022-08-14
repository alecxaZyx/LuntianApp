package com.example.luntian;

public class Reminder {
    String t;
    String d;
    String tm;
    String dt;

    public Reminder(String t, String d, String tm, String dt) {
        this.t = t;
        this.d = d;
        this.tm = tm;
        this.dt = dt;
    }

    public String gett() {
        return t;
    }

    public String getd() {
        return d;
    }

    public String gettm() {
        return tm;
    }

    public String getdt() {
        return dt;
    }

    public Reminder() {
    }
}
