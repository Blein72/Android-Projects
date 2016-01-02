package com.example.forchanclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView web;
    private TextView boards;
    private TextView threads;
    private Button showthreads;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> threadslist;
    private Elements title;
    private ListView threadlistView;
    private ListView lv;
    private TextView systemInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // setContentView(R.layout.internettesting);
        // web=(WebView)findViewById(R.id.webView);
        // web.loadUrl("http://www.4chan.org")

        boards = (TextView) findViewById(R.id.textViewBoards);
        threads = (TextView) findViewById(R.id.textViewThreads);
        showthreads = (Button) findViewById(R.id.btnShowThreads);
        systemInfo=(TextView)findViewById(R.id.SystemInfo);
        threadlistView=(ListView) findViewById(R.id.listOfThreads);

        threadslist = new ArrayList<String>();
        //adapter=new ArrayAdapter<String>(this,R.layout.simple_list_item_1 ,threadslist);
        //threadlistView.setAdapter(adapter);
        threadslist.add("start!!!!!");


        //systemInfo.append("before async");
        new ParseTask().execute();
       // systemInfo.append("after async");
        for (String st:threadslist) {
            threads.append(st);
        }

    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // получаем данные с внешнего ресурса
            try {
                URL url = new URL("https://a.4cdn.org/a/threads.json");

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
            JSONObject dataJsonObj = null;
            //String secondName = "";

           GsonBuilder builder=new GsonBuilder();
           Gson gson=builder.create();
           Pages[] pages=gson.fromJson(strJson,Pages[].class);
           //Pages page=gson.fromJson(strJson,Pages.class)

           threads.append(gson.toJson(pages));
         // threads.append(strJson);
          // threads.append("end");

          //try {
                //dataJsonObj = new JSONObject(strJson);
             //JSONArray pages = dataJsonObj.getJSONArray("page");
               // JSONArray threads = dataJsonObj.getJSONArray("threads");

               //for(int i=0;i<threads.length();i++) {
              //     JSONObject thread=threads.getJSONObject(i);
              //     threadslist.add(thread.toString());
               //}



           // } //catch (JSONException e) {
          //      e.printStackTrace();
          // }
        }
    }
}
