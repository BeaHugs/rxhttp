package org.beahugs.helper;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.beahugs.helper.core.RxHttp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxHttp.init(this);

        findViewById(R.id.tv_go_test_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestRequestActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.tv_go_test_download).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestDownloadActivity.class);
                startActivity(intent);
            }
        });
    }
}
