package com.example.luntian.model;

public class TrackModel {


    String ID, Image, PlantName, DatePlanted, WaterFrequency1, WaterFrequency2, FertilizerFreq1, FertilizerFreq2;

    TrackModel(){

    }
    public String getId() {
        return ID;
    }

    public void setId(String id) {
        this.ID = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPlantName() {
        return PlantName;
    }

    public void setPlantName(String plantName) {
        PlantName = plantName;
    }

    public String getDatePlanted() {
        return DatePlanted;
    }

    public void setDatePlanted(String datePlanted) {
        DatePlanted = datePlanted;
    }

    public String getWaterFrequency1() {
        return WaterFrequency1;
    }

    public void setWaterFrequency1(String waterFrequency1) {
        WaterFrequency1 = waterFrequency1;
    }

    public String getWaterFrequency2() {
        return WaterFrequency2;
    }

    public void setWaterFrequency2(String waterFrequency2) {
        WaterFrequency2 = waterFrequency2;
    }

    public String getFertilizerFreq1() {
        return FertilizerFreq1;
    }

    public void setFertilizerFreq1(String fertilizerFreq1) {
        FertilizerFreq1 = fertilizerFreq1;
    }

    public String getFertilizerFreq2() {
        return FertilizerFreq2;
    }

    public void setFertilizerFreq2(String fertilizerFreq2) {
        FertilizerFreq2 = fertilizerFreq2;
    }

    public TrackModel(String plantImg, String plantName, String plantDate, String plantWF1, String plantWF2, String plantFF1, String plantFF2){
        Image = plantImg;
        PlantName = plantName;
        DatePlanted = plantDate;
        WaterFrequency1 = plantWF1;
        WaterFrequency2 = plantWF2;
        FertilizerFreq1 = plantFF1;
        FertilizerFreq2 = plantFF2;


    }
}
