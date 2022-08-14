package com.example.luntian.model;

public class RemarkModel {
    String RemarkDate;
    String RemarkTxt;
    public RemarkModel() {
    }
    public RemarkModel(String remarkDate, String remarkTxt) {
        RemarkDate = remarkDate;
        RemarkTxt = remarkTxt;
    }

    public String getRemarkDate() {
        return RemarkDate;
    }

    public void setRemarkDate(String remarkDate) {
        RemarkDate = remarkDate;
    }

    public String getRemarkTxt() {
        return RemarkTxt;
    }

    public void setRemarkTxt(String remarkTxt) {
        RemarkTxt = remarkTxt;
    }
}






