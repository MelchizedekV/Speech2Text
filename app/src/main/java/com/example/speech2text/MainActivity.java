package com.example.speech2text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView microphone;
    Intent speechRecognizer;
    TextView textDisplay;
//    SpeechRecognizer mSpeechRecognizer;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        microphone =findViewById(R.id.microphone);
        textDisplay=findViewById(R.id.textDisplay);
//        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        microphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                checkPermission();

                convertSpeechToText();

            }
        });

    }



    private void convertSpeechToText()
    {
        speechRecognizer =new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        speechRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizer.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        speechRecognizer.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        startActivityForResult(speechRecognizer,REQ_CODE_SPEECH_INPUT);

//        mSpeechRecognizer.startListening(speechRecognizer);
//        startActivityForResult(speechRecognizer,REQ_CODE_SPEECH_INPUT);
//        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
//            @Override
//            public void onReadyForSpeech(Bundle bundle) {
//
//
//            }
//
//            @Override
//            public void onBeginningOfSpeech() {
//
//            }
//
//            @Override
//            public void onRmsChanged(float v) {
//
//            }
//
//            @Override
//            public void onBufferReceived(byte[] bytes) {
//
//            }
//
//            @Override
//            public void onEndOfSpeech() {
//
//            }
//
//            @Override
//            public void onError(int i) {
//
//            }
//
//            @Override
//            public void onResults(Bundle bundle) {
//
//                ArrayList<String> speech2Text = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
//
//                textDisplay.setText(speech2Text.get(0));
//
//
//            }
//
//            @Override
//            public void onPartialResults(Bundle bundle) {
//
//            }
//
//            @Override
//            public void onEvent(int i, Bundle bundle) {
//
//            }
//        });

    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech2Text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if(speech2Text != null) 
                    textDisplay.setText(speech2Text.get(0));

                }
                break;
            }

        }




    }
}
