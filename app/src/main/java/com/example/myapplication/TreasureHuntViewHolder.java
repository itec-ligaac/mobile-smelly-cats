package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.models.TreasureHuntType;
import com.google.android.material.card.MaterialCardView;

public class TreasureHuntViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTv;
    private TextView typeTv;
    private MaterialCardView containerMcv;
    private ImageView iconIv;
    public TextView statusTv;

    public TreasureHuntViewHolder(@NonNull View itemView) {
        super(itemView);
        initializeViews(itemView);
    }

    private void initializeViews(View itemView) {
        nameTv = itemView.findViewById(R.id.tv_row_treasure_hunt_name);
        typeTv = itemView.findViewById(R.id.tv_row_treasure_hunt_type);
        statusTv = itemView.findViewById(R.id.tv_row_treasure_hunt_status);
        containerMcv = itemView.findViewById(R.id.mcv_row_container);
        iconIv = itemView.findViewById(R.id.iv_row_treasure_hunt_logo);
    }

    public void setValues(String name, int type, boolean isStarted){
        nameTv.setText(name);
        switch (type){
            case TreasureHuntType.CULTURE:
                typeTv.setText("Museums");
                nameTv.setText("Culture");
                iconIv.setImageResource(R.drawable.ic_culture);
                break;
            case TreasureHuntType.PARKS:
                typeTv.setText("Parks");
                nameTv.setText("Outdoors");
                iconIv.setImageResource(R.drawable.ic_outdoor);
                break;
            case TreasureHuntType.SHOPPING:
                typeTv.setText("Shops, Restaurants,Bars");
                nameTv.setText("Entertainment");
                iconIv.setImageResource(R.drawable.ic_entertainment);
                break;
            default:
                typeTv.setText("Default");
                break;
        }
        if (isStarted){
            statusTv.setText("In Progress");
            statusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.pendingColor));
            containerMcv.setStrokeColor(itemView.getContext().getResources().getColor(R.color.pendingColor));
            return;
        }
        statusTv.setText("Start");
        statusTv.setTextColor(itemView.getContext().getResources().getColor(R.color.inProgress));
        containerMcv.setStrokeColor(itemView.getContext().getResources().getColor(R.color.inProgress));
    }
}
