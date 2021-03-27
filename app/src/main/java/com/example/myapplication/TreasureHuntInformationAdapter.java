package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.helpers.ITreasureHuntStart;
import com.example.myapplication.models.TreasureHuntItemModel;
import com.example.myapplication.models.TreasureHuntModel;
import com.here.sdk.core.GeoCoordinates;

import java.util.ArrayList;

public class TreasureHuntInformationAdapter extends RecyclerView.Adapter<TreasureHuntInformationViewHolder> {
    private ArrayList<TreasureHuntItemModel> itemsList;
    private String status;
    private String placeDescription;
    private String nextClue;
    private GeoCoordinates coordinates;

    public TreasureHuntInformationAdapter(ArrayList<TreasureHuntItemModel> itemsList){
        this.itemsList = itemsList;
    }

    @NonNull
    @Override
    public TreasureHuntInformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View contactView = inflater.inflate(R.layout.row_information, parent, false);
        TreasureHuntInformationViewHolder viewHolder = new TreasureHuntInformationViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TreasureHuntInformationViewHolder holder, int position) {
        TreasureHuntItemModel treasureHunt = itemsList.get(position);
        status = (position+1) + "/" + itemsList.size();
        holder.setValues(status, treasureHunt.getPlaceDescription(), treasureHunt.getNextClue());
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }
}
