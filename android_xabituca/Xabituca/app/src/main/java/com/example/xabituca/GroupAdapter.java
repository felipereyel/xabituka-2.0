package com.example.xabituca;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xabituca.models.Group;
import java.util.ArrayList;

public class GroupAdapter extends ArrayAdapter<Group> {
    private final Activity context;
    private final ArrayList<Group> groups;

    public GroupAdapter(Activity context, int layoutItem, ArrayList<Group> groups) {
        super(context, layoutItem, groups);
        this.context = context;
        this.groups = groups;
    }

//    public View getView(int position, View view, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View rowView = inflater.inflate(R.layout.group_layout, null,true);
//        TextView titleText = (TextView) rowView.findViewById(R.id.title);
//        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
////        titleText.setText(groups.get(position).nome);
//        imageView.setImageResource(R.mipmap.ic_launcher_round);
////        subtitleText.setText(groups.get(position).texto);
//        return rowView;
//    };


}