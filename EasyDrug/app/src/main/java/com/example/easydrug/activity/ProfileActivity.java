package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.easydrug.Configs;
import com.example.easydrug.R;
import com.example.easydrug.Utils.FileUtil;
import com.example.easydrug.Utils.FinishActivity;
import com.example.easydrug.Utils.UIUtils;
import com.example.easydrug.widget.TwoButtonDialog;
import com.githang.statusbar.StatusBarCompat;

import org.greenrobot.eventbus.EventBus;

public class ProfileActivity extends Activity {

    private LinearLayout editProfile, logout, contactUs;
    private TextView username;
    private ImageView back;
    private ConstraintLayout editProfileView, profileList;
    private EditText usernameEdit, passwordEdit;
    private ConstraintLayout saveButton;
    private ImageView showPassword;
    private boolean isShowPassword = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.bg_color));

        editProfile = findViewById(R.id.edit_profile);
        logout = findViewById(R.id.log_out);
        contactUs = findViewById(R.id.contact_us);
        back = findViewById(R.id.back);
        editProfileView = findViewById(R.id.edit_view);
        usernameEdit = findViewById(R.id.username_edit);
        passwordEdit = findViewById(R.id.password_edit);
        saveButton = findViewById(R.id.save_button);
        showPassword = findViewById(R.id.show_password);
        profileList = findViewById(R.id.profile_list);

        back.setOnClickListener(v -> {
            if (editProfileView.getVisibility() == View.VISIBLE) {
                editProfileView.setVisibility(View.GONE);
                profileList.setVisibility(View.VISIBLE);
                isShowPassword = false;
                showPassword.setImageResource(R.drawable.hide_password);
            } else {
                finish();
            }
        });

        editProfile.setOnClickListener(v -> {
            profileList.setVisibility(View.GONE);
            editProfileView.setVisibility(View.VISIBLE);
            usernameEdit.setText(FileUtil.getSPString(ProfileActivity.this, Configs.userNameKey));
            String password = FileUtil.getSPString(ProfileActivity.this, Configs.passwordKey);
            StringBuilder passwordHide = new StringBuilder();
            for (int i = 0; i < password.length(); i++) {
                passwordHide.append("*");
            }
            passwordEdit.setText(passwordHide);
        });

        showPassword.setOnClickListener(v -> {
            String password = FileUtil.getSPString(ProfileActivity.this, Configs.passwordKey);
            if (isShowPassword) {
                int padding = UIUtils.dp2px(this, 10f);
                showPassword.setPadding(padding, padding, padding, padding);
                showPassword.setImageResource(R.drawable.hide_password);
                StringBuilder passwordHide = new StringBuilder();
                for (int i = 0; i < password.length(); i++) {
                    passwordHide.append("*");
                }
                passwordEdit.setText(passwordHide);
                isShowPassword = false;
            } else {
                showPassword.setImageResource(R.drawable.see_password);
                int padding = UIUtils.dp2px(this, 8f);
                showPassword.setPadding(padding, padding, padding, padding);
                passwordEdit.setText(password);
                isShowPassword = true;
            }
        });

        saveButton.setOnClickListener(v -> {
            // todo update profile

        });

        logout.setOnClickListener(v -> {
            new TwoButtonDialog(this, () -> {
                // do nothing
            }, () -> {
                // delete profile & go to login
                FileUtil.deleteSPString(ProfileActivity.this, Configs.userNameKey);
                FileUtil.deleteSPString(ProfileActivity.this, Configs.passwordKey);
                startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                finish();
                EventBus.getDefault().post(new FinishActivity());
            }).setTitle(getString(R.string.log_out_title))
                    .setStatusImgRes(R.drawable.dialog_warning)
                    .setRightButtonText("Log Out")
                    .setLeftButtonText("Cancel")
                    .setRightButtonBg(R.drawable.grey_color_stroke_bg_8dp)
                    .setLeftButtonBg(R.drawable.theme_color_bg_8dp)
                    .setLeftButtonTextColor(R.color.white)
                    .setRightButtonTextColor(R.color.grey)
                    .show();
        });

    }
}
