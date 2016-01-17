package com.example.forchanclient;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.forchanclient.Adapters.PostsAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ThreadsScreenActivity extends AppCompatActivity {
    private TextView threads;

    private ListView threadlistView;
    private String boardname;
    private Threads threadParse;
    private TextView boardhello;
    private ListView pagesListView;
    private PostsAdapter newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threads_screen);

        boardname=getIntent().getExtras().getString("boardname");

        boardhello=(TextView)findViewById(R.id.TextViewBoardsTop);
        threadlistView=(ListView) findViewById(R.id.listOfThreads);

        boardhello.append(boardname+"/");

        new ParseTask().execute();

        threadlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post thread=newAdapter.getItem(position);
                Intent threadnumber = new Intent(ThreadsScreenActivity.this,ThreadActivity.class);
                threadnumber.putExtra("boardname",boardname);
                threadnumber.putExtra("threadnumber",thread.no);
                startActivity(threadnumber);

            }
        });
    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("https://a.4cdn.org/"+boardname+"/1.json");

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
           threadParse = gson.fromJson(strJson, Threads.class);

           ArrayList<Post> postarray=new ArrayList<Post>();


           for (Thread t:threadParse.threads)
           {
               postarray.add(t.posts.get(0));
           }

          newAdapter=new PostsAdapter(ThreadsScreenActivity.this, postarray,boardname);

          threadlistView.setAdapter(newAdapter);

        }
    }
   // public
}
