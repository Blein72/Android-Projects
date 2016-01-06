package com.example.forchanclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Я on 04.01.2016.
 */
public class MyListViewAdapter extends ArrayAdapter<Post> {
    private  Context context;
    private  ArrayList<Post> values;

    //public MyListViewAdapter(Context context, ArrayList<Threads> objects) {
    public MyListViewAdapter(Context context, ArrayList<Post> objects) {
        super(context, R.layout.thread_item, objects);
        this.context=context;
        values=new ArrayList<Post>();

        //values=objects.get(0).posts;

        values.addAll(objects);
        //values.addAll(objects.get(1).posts);
       // this.values=objects.get(1).posts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.thread_item, parent, false);
        TextView name=(TextView)rowView.findViewById(R.id.name);
        TextView date=(TextView)rowView.findViewById(R.id.date);
        TextView number=(TextView)rowView.findViewById(R.id.number);
        TextView comment=(TextView)rowView.findViewById(R.id.comment);
        name.setText(values.get(position).name);
        date.setText(values.get(position).now);
        number.setText(values.get(position).no);
        comment.setText(values.get(position).com);

        return rowView;



    }
}
