package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dyoon.myapplication.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jun on 2016/11/26.
 */

public class   DownLoadActivity extends Activity {
    private ProgressBar progressBar;
    private Button downloadBtn;
    private TextView textView;
    private URLConnection urlConnection;
    private InputStream inputStream;
    private OutputStream outputStream;
    private int FileLength;
    private int DownloadFileLength=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    progressBar.setMax(FileLength);
                    Log.e("fileLenth", String.valueOf(FileLength));
                    break;
                case 1:
                    progressBar.setProgress(DownloadFileLength);
                    Log.e("下载比例", String.valueOf(DownloadFileLength / FileLength));
                    textView.setText(String.valueOf(DownloadFileLength*100 / FileLength)+"%");
                    break;

                case 2:
                    Log.e("download", "下载完成");
                    break;
                default:
                    break;

            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        progressBar = (ProgressBar) findViewById(R.id.download_progressbar);
        downloadBtn = (Button) findViewById(R.id.btn_download);
        textView = (TextView) findViewById(R.id.txt_download);
        downloadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("dianji", "ni dian l wo yixia ");
//                        DownLoadFile("http://210.30.12.1:8080/mp3/DJ.mp3");
                        downLoadFile("http://192.168.1.117/file/10/QQ7.9Light.exe");

                    }
                }).start();
            }
        });
    }

    private void downLoadFile(String url) {
        Log.e("test", "这个方法为什么不执行");
        try {
            URL url1 = new URL(url);
            urlConnection=url1.openConnection();
            if (urlConnection.getReadTimeout()==5) {
                Log.i("---------->", "当前网络有问题");
                // return;
            }
            inputStream=urlConnection.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String savePath = Environment.getExternalStorageDirectory() + "/DownFile/";
        File folder = new File(savePath);
        if (!folder.exists()) {
            folder.mkdir();
        }
        String filePath = savePath + "DJ.mp3";
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Message message=Message.obtain();
        try {
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            FileLength=urlConnection.getContentLength();
            if (FileLength < 0) {
                Toast.makeText(DownLoadActivity.this,"获取文件大小失败",Toast.LENGTH_SHORT).show();
            }
            message.what=0;
            handler.sendMessage(message);
            while (DownloadFileLength < FileLength) {
                int read = inputStream.read(buffer);
                DownloadFileLength+=read;
                outputStream.write(buffer);
                Message message1=new Message();
                message1.what=1;
                handler.sendMessage(message);
            }
            Message message2=new Message();
            message2.what=2;
            handler.sendMessage(message2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
