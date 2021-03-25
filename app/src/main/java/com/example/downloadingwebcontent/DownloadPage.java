package com.example.downloadingwebcontent;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadPage extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder sb = new StringBuilder();

        try {
            URL url = new URL(urls[0]);
            URLConnection urlConnection =  url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
