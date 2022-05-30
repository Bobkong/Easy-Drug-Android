package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.adapter.DrugInteractionAdapter;
import com.example.easydrug.model.DrugDetail;
import com.example.easydrug.widget.ExpandTextView;
import com.githang.statusbar.StatusBarCompat;

public class DrugDetailActivity extends Activity {

    private ExpandTextView drugDescription;
    private ImageView back;
    private RecyclerView interactionList;
    private DrugInteractionAdapter interactionAdapter;
    private TextView drugName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        Intent intent = getIntent();
        String name = intent.getStringExtra("drugName");
        String description = intent.getStringExtra("drugDescription");
        DrugDetail drugDetail = (DrugDetail) intent.getSerializableExtra("drugDetail");

        drugDescription = findViewById(R.id.description_content);
        int width = UIUtils.getWidth(this) - UIUtils.dp2px(this, 72);
        drugDescription.initWidth(width);
        drugDescription.setMaxLines(7);
        drugDescription.setCloseText(description);

        drugName = findViewById(R.id.drug_name);
        drugName.setText(name);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });

        interactionList = findViewById(R.id.drug_interaction_list);
        interactionList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        interactionAdapter = new DrugInteractionAdapter(drugDetail.getDrugDetailContent().getDrugInteractions());
        interactionList.setAdapter(interactionAdapter);
    }
}
