package com.example.forchanclient;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.forchanclient.Adapters.BoardListViewAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Я on 10.01.2016.
 */
public class MainScreenActivity extends Activity {
    private ListView boardsListView;
    private Boards boards;
    private BoardListViewAdapter adapter;
    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        info = (TextView) findViewById(R.id.TextViewTopHeader);
        boardsListView = (ListView) findViewById(R.id.boardsListView);
        //info.append(" initialized");

        new ParseTask().execute();


        boardsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                //TextView test= (TextView)itemClicked.findViewById(R.id.boardName);
                Board board1 = (Board) adapter.getItem(position);
                Intent intent = new Intent(MainScreenActivity.this, ThreadsScreenActivity.class);
                intent.putExtra("boardname", board1.board);
                //info.append(board1.board);
                startActivity(intent);
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
                URL url = new URL("https://a.4cdn.org/boards.json");

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
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            boards = gson.fromJson(strJson, Boards.class);

            adapter = new BoardListViewAdapter(MainScreenActivity.this, boards.boards);
            boardsListView.setAdapter(adapter);

        }
    }
}
