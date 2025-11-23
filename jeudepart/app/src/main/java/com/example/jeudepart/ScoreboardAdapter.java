package com.example.jeudepart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class ScoreboardAdapter extends ArrayAdapter<Match> {

    private final Context context;
    private final Match[] items;

    public ScoreboardAdapter(Context context, Match[] items) {
        super(context, R.layout.activity_scoreboard, items);
        this.context = context;
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.scoreboard_item, parent, false);

        ImageView country = rowView.findViewById(R.id.country);
        TextView firstname = rowView.findViewById(R.id.firstname);
        TextView lastName = rowView.findViewById(R.id.lastname);
        TextView score = rowView.findViewById(R.id.score);
        TextView date = rowView.findViewById(R.id.date);

        User currentUser = items[position].getUser();
        Pays paysEnum = currentUser.getPays();
        country.setImageDrawable(ContextCompat.getDrawable(context,paysEnum.getDrapeauResId()));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        firstname.setText(items[position].getUser().getPrenom());
        lastName.setText(items[position].getUser().getNom());
        score.setText(String.valueOf(items[position].getScore()));
        date.setText(formatter.format(items[position].getDate()));

        return rowView;
    }
}
