package com.example.forchanclient.Adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.forchanclient.Board;
import com.example.forchanclient.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Я on 10.01.2016.
 */
public class BoardListViewAdapter extends ArrayAdapter {
    private ArrayList<Board> values;
    private Context context;

    public BoardListViewAdapter(Context context,  ArrayList<Board> objects) {
        super(context, R.layout.boards_item, objects);
        this.context=context;
        values=new ArrayList<Board>();
        values.addAll(objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.boards_item, parent, false);
        TextView boardName=(TextView)rowView.findViewById(R.id.boardName);
        TextView boardTitle=(TextView)rowView.findViewById(R.id.boardTitle);
        TextView  boardDescription=(TextView)rowView.findViewById(R.id.boardDescription);

        boardName.setText(values.get(position).board);
        boardTitle.setText(values.get(position).title);
        boardDescription.setText(Html.fromHtml(values.get(position).meta_description));
        return rowView;
    }
}
