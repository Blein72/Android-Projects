package com.example.forchanclient.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.SystemClock;
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
import com.example.forchanclient.Thread;
import com.squareup.picasso.Picasso;


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


    //public PostsAdapter(Context context, ArrayList<Thread> objects) {
    public PostsAdapter(Context context, ArrayList<Post> objects,String boardname) {
        super(context, R.layout.threads_item, objects);
        this.context=context;
        board=boardname;
        values=new ArrayList<Post>();
        values.addAll(objects);

    }
    static class ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView date;
        public TextView number;
        public TextView comment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.threads_item, parent, false);
        holder = new ViewHolder();
        holder.name=(TextView)rowView.findViewById(R.id.name);
        holder.date=(TextView)rowView.findViewById(R.id.date);
        holder.number=(TextView)rowView.findViewById(R.id.number);
        holder.comment=(TextView)rowView.findViewById(R.id.comment);
        holder.picture=(ImageView)rowView.findViewById(R.id.Picture);
        rowView.setTag(holder);


        //TextView name=(TextView)rowView.findViewById(R.id.name);
        //TextView date=(TextView)rowView.findViewById(R.id.date);
       // TextView number=(TextView)rowView.findViewById(R.id.number);
       // TextView comment=(TextView)rowView.findViewById(R.id.comment);
       //ImageView picture=(ImageView)rowView.findViewById(R.id.Picture);
        holder.picture.setMaxWidth(values.get(position).th_w);
        holder.picture.setMaxHeight(values.get(position).th_h);
        if (values.get(position).ext!=null&&((values.get(position).ext.equals(".jpg"))|(values.get(position).ext.equals(".png")))) {
            siteurl="https://i.4cdn.org/" + board + "/" + values.get(position).tim.toString() + values.get(position).ext;
            new DownloadImageTask(holder.picture,siteurl).execute();
        //Picasso.with(context).load(siteurl).fit().into(holder.picture);
       }

        //comment.setText("https://i.4cdn.org/"+board+"/"+values.get(position).tim+values.get(position).ext);
        holder.name.setText(values.get(position).name);
        holder.date.setText(values.get(position).now);
        holder.number.setText(values.get(position).no);
        holder.comment.setText(values.get(position).com);
        holder.comment.setText(Html.fromHtml(values.get(position).com));
        //SystemClock.sleep(1000);

        return rowView;



    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String url;

        public DownloadImageTask(ImageView bmImage,String url) {
            this.bmImage = bmImage;
            this.url = url;
        }

        protected Bitmap doInBackground(String... urls) {

            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            final int THUMBSIZE = 300;

            Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(result,
                    THUMBSIZE, THUMBSIZE);
            bmImage.setImageBitmap(ThumbImage);
        }
    }
}
