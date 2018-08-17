package com.mvp.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.mvp.demo.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator Wang  on 2018/4/4.
 */

public class WinActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Toast.makeText(this, "这个是一个窗口Activity", Toast.LENGTH_LONG).show();


        OkHttpClient mOkHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url("https://www.jianshu.com/u/b4e69e85aef6").addHeader("user_agent", "22222").build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null){}

            }
        });


    }
}
