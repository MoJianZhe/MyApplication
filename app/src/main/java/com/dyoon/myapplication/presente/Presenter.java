package com.dyoon.myapplication.presente;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by jun on 2016/11/1.
 */

public class Presenter {
    private String name;



    public void  gotoSecond(final Handler handler)  {
        RequestParams requestParams = new RequestParams("http://192.168.1.117/api/login/?username=dagg&password=123");

        x.http().get(requestParams, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Log.e("success", "success method start");
                Message m=Message.obtain();
                m.obj=result;
                handler.sendMessage(m);

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(), "cuowu ", Toast.LENGTH_SHORT).show();
                Log.e("error", "wangluo chu cuo ");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
   /*    try {
            User u =x.http().getSync(requestParams, User.class);
           Log.e("test", u.toString());

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            Log.e("getSync", "tong bu qing qiu chu cuo ");

        }*/
    }
    //解析String json数据
}
