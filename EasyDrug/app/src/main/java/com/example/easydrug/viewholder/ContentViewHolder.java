package com.example.easydrug.viewholder;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.easydrug.Configs;
import com.example.easydrug.R;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.adapter.ContentAdapter;
import com.example.easydrug.adapter.TagAdapter;
import com.example.easydrug.model.ResourcesContent;

public class ContentViewHolder extends RecyclerView.ViewHolder {
//    private TextView tagName;
    private ContentAdapter adapter;
    private int position;
    private String TAG = "ContentViewHolder";
    private TextView title, desc, isNew, contentType;
    private ImageView contentImage;
    private Activity activity;

    public ContentViewHolder(@NonNull View itemView) {
        super(itemView);
//        tagName = itemView.findViewById(R.id.tag_name);
        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);
        contentImage = itemView.findViewById(R.id.content_image);
        isNew = itemView.findViewById(R.id.is_new_id);
        contentType = itemView.findViewById(R.id.content_type);
    }

    public void setData(ResourcesContent content, ContentAdapter adapter, int position, Activity activity) {
        title.setText(content.getName());
        desc.setText(content.getDesc());
//        Glide.with(activity).load(content.getImage_url()).into(contentImage);
        Glide.with(activity)
                .load(content.getImage_url())
                .apply(new RequestOptions().placeholder(R.drawable.defaultimage))
                .into(contentImage);
        this.adapter = adapter;
        this.position = position;
        if (content.isIs_new()) {
            isNew.setVisibility(View.VISIBLE);
        }
        contentType.setText(content.getType());
        if (content.isIs_new()) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) this.contentType.getLayoutParams();
            params.leftMargin = UIUtils.dp2px(activity, 8f);
        }

    }


//    public String getIngredientName() {
////        return tagName.getText().toString();
//    }
}
