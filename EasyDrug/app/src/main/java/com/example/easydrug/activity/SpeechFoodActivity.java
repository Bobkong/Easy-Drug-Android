package com.example.easydrug.activity;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;

import static com.example.easydrug.Configs.serviceRegion;
import static com.example.easydrug.Configs.speechSubscriptionKey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.example.easydrug.R;
import com.githang.statusbar.StatusBarCompat;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

public class SpeechFoodActivity extends Activity {

    private static String TAG = "SpeechFoodActivity";
    SpeechConfig config;
    SpeechRecognizer reco;
    private boolean isRecording = false;
    private StringBuilder recordingResult = new StringBuilder();
    private TextView recordTitle;
    private ImageView analyzeSpeech;
    private ImageView backButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_food);
        StatusBarCompat.setStatusBarColor(this, this.getResources().getColor(R.color.bg_color));

        recordTitle = findViewById(R.id.record_title);
        analyzeSpeech = findViewById(R.id.analyzing_speech);
        backButton = findViewById(R.id.back);

        backButton.setOnClickListener(v -> startActivity(new Intent(SpeechFoodActivity.this, MainActivity.class)));

        int requestCode = 5; // unique code for the permission request
        ActivityCompat.requestPermissions(SpeechFoodActivity.this, new String[]{RECORD_AUDIO, INTERNET}, requestCode);

        try {
            config = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
            reco = new SpeechRecognizer(config);
            reco.recognized.addEventListener((o, speechRecognitionResultEventArgs) -> {
                final String s = speechRecognitionResultEventArgs.getResult().getText();
                Log.i(TAG, "Final result received: " + s);
                recordingResult.append(",");
                recordingResult.append(s);
                // if record has been stopped
                if (!isRecording) {
                    // use comma to split recording result.
                    SpeechFoodActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SpeechFoodActivity.this, SpeechFoodResultActivity.class);
                            intent.putExtra("ingredients", recordingResult.toString());
                            startActivity(intent);
                        }
                    });
                }
            });
        } catch (Exception ex) {
            Log.e("SpeechSDKDemo", "unexpected " + ex.getMessage());
        }
    }

    public void onSpeechButtonClicked(View v) {
        ImageView button = this.findViewById(R.id.record_button);

        if (isRecording) {
            isRecording = false;
            reco.stopContinuousRecognitionAsync();
            Log.i(TAG, "Continuous recognition stopped.");
            button.setImageResource(R.drawable.record_play);
            recordTitle.setText(R.string.go_to_record_text);
            analyzeSpeech.setVisibility(View.VISIBLE);
        } else {
            recordingResult = new StringBuilder();
            isRecording = true;
            reco.startContinuousRecognitionAsync();
            button.setImageResource(R.drawable.record_pause);
            recordTitle.setText(R.string.go_to_pause_text);
        }




    }
}
