package com.dyoon.myapplication.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.dyoon.myapplication.adapter.DialogListAdapter;
import com.dyoon.myapplication.R;
import com.dyoon.myapplication.presente.MainPresenter;
import com.dyoon.myapplication.presente.Presenter;
import com.dyoon.myapplication.service.MyIntentService;
import com.dyoon.myapplication.service.ServiceTest;
import com.dyoon.myapplication.ui.listview.ImageListActivity;
import com.dyoon.myapplication.ui.listview.IndexListViewActivity;
import com.dyoon.myapplication.ui.listview.LoadMoreActivity;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
@ContentView(R.layout.activity_main)
public class    MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MainActivity";
    @ViewInject(R.id.btn)
    private Button button;
    private String name;
    @ViewInject(R.id.send)
    private Button send;
    @ViewInject(R.id.send2)
    private Button send2;
    @ViewInject(R.id.start_expand)
    private Button expande;
    @ViewInject(R.id.download)
    private Button downLoad;
    @ViewInject(R.id.btn_main_download)
    private Button downloadActivity;
    @ViewInject(R.id.btn_testView)
    private Button testView;
    @ViewInject(R.id.btn_underline)
    private Button underline;
    @ViewInject(R.id.btn_viewpager)
    private Button viewpager;
    @ViewInject(R.id.btn_recycler)
    private Button recycler;
    @ViewInject(R.id.btn_dialog)
    private Button btnDialog;
    @ViewInject(R.id.btn_start_service)
    private Button startService;
    @ViewInject(R.id.btn_stop_service)
    private Button stopService;
    @ViewInject(R.id.btn_my_intent_service)
    private Button startIntentService;
    @ViewInject(R.id.btn_action_mode)
    private Button actionMode;
    @ViewInject(R.id.btn_animal)
    private Button animal;
    @ViewInject(R.id.btn_add)
    private Button add;
    @ViewInject(R.id.btn_listener)
    private Button listener;
    @ViewInject(R.id.btn_property_animation)
    private Button PropertyAnimation;
    @ViewInject(R.id.start_img_list)
    private Button imgList;
    @ViewInject(R.id.btn_load_more)
    private Button loadMore;
    @ViewInject(R.id.btn_index_list)
    private Button indexListView;
    private Handler handler = new Handler() {

/*        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            name= (String) msg.obj;
            Log.e("name", name);
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }*/

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 5:
//                        getHomePage();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final MyThread myThread=new MyThread();
        myThread.start();
        x.view().inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter presenter = new Presenter();
                presenter.gotoSecond(handler);
                Log.i(TAG, "onClick: present");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                myThread.handler.sendEmptyMessage(0);
                Message message=Message.obtain();
                message.what=1;
                message.obj = "sed1click";
                myThread.handler.sendMessage(message);

            }
        });
        send2.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Log.i(TAG, "onClick: send2");
                /*    Message message=Message.obtain();
                    message.what=2;
                    message.obj = "send2click";
                    myThread.handler.sendMessage(message);*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getData(handler);
                    }
                }).start();
            }
        });


        expande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Expandable.class);
                startActivity(intent);
            }
        });

        downloadActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DownLoadActivity.class);
                startActivity(intent);
            }
        });
        testView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewTestActivity.class);
                startActivity(intent);
            }
        });
        underline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LinearoutActivity.class);
                startActivity(intent);
            }
        });
        downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams requestParams = new RequestParams("http://192.168.1.117/api/login/?username=dagg&password=123");

                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                 /*       Log.e(TAG, "开始访问Homepage");
                        try {
                            getHomePage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }*/
                        final RequestParams requestParams1 = new RequestParams("http://192.168.1.117/api/homepage/");

                        x.http().get(requestParams1, new CommonCallback<String>() {
                            @Override
                            public void onSuccess(String result) {
                                int index=0;
                                int maxLength=4000;
                                Log.e(TAG, String.valueOf(result.length()));
                                String sub1 = result.substring(0, maxLength);
                                String sub2 = result.substring(maxLength, maxLength *2);
                                String sub3 = result.substring(maxLength * 2, maxLength * 3);
                                String sub4 = result.substring(maxLength * 3, maxLength * 4);
                                String sub5 = result.substring(maxLength * 4, result.length());
                                Log.e("sub1", sub1);
                                Log.e("sub2", sub2);
                                Log.e("sub3", sub3);
                                Log.e("sub4", sub4);
                                Log.e("sub5", sub5);
                            }

                            @Override
                            public void onError(Throwable ex, boolean isOnCallback) {

                            }

                            @Override
                            public void onCancelled(CancelledException cex) {

                            }

                            @Override
                            public void onFinished() {

                            }
                        });
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });


    }


    private void getData(Handler handler) {
        String json = "{}";
        Request request=new Request.Builder()
                .get()
       .url("http://192.168.1.117/api/login/?username=dagg&password=123").build();
        OkHttpClient okHttpClient=new OkHttpClient();
        try {

            Response response=okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.i(TAG, "getData: login successfull");
          /*      Log.e(TAG, "successs");
                Log.e(TAG, response.body().string());
                Message message = Message.obtain();
                message.what = 5;
                handler.sendMessage(message);*/


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            getHomePage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                Log.e(TAG, "error");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getHomePage() throws IOException {

/*        Request request = new Request.Builder()
                .get()
                .url("http://192.168.1.117/api/homepage/").build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                Log.e("okhttp", "isOk");
                Log.e("okhttp", response.body().string());
            } else {
                throw new IOException("Unexpected code" + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        String path = "http://192.168.1.117/api/homepage/";
        // 新建一个URL对象
        URL url = new URL(path);
        // 打开一个HttpURLConnection连接
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        // 设置连接超时时间
//        urlConn.setConnectTimeout(5 * 1000);
        urlConn.setReadTimeout(12000*100);
        urlConn.setRequestProperty("cookie","csrftoken=3d5a3e6840ab1b3a6a0b440e315dc28c; sessionid=421d80ee2ef73171e2752f28559f29c7");

        // 开始连接
        urlConn.connect();
        // 判断请求是否成功
        Log.i(TAG, "getHomePage: "+urlConn.getResponseMessage()+urlConn.getResponseCode());
        byte[] data1 = readStream(urlConn.getInputStream());
        Log.i(TAG, "getHomePage 返回值:  "+new String (data1,"utf-8"));


        if (urlConn.getResponseCode() == 200) {
            // 获取返回的数据
            byte[] data = readStream(urlConn.getInputStream());
            Log.e(TAG, "Get方式请求成功，返回数据如下：");
            Log.e(TAG, new String(data, "UTF-8"));
        } else {
            Log.e(TAG, "Get方式请求失败");
        }
        // 关闭连接
        urlConn.disconnect();
        Log.e(TAG, "连接关闭");

    }

    private byte[] readStream(InputStream inputStream) throws IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buffer)) != -1)
        {
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        return outStream.toByteArray();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: 我又回来了");

    }

    @Event(value = R.id.btn_viewpager ,type= View.OnClickListener.class)
    private void viewPagerClick(View view) {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_recycler, type = View.OnClickListener.class)
    private void recyclerClick(View view) {
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_dialog, type = View.OnClickListener.class)
    private void btnDialogClick(View view) {
        DialogListAdapter listAdapter=new DialogListAdapter(this, new MainPresenter().getDialogList());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("测试");
        builder.setAdapter(listAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"hello world",Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    @Event(value = R.id.btn_start_service, type = View.OnClickListener.class)
    private void startServiceClick(View view) {
        Intent intent = new Intent(this, ServiceTest.class);
        startService(intent);
    }
    @Event(value = R.id.btn_stop_service,type = View.OnClickListener.class)
    private void stopServiceClick(View view) {
        Intent intent = new Intent(this, ServiceTest.class);
        stopService(intent);
    }

    @Event(value = R.id.btn_my_intent_service)
    private void startMyIntentServiceClick(View view) {
        Log.i(TAG, "startMyIntentServiceClick: this thread id is" + Thread.currentThread().getId());
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }

    @Event(value = R.id.btn_action_mode)
    private void actionModeClick(View view) {
        Intent intent = new Intent(this, ActionModeActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_animal,type = View.OnClickListener.class)
    private void animalClick(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_add)
    private void AddClick(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_listener)
    private void listenerClick(View view) {
        Intent intent = new Intent(this, ListenerActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_property_animation)
    private void PropertyAnimationClick(View view) {
        Intent intent = new Intent(this, PropertyAnimationActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.start_img_list)
    private void imgListClick(View view) {
        Intent intent = new Intent(this, ImageListActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_load_more)
    private void loadMoreClick(View view) {
        Intent intent = new Intent(this, LoadMoreActivity.class);
        startActivity(intent);
    }

    @Event(value = R.id.btn_index_list)
    private void indexListClick(View view) {
        Intent intent = new Intent(this, IndexListViewActivity.class);
        startActivity(intent);
    }
}
