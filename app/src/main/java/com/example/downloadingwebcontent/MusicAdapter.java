package com.example.downloadingwebcontent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MusicAdapter extends ArrayAdapter<Comment> {
    ArrayList<Comment> list;

    public MusicAdapter(@NonNull Context context, ArrayList<Comment> list) {
        super(context, R.layout.music_list_item, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Comment comment = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.music_list_item, null);
        }

        ((TextView) convertView.findViewById(R.id.titleTextView))
                .setText(comment.email);
        ((TextView) convertView.findViewById(R.id.singerTextView))
                .setText(comment.id + "");

        return convertView;
    }
}
