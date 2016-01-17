package com.example.forchanclient;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import com.example.forchanclient.Adapters.PostsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Я on 17.01.2016.
 */
public class ThreadActivity extends Activity {
    private ListView posts;
    private String threadNumber;
    private String boardname;
    private Thread thread;
    private PostsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        threadNumber=getIntent().getExtras().getString("threadnumber");
        boardname=getIntent().getExtras().getString("boardname");

        setContentView(R.layout.thread_screen_layout);
        posts=(ListView)findViewById(R.id.listViewThread);
        new ParseTask().execute();



    }
    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("https://a.4cdn.org/"+boardname+"/thread/"+threadNumber+".json");

                urlConnection = (HttpURLConnection) url.openConnection();
                //urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultJson;

        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            GsonBuilder builder=new GsonBuilder();
            Gson gson=builder.create();
            thread = gson.fromJson(strJson, Thread.class);
            adapter=new PostsAdapter(ThreadActivity.this, thread.posts,boardname);
            posts.setAdapter(adapter);

        }
    }
}
