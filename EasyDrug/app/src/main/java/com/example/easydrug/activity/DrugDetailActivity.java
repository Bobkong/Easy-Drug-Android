package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.easydrug.Configs;
import com.example.easydrug.R;
import com.example.easydrug.Utils.FileUtil;
import com.example.easydrug.Utils.SpeechUtil;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.adapter.DrugInteractionAdapter;
import com.example.easydrug.model.DrugDetail;
import com.example.easydrug.model.DrugInteraction;
import com.example.easydrug.model.GeneralResponse;
import com.example.easydrug.model.SideEffectPossibility;
import com.example.easydrug.netservice.Api.DrugService;
import com.example.easydrug.widget.ExpandTextView;
import com.example.easydrug.widget.OneButtonDialog;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class DrugDetailActivity extends Activity {

    private String TAG = "DrugDetailActivity";
    private ExpandTextView drugDescription;
    private ImageView back;
    private RecyclerView interactionList;
    private DrugInteractionAdapter interactionAdapter;
    private TextView drugName;
    private ConstraintLayout addToList;
    private ImageView drugImage;
    private TextView disclaimer;
    private ImageView descriptionSpeaker, interactionSpeaker;
    private ConstraintLayout noDrugView, drugInteractionView, noInteractionView, loadingView, errorView;
    private ImageView addToListImage;
    private TextView refresh;

    private String drugNameString, descriptionString, imageUrl, upc;
    private DrugDetail drugDetail;
    private boolean isFromOnboarding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        noDrugView = findViewById(R.id.no_drug_view);
        drugInteractionView = findViewById(R.id.drug_interaction);
        noInteractionView = findViewById(R.id.no_interaction_view);
        loadingView = findViewById(R.id.loading_view);
        errorView = findViewById(R.id.loading_error_view);
        refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(v -> requestDrugDetail());

        Intent intent = getIntent();
        drugNameString = intent.getStringExtra("drugName");
        descriptionString = intent.getStringExtra("drugDescription");
        imageUrl = intent.getStringExtra("drugImage");
        upc = intent.getStringExtra("upc");
        isFromOnboarding = intent.getBooleanExtra(Configs.ifFromOnBoarding, false);

        drugDescription = findViewById(R.id.description_content);
        int width = UIUtils.getWidth(this) - UIUtils.dp2px(this, 72);
        drugDescription.initWidth(width);
        drugDescription.setMaxLines(7);
        drugDescription.setCloseText(descriptionString);

        descriptionSpeaker = findViewById(R.id.description_speaker);
        descriptionSpeaker.setOnClickListener(v -> {
            SpeechUtil.speechText(DrugDetailActivity.this, descriptionString);
        });

        interactionSpeaker = findViewById(R.id.drug_interaction_speaker);
        interactionSpeaker.setOnClickListener(v -> {
            readInteraction();
        });

        drugName = findViewById(R.id.drug_name);
        drugName.setText(drugNameString);

        drugImage = findViewById(R.id.drug_image);
        Glide.with(this).load(imageUrl).into(drugImage);

        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            if (isFromOnboarding) {
                startActivity(new Intent(DrugDetailActivity.this, MainActivity.class));
            } else {
                finish();
            }
        });

        disclaimer = findViewById(R.id.disclaimer);

        requestDrugDetail();

    }

    private void requestDrugDetail() {
        loadingView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);

        DrugService.getInstance().getDrugDetail(FileUtil.getSPString(this, Configs.userNameKey), drugNameString, descriptionString)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<DrugDetail>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(DrugDetail drugDetail) {
                    Log.i(TAG, drugDetail.toString());
                    if (drugDetail.getCode() == Configs.requestSuccess) {
                        loadingView.setVisibility(View.GONE);
                        DrugDetailActivity.this.drugDetail = drugDetail;

                        if (drugDetail.isDrugListEmpty()) {
                            noDrugView.setVisibility(View.VISIBLE);
                        } else {
                            noDrugView.setVisibility(View.GONE);
                        }

                        if (drugDetail.getDrugInteractions().isEmpty()) {
                            noInteractionView.setVisibility(View.VISIBLE);
                            interactionList.setVisibility(View.GONE);
                        } else {
                            noInteractionView.setVisibility(View.GONE);
                            interactionList.setVisibility(View.VISIBLE);

                            // show disclaimer
                            String disclaimerText = getResources().getString(R.string.disclaimer);
                            SpannableString span = new SpannableString(disclaimerText);
                            span.setSpan(new StyleSpan(Typeface.BOLD), 0, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            disclaimer.setText(span);

                            interactionList = findViewById(R.id.drug_interaction_list);
                            interactionList.setLayoutManager(new LinearLayoutManager(DrugDetailActivity.this, LinearLayoutManager.VERTICAL, false));
                            interactionAdapter = new DrugInteractionAdapter(drugDetail.getDrugInteractions());
                            interactionList.setAdapter(interactionAdapter);
                        }

                        addToListImage = findViewById(R.id.add_to_list_top);
                        addToList = findViewById(R.id.add_to_list);
                        if (drugDetail.isInList()) {
                            addToList.setVisibility(View.GONE);
                            addToListImage.setVisibility(View.GONE);
                        } else {
                            addToList.setOnClickListener(addToListListener);
                            addToListImage.setOnClickListener(addToListListener);
                        }
                    } else {
                        errorView.setVisibility(View.VISIBLE);
                        loadingView.setVisibility(View.GONE);
                        Toast.makeText(DrugDetailActivity.this, drugDetail.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, e.toString());
                    errorView.setVisibility(View.VISIBLE);
                    loadingView.setVisibility(View.GONE);
                    Toast.makeText(DrugDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpeechUtil.destroy();
    }

    private String generateInteractionText(ArrayList<DrugInteraction> interactions) {
        StringBuilder result = new StringBuilder();
        for (DrugInteraction interaction : interactions) {
            result.append("Interact with");
            result.append(interaction.getDrugName());
            result.append(".");
            result.append(interaction.getInteractionDesc());
            result.append("The interaction probability is ");
            result.append(interaction.getProbability());
            result.append("%");
        }
        return result.toString();
    }

    private final View.OnClickListener addToListListener = v -> {
        DrugService.getInstance().addDrug(FileUtil.getSPString(DrugDetailActivity.this, Configs.userNameKey), drugNameString, imageUrl, upc, descriptionString)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GeneralResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GeneralResponse value) {
                        int statusRes;
                        String dialogTitle;
                        if (value.getCode() == Configs.requestSuccess) {
                            statusRes = R.drawable.dialog_correct;
                            dialogTitle = "Successfully added to list!";
                        } else {
                            statusRes = R.drawable.dialog_error;
                            dialogTitle = value.getMsg();
                        }
                        new OneButtonDialog(DrugDetailActivity.this, () -> {
                            // todo go to drug list screen

                        }).setTitle(dialogTitle)
                                .setStatusImgRes(statusRes)
                                .show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, e.toString());
                        Toast.makeText(DrugDetailActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    };

    private void readInteraction() {
        if (loadingView.getVisibility() == View.VISIBLE) {
            SpeechUtil.speechText(DrugDetailActivity.this, this.getResources().getString(R.string.interaction_loading));
        } else if (errorView.getVisibility() == View.VISIBLE) {
            SpeechUtil.speechText(DrugDetailActivity.this, this.getResources().getString(R.string.interaction_load_error));
        } else if (noInteractionView.getVisibility() == View.VISIBLE) {
            SpeechUtil.speechText(DrugDetailActivity.this, this.getResources().getString(R.string.interaction_not_detected));
        } else if (noDrugView.getVisibility() == View.VISIBLE) {
            SpeechUtil.speechText(DrugDetailActivity.this, this.getResources().getString(R.string.interaction_no_drug));
        } else {
            SpeechUtil.speechText(DrugDetailActivity.this, generateInteractionText(drugDetail.getDrugInteractions()));
        }
    }

}
