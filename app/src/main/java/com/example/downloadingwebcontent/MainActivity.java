package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    TextView contentTextView;
    EditText urlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contentTextView = findViewById(R.id.contentTextView);
        urlEditText = findViewById(R.id.urlEditText);


    }

    public void goClick(View v) {
        contentTextView.setText("Loading...");
        DownloadPage task = new DownloadPage();

        try {
            String result = task.execute("https://" + urlEditText.getText().toString()).get();
            contentTextView.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}