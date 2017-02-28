package com.dyoon.myapplication.http;

import com.dyoon.myapplication.model.User;
import com.dyoon.mylibrary.MyLibrary;
import com.google.gson.Gson;

import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by jun on 2016/11/3.
 */


public class JsonResonseParser  implements ResponseParser{
    @Override
    public void checkResponse(UriRequest request) throws Throwable {

    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        if (resultClass == List.class) {
            Gson gson = new Gson();
            User user = gson.fromJson(result, User.class);
            return user;

        } else {
            Gson gson = new Gson();
            User user = gson.fromJson(result, User.class);
            return user;
        }

    }
}
