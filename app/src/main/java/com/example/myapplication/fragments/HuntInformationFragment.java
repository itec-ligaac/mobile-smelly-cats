package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import com.example.myapplication.R;
import com.example.myapplication.TreasureHuntAdapter;
import com.example.myapplication.TreasureHuntInformationAdapter;
import com.example.myapplication.helpers.DataStorageHelper;

public class HuntInformationFragment extends Fragment {

    private RecyclerView recyclerView;
    private TreasureHuntInformationAdapter informationAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hunt_information, container, false);
        initializeViews(root);
        setRecyclerView();
        return root;
    }

    private void initializeViews(View root) {
        recyclerView = root.findViewById(R.id.rv_treasure_hunt_info);
    }

    private void setRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        informationAdapter = new TreasureHuntInformationAdapter(DataStorageHelper.getInstance().outdoorHuntList());
        recyclerView.setAdapter(informationAdapter);
    }
}