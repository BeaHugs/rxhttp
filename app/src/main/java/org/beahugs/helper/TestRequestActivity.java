package org.beahugs.helper;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.beahugs.helper.bean.BannerBean;
import org.beahugs.helper.http.FreeApi;

import java.util.List;

import io.reactivex.disposables.Disposable;
import org.beahugs.helper.core.RxLife;
import org.beahugs.helper.request.RxRequest;
import org.beahugs.helper.request.exception.BaseException;

public class TestRequestActivity extends AppCompatActivity {
    private static final String TAG = "TestRequestActivity";

    private RxLife mRxLife;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_request);

        mRxLife = RxLife.create();

        tvLog = findViewById(R.id.tv_log);

        findViewById(R.id.tv_get_weather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getBannerList();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxLife.destroy();
    }


    private void getBannerList() {

        Disposable request = RxRequest.create(FreeApi.api().getBannerList()).listener(new RxRequest.RequestListener() {
            private long timeStart = 0;

            @Override
            public void onStart() {
                log(null);
                log("onStart()");
                timeStart = System.currentTimeMillis();
            }

            @Override
            public void onError(BaseException handle) {
                log("onError(" + handle.getMessage() + ")");
            }

            @Override
            public void onFinish() {
                long cast = System.currentTimeMillis() - timeStart;
                log("onFinish(cast=" + cast + ")");
            }
        }).request(new RxRequest.ResultCallback<List<BannerBean>>() {
            @Override
            public void onSuccess(int code, List<BannerBean> data) {
                log(data.toString());
                Log.i("onSuccess", code + "");
            }

            @Override
            public void onFailed(int code, String msg) {
                Log.i("onFailed", code + "");
            }
        });

        mRxLife.add(request);
    }


    private void log(String text) {
        if (text == null) {
            tvLog.setText("");
        } else {
            Log.d(TAG, text);
            String textOld = tvLog.getText().toString();
            if (TextUtils.isEmpty(textOld)) {
                tvLog.setText(text);
            } else {
                tvLog.setText(tvLog.getText().toString() + "\n" + text);
            }
        }
    }
}
