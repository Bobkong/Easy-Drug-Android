package com.example.easydrug.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easydrug.Configs;
import com.example.easydrug.R;
import com.example.easydrug.adapter.ContentAdapter;
import com.example.easydrug.adapter.TagAdapter;
import com.example.easydrug.model.ResourcesResponse;
import com.example.easydrug.netservice.Api.ResourceService;
import com.githang.statusbar.StatusBarCompat;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class ExploreActivity extends Activity {
    private RecyclerView tagView;
    private RecyclerView contentView;
    private TagAdapter tagAdapter;
    private ContentAdapter contentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        tagView = findViewById(R.id.tag_list);

        contentView = findViewById(R.id.content_list);

        requestExplore();

    }
    private void requestExplore() {
        ResourceService.getInstance().getResources()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResourcesResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResourcesResponse value) {
                        if (value.getCode() == Configs.requestSuccess) {
                            tagAdapter = new TagAdapter(value.getTag_list(), ExploreActivity.this);
                            tagView.setAdapter(tagAdapter);

                            contentAdapter = new ContentAdapter(value.getResource_list(), ExploreActivity.this);
                            contentView.setAdapter(contentAdapter);
                        } else {
                            Toast.makeText(ExploreActivity.this, value.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ExploreActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
