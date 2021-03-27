package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.TreasureHuntModel;

import java.util.ArrayList;

public class TreasureHuntAdapter extends RecyclerView.Adapter<TreasureHuntViewHolder> {
    private ArrayList<TreasureHuntModel> treasureHuntList;

    public TreasureHuntAdapter(ArrayList<TreasureHuntModel> treasureHuntList) {
        this.treasureHuntList = treasureHuntList;
    }

    @NonNull
    @Override
    public TreasureHuntViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.row_treasure_hunt, parent, false);
        TreasureHuntViewHolder viewHolder = new TreasureHuntViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TreasureHuntViewHolder holder, int position) {
        TreasureHuntModel treasureHunt = treasureHuntList.get(position);
        holder.setValues(treasureHunt.getName(), treasureHunt.getType(), treasureHunt.isStarted());
        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return treasureHuntList.size();
    }
}
