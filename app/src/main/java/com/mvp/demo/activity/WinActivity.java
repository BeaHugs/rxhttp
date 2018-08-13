package com.mvp.demo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.mvp.demo.R;

/**
 * Created by Administrator Wang  on 2018/4/4.
 */

public class WinActivity extends Activity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        Toast.makeText(this, "这个是一个窗口Activity", Toast.LENGTH_LONG).show();
    }
}
