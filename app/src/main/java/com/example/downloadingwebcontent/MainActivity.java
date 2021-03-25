package com.example.downloadingwebcontent;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView contentTextView;
    ArrayList<String> list;
    ImageView imageView;
    ArrayList<Comment> comments;
    ListView listView;
    MusicAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        // download page
//        setContentView(R.layout.activity_main);
//        contentTextView = findViewById(R.id.contentTextView);

//        // download and parse content
//        setContentView(R.layout.activity_main_parse_content);
//        list = new ArrayList<>();
//        listView = findViewById(R.id.listView);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);

//        // download image
//        setContentView(R.layout.activity_main_image);
//        imageView = findViewById(R.id.imageView);

        // download json
        setContentView(R.layout.activity_main_json);
        comments = new ArrayList<>();
        listView = findViewById(R.id.listView);
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comments);
        adapter = new MusicAdapter(this, comments);
        listView.setAdapter(adapter);

    }

    public void downloadPage(View v) {
        contentTextView.setText("Loading...");
        DownloadPage task = new DownloadPage();
        try {
            String url = "https://www.listchallenges.com/200-most-famous-people-of-all-time/vote";
            String result = task.execute(url).get();
            contentTextView.setText(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadAndParseContent(View v) {
        DownloadAndParseContent task = new DownloadAndParseContent();
        list.clear();
        try {
            ArrayList<Pair<String, String>> pairs = task.execute("https://www.listchallenges.com", "/200-most-famous-people-of-all-time/vote").get();
            for (Pair<String, String> pair : pairs) {
                list.add(pair.toString());
            }

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadImage(View view) {
        DownloadImage task = new DownloadImage();
        try {
            Bitmap bitmap = task.execute("https://upload.wikimedia.org/wikipedia/en/thumb/a/aa/Bart_Simpson_200px.png/170px-Bart_Simpson_200px.png").get();
            imageView.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void downloadJSON(View v) {
        DownloadPage task = new DownloadPage();
        try {
            String json = task.execute("https://jsonplaceholder.typicode.com/comments?postId=1").get();
            JSONArray jsonArray = new JSONArray(json);
            comments.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                String body = jsonObject.getString("body");
//                comments.add(body);
                Comment comment = new Comment(jsonObject);
                comments.add(comment);
            }
            adapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}