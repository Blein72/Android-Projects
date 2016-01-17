package com.example.forchanclient.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forchanclient.Post;
import com.example.forchanclient.R;

import java.util.ArrayList;

/**
 * Created by Я on 04.01.2016.
 */
public class PostsAdapter extends ArrayAdapter<Post> {
    private  Context context;
    private  ArrayList<Post> values;

    //public PostsAdapter(Context context, ArrayList<Thread> objects) {
    public PostsAdapter(Context context, ArrayList<Post> objects) {
        super(context, R.layout.threads_item, objects);
        this.context=context;
        values=new ArrayList<Post>();
        values.addAll(objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.threads_item, parent, false);
        TextView name=(TextView)rowView.findViewById(R.id.name);
        TextView date=(TextView)rowView.findViewById(R.id.date);
        TextView number=(TextView)rowView.findViewById(R.id.number);
        TextView comment=(TextView)rowView.findViewById(R.id.comment);
        name.setText(values.get(position).name);
        date.setText(values.get(position).now);
        number.setText(values.get(position).no);
        comment.setText(Html.fromHtml(values.get(position).com));

        return rowView;



    }
}
