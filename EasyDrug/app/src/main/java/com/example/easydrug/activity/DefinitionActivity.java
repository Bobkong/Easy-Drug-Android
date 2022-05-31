package com.example.easydrug.activity;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.R;
import com.example.easydrug.Utils.SpeechUtil;
import com.example.easydrug.adapter.DefinitionAdapter;
import com.example.easydrug.model.SideEffectPossibility;
import com.githang.statusbar.StatusBarCompat;

import java.util.ArrayList;

public class DefinitionActivity extends Activity {

    private RecyclerView definitionList;
    private ArrayList<SideEffectPossibility> mData;
    private DefinitionAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definition);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        definitionList = findViewById(R.id.definition_list);

        //mData = (ArrayList<SideEffectPossibility>) getIntent().getSerializableExtra("possibilities");
        mData = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SideEffectPossibility possibility = new SideEffectPossibility("Asthenia", "The quality or state of lacking physical strength or vigor");
            mData.add(possibility);
        }

        for (int i = 0; i < 5; i++) {
            SideEffectPossibility possibility = new SideEffectPossibility("Asthenia", "xxxxxxxxxxxxxxxxxxx");
            mData.add(possibility);
        }


        definitionList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mAdapter = new DefinitionAdapter(mData, this);
        definitionList.setAdapter(mAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SpeechUtil.destroy();
    }

}
