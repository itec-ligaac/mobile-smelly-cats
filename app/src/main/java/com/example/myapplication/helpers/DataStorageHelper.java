package com.example.myapplication.helpers;

import com.example.myapplication.models.TreasureHuntItemModel;
import com.example.myapplication.models.UserModel;
import com.here.sdk.core.GeoCoordinates;

import java.util.ArrayList;

public class DataStorageHelper {
    private static DataStorageHelper instance;
    private UserModel currentUser;

    public static DataStorageHelper getInstance()
    {
        if(instance == null)
        {
            instance = new DataStorageHelper();
        }
        return instance;
    }

    public void setCurrentUser(UserModel user){
        currentUser = user;
    }

    public UserModel getCurrentUser(){
        return currentUser;
    }

    public ArrayList<TreasureHuntItemModel> cultureHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();
        list.add(new TreasureHuntItemModel(100, "Descriere", "Indiciu", new GeoCoordinates(45.323, 21.23)));
        return list;
    }

    public ArrayList<TreasureHuntItemModel> outdoorHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();

        return list;
    }

    public ArrayList<TreasureHuntItemModel> entertainmentHuntList(){
        ArrayList<TreasureHuntItemModel> list = new ArrayList<>();

        return list;
    }
}
