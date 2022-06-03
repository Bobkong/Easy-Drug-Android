package com.example.easydrug.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.model.ResourcesContent;
import com.example.easydrug.viewholder.ContentViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class ContentAdapter extends RecyclerView.Adapter<ContentViewHolder>{
    private ArrayList<HashMap<String,String>> contentData;
    private Activity activity;
    private ArrayList<ResourcesContent> resource_list;

    public ContentAdapter(ArrayList<ResourcesContent> resource_list, Activity activity) {
        this.resource_list = resource_list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_explore, parent, false);
        return new ContentViewHolder(v);
    }
    @Override
    public int getItemCount() {
        return contentData.size();
    }
    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int position) {
        holder.setData(resource_list.get(position), this, position, activity);
    }
}
