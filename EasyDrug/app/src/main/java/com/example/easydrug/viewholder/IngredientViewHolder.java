package com.example.easydrug.viewholder;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.adapter.IngredientAdapter;

public class IngredientViewHolder  extends RecyclerView.ViewHolder {
    private TextView ingredientName;
    private ImageView deleteButton;
    private int EDIT_OK = 0;
    private IngredientAdapter adapter;
    private int position;
    private String TAG = "IngredientViewHolder";

    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        ingredientName = itemView.findViewById(R.id.ingredient_name);
        deleteButton = itemView.findViewById(R.id.delete);

        ingredientName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            //输入时的调用
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                Log.i(TAG, "after text changed");
                if (adapter != null && !ingredientName.getText().toString().equals(adapter.getIngredient(position))) {
                    adapter.updateIngredient(position, ingredientName.getText().toString());
                }
            }
        });


        deleteButton.setOnClickListener(v -> {
            if (adapter != null) {
                adapter.deleteItem(position);
            }
        });
    }

    public void setData(String ingredient, IngredientAdapter adapter, int position) {
        ingredientName.setText(ingredient);
        this.adapter = adapter;
        this.position = position;
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (EDIT_OK == msg.what && adapter != null) {
                adapter.updateIngredient(position, ingredientName.getText().toString());
            }
        }
    };

    private final Runnable mRunnable = () -> mHandler.sendEmptyMessage(EDIT_OK);
}
