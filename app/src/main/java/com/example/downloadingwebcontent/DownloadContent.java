package com.example.downloadingwebcontent;

import android.os.AsyncTask;
import android.util.Pair;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadContent extends AsyncTask<String, Void, ArrayList<Pair<String, String>>> {

    @Override
    protected ArrayList<Pair<String, String>> doInBackground(String... params) {
        StringBuilder sb = new StringBuilder();

        try {
            String host = params[0];
            String path = params[1];
            URL url = new URL(host + path);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            ArrayList<Pair<String, String>> pairs = new ArrayList<>();

            String[] listVotes = sb.toString().split("class=\"listVote-item small-square \"");
            Pattern patternName = Pattern.compile("<div class=\"item-name\">(.*?)</div>");
            Pattern patternImage = Pattern.compile("<div class=\"item-image-wrapper\">(.*?)</div>");
            Pattern patternImageUrl = Pattern.compile("data-src=\"(.*?)\"");
            for (int i = 1; i < listVotes.length; i++) {
                Matcher matcherName = patternName.matcher(listVotes[i]);
                Matcher matcherImage = patternImage.matcher(listVotes[i]);
                String name = null;
                String imageUrl = null;

                if (matcherImage.find()) {
                    String image = matcherImage.group(1).trim();
                    Matcher matcherImageUrl = patternImageUrl.matcher(image);
                    if (matcherImageUrl.find()) {
                        imageUrl = matcherImageUrl.group(1).trim();
                    }
                }

                if (matcherName.find()) {
                    name = matcherName.group(1).trim();
                }

                if (name == null && imageUrl == null) {
                    System.out.println(listVotes[i]);
                }

                pairs.add(new Pair<String, String>(name, host + imageUrl));
            }

            return pairs;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}