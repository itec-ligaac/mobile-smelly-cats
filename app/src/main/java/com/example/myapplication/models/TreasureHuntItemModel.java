package com.example.myapplication.models;

import com.here.sdk.core.GeoCoordinates;

public class TreasureHuntItemModel {
    private int id;
    private String placeDescription;
    private String nextClue;
    private GeoCoordinates coordinates;

    public TreasureHuntItemModel(int id, String placeDescription, String nextClue, GeoCoordinates coordinates) {
        this.id = id;
        this.placeDescription = placeDescription;
        this.nextClue = nextClue;
        this.coordinates = coordinates;
    }

    public GeoCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(GeoCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public String getNextClue() {
        return nextClue;
    }

    public void setNextClue(String nextClue) {
        this.nextClue = nextClue;
    }
}
