package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.helpers.ITreasureHuntStart;
import com.example.myapplication.models.TreasureHuntModel;

import java.util.ArrayList;

public class TreasureHuntAdapter extends RecyclerView.Adapter<TreasureHuntViewHolder> {
    private ArrayList<TreasureHuntModel> treasureHuntList;
    private ITreasureHuntStart treasureHuntStart;

    public TreasureHuntAdapter(ArrayList<TreasureHuntModel> treasureHuntList, ITreasureHuntStart treasureHuntStart) {
        this.treasureHuntList = treasureHuntList;
        this.treasureHuntStart = treasureHuntStart;
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
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                treasureHunt.setStarted(true);
                holder.setValues(treasureHunt.getName(), treasureHunt.getType(), true);
                treasureHuntStart.startTreasureHunt(treasureHunt.getType());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return treasureHuntList.size();
    }
}
