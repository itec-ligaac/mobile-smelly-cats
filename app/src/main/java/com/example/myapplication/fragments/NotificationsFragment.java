package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.TreasureHuntAdapter;
import com.example.myapplication.helpers.ITreasureHuntStart;
import com.example.myapplication.models.TreasureHuntModel;
import com.example.myapplication.models.TreasureHuntType;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    private RecyclerView treasureHuntsRv;
    private ArrayList<TreasureHuntModel> treasureHuntsList;
    private TreasureHuntAdapter treasureHuntAdapter;
    private ITreasureHuntStart treasureHuntStart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        initializeViews(root);
        setMockers();
        treasureHuntStart = new ITreasureHuntStart() {
            @Override
            public void startTreasureHunt(int type) {
                int a = type;
            }
        };
        setRecyclerView();
        return root;
    }

    private void setMockers() {
        treasureHuntsList = new ArrayList<>();
        treasureHuntsList.add(new TreasureHuntModel("Cool stuff", TreasureHuntType.CULTURE, true));
        treasureHuntsList.add(new TreasureHuntModel("Recreations", TreasureHuntType.PARKS, false));
        treasureHuntsList.add(new TreasureHuntModel("AIurea mall", TreasureHuntType.SHOPPING, false));
    }

    private void initializeViews(View root) {
        treasureHuntsRv = root.findViewById(R.id.rv_treasure_hunts);
    }

    private void setRecyclerView() {
        treasureHuntsRv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        treasureHuntAdapter = new TreasureHuntAdapter(treasureHuntsList, treasureHuntStart);
        treasureHuntsRv.setAdapter(treasureHuntAdapter);
    }
}