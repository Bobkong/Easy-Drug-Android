package com.example.easydrug.adapter;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.model.FoodInteraction;
import com.example.easydrug.viewholder.FoodInteractionViewHolder;

import java.util.ArrayList;

public class FoodInteractionAdapter extends RecyclerView.Adapter<FoodInteractionViewHolder> {
    private Activity activity;
    private ArrayList<FoodInteraction> mData;
    @NonNull
    @Override
    public FoodInteractionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodInteractionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public FoodInteractionAdapter(Activity activity, ArrayList<FoodInteraction> interactions) {
        this.activity = activity;
        this.mData = interactions;
    }
}
