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

public class ProfileFragment extends Fragment {

    private TextView nameTv;
    private TextView emailTv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initializeViews(root);
        setValues(DataStorageHelper.getInstance().getCurrentUser());
        return root;
    }

    private void initializeViews(View root) {
        emailTv = root.findViewById(R.id.tv_email_export);
        nameTv = root.findViewById(R.id.tv_full_name_export);
    }

    private void setValues(UserModel currentUser) {
        emailTv.setText(currentUser.getEmail());
        nameTv.setText(currentUser.getNume());
    }

    public void OnLogOut(View view) {
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}