package com.example.easydrug;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.RECORD_AUDIO;
import static com.example.easydrug.Configs.serviceRegion;
import static com.example.easydrug.Configs.speechSubscriptionKey;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.CancellationReason;
import com.microsoft.cognitiveservices.speech.ResultReason;
import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechRecognizer;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisCancellationDetails;
import com.microsoft.cognitiveservices.speech.SpeechSynthesisResult;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;

import java.util.concurrent.ExecutionException;

public class TextToSpeechActivity extends AppCompatActivity {
    private SpeechConfig speechConfig;
    private SpeechSynthesizer synthesizer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);


        // Note: we need to request the permissions
        int requestCode = 5; // unique code for the permission request
        ActivityCompat.requestPermissions(TextToSpeechActivity.this, new String[]{INTERNET}, requestCode);

        // Initialize speech synthesizer and its dependencies
        speechConfig = SpeechConfig.fromSubscription(speechSubscriptionKey, serviceRegion);
        assert(speechConfig != null);

        synthesizer = new SpeechSynthesizer(speechConfig);
        assert(synthesizer != null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Release speech synthesizer and its dependencies
        synthesizer.close();
        speechConfig.close();
    }
    public void onSpeechButtonClicked(View v) {
        TextView outputMessage = this.findViewById(R.id.outputMessage);
        EditText speakText = this.findViewById(R.id.speakText);

        try {
            // Note: this will block the UI thread, so eventually, you want to register for the event
            SpeechSynthesisResult result = synthesizer.SpeakText(speakText.getText().toString());
            assert(result != null);

            if (result.getReason() == ResultReason.SynthesizingAudioCompleted) {
                outputMessage.setText("Speech synthesis succeeded.");
            }
            else if (result.getReason() == ResultReason.Canceled) {
                String cancellationDetails =
                        SpeechSynthesisCancellationDetails.fromResult(result).toString();
                outputMessage.setText("Error synthesizing. Error detail: " +
                        System.lineSeparator() + cancellationDetails +
                        System.lineSeparator() + "Did you update the subscription info?");
            }

            result.close();
        } catch (Exception ex) {
            Log.e("SpeechSDKDemo", "unexpected " + ex.getMessage());
            assert(false);
        }
    }
}
