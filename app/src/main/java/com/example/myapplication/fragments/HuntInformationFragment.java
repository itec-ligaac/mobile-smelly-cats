package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.TreasureHuntAdapter;
import com.example.myapplication.TreasureHuntInformationAdapter;
import com.example.myapplication.helpers.DataStorageHelper;
import com.example.myapplication.models.TreasureHuntItemModel;

import java.util.ArrayList;

public class HuntInformationFragment extends Fragment {

    private RecyclerView recyclerView;
    private TreasureHuntInformationAdapter informationAdapter;
    private ArrayList<TreasureHuntItemModel> infoList;
    private TextView infoTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_hunt_information, container, false);
        initializeViews(root);
        setRecyclerView();
        return root;
    }

    private void initializeViews(View root) {
        infoTv = root.findViewById(R.id.tv_treasure_hunt_info);
        recyclerView = root.findViewById(R.id.rv_treasure_hunt_info);
    }

    private void setRecyclerView(){
        infoList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        informationAdapter = new TreasureHuntInformationAdapter(infoList);
        recyclerView.setAdapter(informationAdapter);
    }

    public void addHuntInfo(int position) {
        infoTv.setVisibility(View.GONE);
        infoList.add(position, DataStorageHelper.getInstance().outdoorHuntList().get(position));
        informationAdapter.notifyDataSetChanged();
    }
}