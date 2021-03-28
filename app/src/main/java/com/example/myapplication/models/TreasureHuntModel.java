package com.example.myapplication.models;

public class TreasureHuntModel {
    private String name;
    private int type;
    private boolean started;

    public TreasureHuntModel(String name, int type, boolean started) {
        this.name = name;
        this.type = type;
        this.started = started;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
}

