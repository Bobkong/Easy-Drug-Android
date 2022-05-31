package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easydrug.R;
import com.example.easydrug.Utils.SpeechUtil;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.adapter.DrugInteractionAdapter;
import com.example.easydrug.model.DrugDetail;
import com.example.easydrug.widget.ExpandTextView;
import com.example.easydrug.widget.OneButtonDialog;
import com.githang.statusbar.StatusBarCompat;

public class DrugDetailActivity extends Activity {

    private ExpandTextView drugDescription;
    private ImageView back;
    private RecyclerView interactionList;
    private DrugInteractionAdapter interactionAdapter;
    private TextView drugName;
    private ConstraintLayout addToList;
    private ImageView drugImage;
    private TextView disclaimer;
    private ImageView descriptionSpeaker;
    private ConstraintLayout noDrugView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        noDrugView = findViewById(R.id.no_drug_view);

        Intent intent = getIntent();
        String name = intent.getStringExtra("drugName");
        String description = intent.getStringExtra("drugDescription");
        String imageUrl = intent.getStringExtra("drugImage");

        DrugDetail drugDetail = (DrugDetail) intent.getSerializableExtra("drugDetail");
        if (drugDetail.getDrugDetailContent().isDrugListEmpty()) {
            noDrugView.setVisibility(View.VISIBLE);
        } else {
            noDrugView.setVisibility(View.GONE);
        }

        if (drugDetail.getDrugDetailContent().getDrugInteractions().isEmpty()) {
        }

        drugDescription = findViewById(R.id.description_content);
        int width = UIUtils.getWidth(this) - UIUtils.dp2px(this, 72);
        drugDescription.initWidth(width);
        drugDescription.setMaxLines(7);
        drugDescription.setCloseText(description);

        descriptionSpeaker = findViewById(R.id.description_speaker);
        descriptionSpeaker.setOnClickListener(v -> {
            SpeechUtil.speechText(DrugDetailActivity.this, description);
        });

        drugName = findViewById(R.id.drug_name);
        drugName.setText(name);

        drugImage = findViewById(R.id.drug_image);
        Glide.with(this).load(imageUrl).into(drugImage);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            finish();
        });

//        interactionList = findViewById(R.id.drug_interaction_list);
//        interactionList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        interactionAdapter = new DrugInteractionAdapter(drugDetail.getDrugDetailContent().getDrugInteractions());
//        interactionList.setAdapter(interactionAdapter);

        addToList = findViewById(R.id.add_to_list);
        addToList.setOnClickListener(v -> {
            new OneButtonDialog(DrugDetailActivity.this, () -> {
                // todo go to drug list screen

            }).setTitle("Successfully added to list!")
            .setStatusImgRes(R.drawable.dialog_correct)
            .show();
        });

        disclaimer = findViewById(R.id.disclaimer);
        String disclaimerText = getResources().getString(R.string.disclaimer);
        SpannableString span = new SpannableString(disclaimerText);
        span.setSpan(new StyleSpan(Typeface.BOLD), 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        disclaimer.setText(span);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpeechUtil.destroy();
    }
}
