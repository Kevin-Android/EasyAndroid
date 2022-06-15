package com.kevin.easyandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.OkHttpClient;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton but1;
    private AppCompatButton but2;
    private AppCompatButton but3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but1 = findViewById(R.id.but1);
        but2 = findViewById(R.id.but2);
        but3 = findViewById(R.id.but3);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);
        but3.setOnClickListener(this);
        findViewById(R.id.but4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == but1.getId()) {
            startActivity(new Intent(this, SplashActivity.class));
        } else if (view.getId() == but2.getId()) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (view.getId() == but3.getId()) {
            requestBaidu();
        }
    }

    private void requestBaidu() {
//        OkHttpClient.Builder().

        GetThread baiduThread = new GetThread();
        baiduThread.start();
    }

    private class GetThread extends Thread {
        @Override
        public void run() {

            try {
                URL url = new URL("http://collect.magicdatatech.com/#/privacyAgreement");
                HttpURLConnection connection = null;
                connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(30 * 1000);//设置超时时长，单位ms
                connection.setRequestMethod("GET");//设置请求格式
                connection.setRequestProperty("Content-Type", "Application/json");//期望返回的数据格式
                connection.setRequestProperty("CharSet", "UTF-8");//设置字符集
                connection.setRequestProperty("Accept-CharSet", "UTF-8");//请求的字符集
                connection.connect();//发送请求
                int responseCode = connection.getResponseCode();//获取返回码
                String responseMessage = connection.getResponseMessage();//获取返回信息
                //请求成功操作
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    Log.e("百度", "请求成功:" + responseMessage);
                } else {
                    Log.e("百度", "请求失败:" + responseMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("百度", "请求异常" + e.getMessage());
            }

        }
    }

}