package com.example.easydrug.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.model.ResourcesContent;
import com.example.easydrug.viewholder.ResourceContentViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class ContentAdapter extends RecyclerView.Adapter<ResourceContentViewHolder>{
    private Activity activity;
    private ArrayList<ResourcesContent> contentData;

    public ContentAdapter(ArrayList<ResourcesContent> contentData, Activity activity) {
        this.contentData = contentData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ResourceContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content, parent, false);
        return new ResourceContentViewHolder(v);
    }
    @Override
    public int getItemCount() {
        return contentData.size();
    }
    @Override
    public void onBindViewHolder(@NonNull ResourceContentViewHolder holder, int position) {
        holder.setData(contentData.get(position), this, position, activity);
    }
}
