package org.beahugs.helper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.beahugs.libs.network.RxHttpUtils;
import org.beahugs.libs.network.base.OkHttpInfo;
import org.beahugs.libs.network.interfaces.BuildHeadersListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        new OkHttpInfo.Builder(this).setHeaders(new BuildHeadersListener() {
            @Override
            public Map<String, String> buildHeaders() {

                return null;
            }
        }).build();


        //RxHttpUtils.getInstance().init(this).config().

    }
}
