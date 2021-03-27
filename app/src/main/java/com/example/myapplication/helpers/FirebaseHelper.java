package com.example.myapplication.helpers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseHelper {
    private static FirebaseHelper firebaseHelper;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference usersDatabase = firebaseDatabase.getReference("users");
    public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    public FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

    public static FirebaseHelper getInstance()
    {
        if(firebaseHelper == null)
        {
            firebaseHelper = new FirebaseHelper();
        }
        return firebaseHelper;
    }

    public FirebaseHelper(){

    }
}
