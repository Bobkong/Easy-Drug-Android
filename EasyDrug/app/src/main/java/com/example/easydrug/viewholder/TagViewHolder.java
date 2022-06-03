package com.example.easydrug.viewholder;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easydrug.R;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.adapter.TagAdapter;

public class TagViewHolder extends RecyclerView.ViewHolder{
    private TagAdapter adapter;
    private int position;
    private String TAG = "TagViewHolder";
    private TextView title;

    public TagViewHolder(@NonNull View itemView) {
        super(itemView);
//        tagName = itemView.findViewById(R.id.tag_name);
        title = itemView.findViewById(R.id.tag_name);
    }

    public void setData(String title, TagAdapter adapter, int position, Context context) {
        this.title.setText(title);
        this.adapter = adapter;
        this.position = position;
        if (position == 0) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) this.title.getLayoutParams();
            params.leftMargin = UIUtils.dp2px(context, 16f);
        }
    }
}


