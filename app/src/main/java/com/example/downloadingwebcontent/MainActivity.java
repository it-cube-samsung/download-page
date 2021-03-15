package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        DownloadContent task = new DownloadContent();
        String url = "https://www.listchallenges.com/200-most-famous-people-of-all-time/vote";

        try {
            ArrayList<Pair<String, String>> pairs = task.execute("https://www.listchallenges.com", "/200-most-famous-people-of-all-time/vote").get();
            StringBuilder sb = new StringBuilder();
            for (Pair<String, String> pair : pairs) {
                sb.append(pair.toString());
                sb.append("\n");
            }
            contentTextView.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}