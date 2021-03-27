package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TreasureHuntInformationViewHolder extends RecyclerView.ViewHolder {

    private TextView descriptionTv;
    private TextView indicationTv;
    private TextView statusTv;

    public TreasureHuntInformationViewHolder(@NonNull View itemView){
        super(itemView);
        initializeViews(itemView);
    }

    private void initializeViews(View itemView) {
        descriptionTv = itemView.findViewById(R.id.tv_row_information_description);
        indicationTv = itemView.findViewById(R.id.tv_row_information_indication);
        statusTv = itemView.findViewById(R.id.tv_row_information_status);
    }

    public void setValues(String status, String description, String clue){
        descriptionTv.setText(description);
        indicationTv.setText(clue);
        statusTv.setText(status);
    }
}
