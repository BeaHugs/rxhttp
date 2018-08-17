package com.mvp.demo.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mvp.demo.R;
import com.mvp.demo.utils.AnnotationsTextBind;
import com.mvp.demo.utils.InfoView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class AnnotationsTextActivity extends AppCompatActivity {

    @InfoView(R.id.zj_btn)
    Button zj_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annotations_text);
        AnnotationsTextBind.bind(this);
        zj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AnnotationsTextActivity.this,zj_btn.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
        zj_btn.getText().toString();
    }


}
