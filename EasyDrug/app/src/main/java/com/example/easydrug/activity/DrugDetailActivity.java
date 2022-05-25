package com.example.easydrug.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.easydrug.R;
import com.example.easydrug.UIUtils;
import com.example.easydrug.widget.ExpandTextView;

public class DrugDetailActivity extends Activity {

    private String testText = "Tylenol is an over-the-counter (OTC) medicine used to treat reduce symptoms of pain and as a fever reducer. Tylenol may be used alone or with other medications. Tylenol belongs to a class of drugs called...View MoreTylenol is an over-the-counter (OTC) medicine used to treat reduce symptoms of pain and as a fever reducer. Tylenol may be used alone or with other medications. Tylenol belongs to a class of drugs called...View More";
    private ExpandTextView drugDescription;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_detail);
        drugDescription = findViewById(R.id.description_content);
        int width = UIUtils.getWidth(this) - UIUtils.dp2px(this, 72);
        drugDescription.initWidth(width);
        drugDescription.setMaxLines(7);
        drugDescription.setCloseText(testText);
    }
}
