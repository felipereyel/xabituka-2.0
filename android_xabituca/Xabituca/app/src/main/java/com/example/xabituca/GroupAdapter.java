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

    public GroupAdapter(Activity context, ArrayList<Group> groups) {
        super(context, 0, groups);
        this.context = context;
        this.groups = groups;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.group_layout, null,true);

        TextView titleView = (TextView) rowView.findViewById(R.id.group_name);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.group_icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.group_description);

        titleView.setText(groups.get(position).name);
//        imageView.setImageResource(R.mipmap.ic_launcher_round);
        subtitleText.setText(groups.get(position).description);
        return rowView;
    };
}