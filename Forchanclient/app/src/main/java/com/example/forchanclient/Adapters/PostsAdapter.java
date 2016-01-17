package com.example.forchanclient.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forchanclient.Post;
import com.example.forchanclient.R;


import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Я on 04.01.2016.
 */
public class PostsAdapter extends ArrayAdapter<Post> {
    private  Context context;
    private  ArrayList<Post> values;
    private String board;
    private String siteurl;
    private ImageView picture;

    //public PostsAdapter(Context context, ArrayList<Thread> objects) {
    public PostsAdapter(Context context, ArrayList<Post> objects,String boardname) {
        super(context, R.layout.threads_item, objects);
        this.context=context;
        board=boardname;
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
        picture=(ImageView)rowView.findViewById(R.id.Picture);
        picture.setMaxWidth(values.get(position).th_w);
        picture.setMaxHeight(values.get(position).th_h);
        if (values.get(position).tim!=null) {
            siteurl="https://i.4cdn.org/" + board + "/" + values.get(position).tim.toString() + values.get(position).ext;
            new DownloadImageTask(picture)
                    .execute(siteurl);


            //picture.setImageURI(Uri.parse("https://i.4cdn.org/" + board + "/" + values.get(position).tim.toString() + values.get(position).ext));
        }
        //comment.setText("https://i.4cdn.org/"+board+"/"+values.get(position).tim+values.get(position).ext);
        name.setText(values.get(position).name);
        date.setText(values.get(position).now);
        number.setText(values.get(position).no);
        comment.setText(Html.fromHtml(values.get(position).com));

        return rowView;



    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            picture.setImageBitmap(result);
        }
    }
}
