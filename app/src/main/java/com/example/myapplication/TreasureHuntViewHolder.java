package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.TreasureHuntType;

public class TreasureHuntViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView typeTv;

    public TreasureHuntViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews(itemView);
    }

    private void initializeViews(View itemView) {
        nameTv = itemView.findViewById(R.id.tv_row_treasure_hunt_name);
        typeTv = itemView.findViewById(R.id.tv_row_treasure_hunt_type);
    }

    public void setValues(String name, int type){
        nameTv.setText(name);
        switch (type){
            case TreasureHuntType.CULTURE:
                typeTv.setText("Culture");
                break;
            case TreasureHuntType.PARKS:
                typeTv.setText("Parks");
                break;
            case TreasureHuntType.SHOPPING:
                typeTv.setText("Shopping");
                break;
            default:
                typeTv.setText("Default");
                break;
        }
    }
}
