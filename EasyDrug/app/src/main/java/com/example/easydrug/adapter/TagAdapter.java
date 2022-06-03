package com.example.easydrug.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.viewholder.TagViewHolder;

import java.util.ArrayList;

public class TagAdapter extends RecyclerView.Adapter<TagViewHolder>{
    private ArrayList<String> tagData;
    private Activity activity;
    public TagAdapter(ArrayList<String> tagData, Activity activity) {
        this.tagData = tagData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public TagViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_explore, parent, false);
        return new TagViewHolder(v);
    }
    @Override
    public int getItemCount() {
        return tagData.size();
    }
    @Override
    public void onBindViewHolder(@NonNull TagViewHolder holder, int position) {
        holder.setData(tagData.get(position) ,this, position, activity);
    }

}
