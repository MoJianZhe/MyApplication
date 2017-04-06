package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dyoon.myapplication.R;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jun on 2017/4/1.
 */

public class UrlConnectionActivity extends Activity {
    private static final String TAG = "UrlConnectionActivity";
    public static final String URL = " http://www.ietf.org/rfc/rfc2616.txt";
    private TextView textView5;
    private Button urlTest;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    Toast.makeText(UrlConnectionActivity.this, "error", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    String text = ((String) msg.obj);
                    Log.i(TAG, "handleMessage: "+text);
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_connnetion);
        textView5 = (TextView) findViewById(R.id.textView5);
        urlTest = (Button) findViewById(R.id.urlTest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        urlTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                getDataFromUrl();
            }
        });
    }

    private void getDataFromUrl() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "run: ");
                    java.net.URL url = new URL(URL);
                    HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection();
                    urlConnection.connect();
                    InputStream is=urlConnection.getInputStream();
                    PrintStream ps = new PrintStream(System.out);
                    byte[] bytes = new byte[1024];
                    int lenth=0;
                    while ( (lenth=is.read(bytes))!= -1) {
                        String stRead = new String(bytes);
                        ps.print(stRead);
                        ps.print(bytes);
                    }
                    urlConnection.disconnect();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }




}
