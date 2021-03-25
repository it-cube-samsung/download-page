package com.example.downloadingwebcontent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {


        try {
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();
            connection.connect();

            InputStream in = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
