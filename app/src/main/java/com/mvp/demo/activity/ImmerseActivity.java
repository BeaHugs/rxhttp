package com.mvp.demo.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.RelativeLayout;



import com.mvp.demo.R;

import com.mvp.demo.utils.GlideUtils;
import com.mvp.demo.utils.LQRPhotoSelectUtils;

import com.mvp.demo.utils.StatusBarUtils;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class ImmerseActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout re_layout;
    private Button btn_pz;
    private Button btn_xc;
    private ImageView img_view;
    private ImageView imageView;
    private LQRPhotoSelectUtils photoSelectUtils;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immerse);
        getSupportActionBar().hide();
        statusBar();
        re_layout = findViewById(R.id.re_layout);
        btn_pz = findViewById(R.id.btn_pz);
        btn_xc = findViewById(R.id.btn_xc);
        img_view = findViewById(R.id.img_view);
        btn_pz.setOnClickListener(this);
        btn_xc.setOnClickListener(this);


        /**
         * 深颜色
         */
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        /**
         * 浅颜色
         */
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                GlideUtils.loadImageView(ImmerseActivity.this,outputUri.getPath(),img_view);
            }
        },true);
    }

    private void statusBar() {
        StatusBarUtils.fullScreen(this);
        StatusBarUtils.FlymeSetStatusBarLightMode(getWindow(), false);
        StatusBarUtils.MIUISetStatusBarLightMode(getWindow(), false);
        StatusBarUtils.setBarTextColor(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pz:
                mLqrPhotoSelectUtils.camearPer();
                break;
            case R.id.btn_xc:
                // 3、调用从图库选取图片方法
                mLqrPhotoSelectUtils.photoPer();
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }
    /**
     * 调取系统相机相关的配置
     */
    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void takePhoto() {
        mLqrPhotoSelectUtils.takePhoto();
    }

    @PermissionSuccess(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void selectPhoto() {
        mLqrPhotoSelectUtils.selectPhoto();
    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_TAKE_PHOTO)
    private void showTip1() {

    }

    @PermissionFail(requestCode = LQRPhotoSelectUtils.REQ_SELECT_PHOTO)
    private void showTip2() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mLqrPhotoSelectUtils.attachToActivityForResult(requestCode, resultCode, data);
    }
}