package com.example.strangers;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardScreen extends AppCompatActivity {

    EditText secretCodeBox;
    Button joinnow,sharenow;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_screen);


        secretCodeBox = findViewById(R.id.secretBox);
        joinnow = findViewById(R.id.joinnow);
        sharenow = findViewById(R.id.sharenow);

        URL serverURL = null;

        try {
            new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions;
            defaultOptions = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(serverURL)
                    .setWelcomePageEnabled(false)
                    .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        


        joinnow.setOnClickListener(v -> {

        });


    }
}