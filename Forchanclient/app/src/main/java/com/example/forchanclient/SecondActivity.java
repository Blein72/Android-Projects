package com.example.forchanclient;

/**
 * Created by Я on 20.12.2015.
 */
public class SecondActivity {
    /*
    import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    WebView web;
    private TextView boards;
    private TextView threads;
    private Button showthreads;
    private ArrayAdapter<String> adapter;
    public ArrayList<String> boardsList;
    public Elements title;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.testlayout);
        // setContentView(R.layout.internettesting);
        // web=(WebView)findViewById(R.id.webView);
        // web.loadUrl("http://www.4chan.org");

        boards = (TextView) findViewById(R.id.textViewBoards);
        threads = (TextView) findViewById(R.id.textViewThreads);
        showthreads = (Button) findViewById(R.id.btnShowThreads);
        boardsList=new ArrayList<String>();
        lv = (ListView) findViewById(R.id.listView1);
        new TestThread().execute();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.product_name, titleList);

    }

    public class TestThread extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            Document doc;
            try {
                // определяем откуда будем воровать данные
                doc = Jsoup.connect("http://freehabr.ru/").get();
                // задаем с какого места, я выбрал заголовке статей
                title = doc.select(".title");
                // чистим наш аррей лист для того что бы заполнить
                boardsList.clear();
                // и в цикле захватываем все данные какие есть на странице
                for (Element titles : title) {
                    // записываем в аррей лист
                    boardsList.add(titles.text());
                }

                // return null;

            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            boards.setText(boardsList.get(0));
        }
    }
}

     */
}
