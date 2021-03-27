package com.example.myapplication.helpers;

import com.example.myapplication.models.UserModel;

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
}
