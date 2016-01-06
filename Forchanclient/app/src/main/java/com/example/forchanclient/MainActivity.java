package com.example.forchanclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView threads;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> threadslist;
    private ArrayList<String> pagesList;
    private ListView threadlistView;
    //private ArrayList<Threads> threadParse;
    private Threads[] threadParse;
    private TextView systemInfo;
    private ListView pagesListView;
    private MyListViewAdapter newAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // setContentView(R.layout.internettesting);
        // web=(WebView)findViewById(R.id.webView);
        // web.loadUrl("http://www.4chan.org")


        threads = (TextView) findViewById(R.id.textViewThreads);
        systemInfo=(TextView)findViewById(R.id.SystemInfo);
        threadlistView=(ListView) findViewById(R.id.listOfThreads);
        //threadParse=new ArrayList<Threads>();
        //threadParse=new Threads[400];
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
                URL url = new URL("https://a.4cdn.org/a/1.json");

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
           Log.d("Result from site",strJson);
           GsonBuilder builder=new GsonBuilder();
           Gson gson=builder.create();
           threadParse=gson.fromJson(strJson,Threads[].class);
           //threadParse = gson.fromJson(strJson,new TypeToken<List<Threads>>(){}.getType());
          // Log.d("result from site","sizeof jsonthreadparse!!!!"+String.valueOf(threadParse.size()));
           //Log.d("result fom site","0 result!!!"+threadParse.get(0).toString());

           /*for (int i=0;i<threadParse.length-1;i++)
           {
                   pagesList.add(threadParse[1].posts.get(i).name);

           }*/
          // newAdapter=new MyListViewAdapter(MainActivity.this,threadParse);
           //threadlistView.setAdapter(newAdapter);


           //Pages page=gson.fromJson(strJson,Pages.class)

         //  threads.append(gson.toJson(pages));
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
   // public
}
