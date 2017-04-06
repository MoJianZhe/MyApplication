package com.dyoon.myapplication.model;

import android.app.Activity;

import com.dyoon.myapplication.http.JsonResonseParser;

import org.xutils.http.annotation.HttpResponse;

import java.io.Serializable;

/**
 * Created by jun on 2016/11/3.
 */
@HttpResponse(parser = JsonResonseParser.class)
public class User implements Serializable {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    /**
     * 用户名
     */
    private String username;
    /**
     * 名
     */
    private String last_name;
    /**
     * 姓
     */
    private String first_name;

    /**
     * email
     */
    private String email;
    /**
     * 最后登录时间
     */
    private String lastLogin;
    /**
     * 登录状态
     */
    private int loginState;

    /*----------------get/set------------------------ */


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getLoginState() {
        return loginState;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    /*---------------------toString---------------------------*/

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", last_name='" + last_name + '\'' +
                ", first_name='" + first_name + '\'' +
                ", email='" + email + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", loginState=" + loginState +
                '}';
    }
}
