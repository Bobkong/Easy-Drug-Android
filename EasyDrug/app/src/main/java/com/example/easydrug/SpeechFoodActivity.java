package com.example.easydrug;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;

import static com.example.easydrug.Configs.serviceRegion;
import static com.example.easydrug.Configs.speechSubscriptionKey;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;

public class SpeechFoodActivity extends Activity {

    private static String TAG = "SpeechFoodActivity";
    SpeechConfig config;
    SpeechRecognizer reco;
    private boolean isRecording = false;
    private StringBuilder recordingResult = new StringBuilder();
    private TextView speechResult;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_food);
        speechResult = findViewById(R.id.speech_result);

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
                    String[] ingredients = recordingResult.toString().split(",");
                    StringBuilder showText = new StringBuilder();
                    for (String ingredient : ingredients) {
                        // remove unuseful character
                        ingredient = ingredient.replace(".", "");
                        showText.append(ingredient).append("\n");
                        Log.i(TAG, ingredient);
                    }
                    SpeechFoodActivity.this.runOnUiThread(() -> speechResult.setText(showText.toString()));
                }
            });
        } catch (Exception ex) {
            Log.e("SpeechSDKDemo", "unexpected " + ex.getMessage());
        }
    }

    public void onSpeechButtonClicked(View v) {
        TextView button = this.findViewById(R.id.record_button); // 'hello' is the ID of your text view

        if (isRecording) {
            isRecording = false;
            reco.stopContinuousRecognitionAsync();
            Log.i(TAG, "Continuous recognition stopped.");
            button.setText("Start Recording");
        } else {
            recordingResult = new StringBuilder();
            isRecording = true;
            reco.startContinuousRecognitionAsync();
            button.setText("Stop Recording");
        }




    }
}
