package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myapplication.Activities.LoginActivity;
import com.example.myapplication.Activities.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.helpers.DataStorageHelper;
import com.example.myapplication.models.UserModel;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        setValues(DataStorageHelper.getInstance().getCurrentUser());
        initializeViews(root);
        return root;
    }

    private void initializeViews(View root) {
    }

    private void setValues(UserModel currentUser) {
    }

    public void OnLogOut(View view) {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}