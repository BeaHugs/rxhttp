package com.mvp.demo.activity;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;

import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mvp.demo.R;

import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.utils.GlideUtils;
import com.mvp.demo.utils.LQRPhotoSelectUtils;

import com.mvp.demo.utils.StatusBarUtils;
import com.mvp.demo.view.ObservableScrollView;


import java.io.File;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

/**
 * https://blog.csdn.net/hmmhhmmhmhhm/article/details/77840604 状态栏博客
 */
public class ImmerseActivity extends BaseActivity implements View.OnClickListener, ObservableScrollView.OnObservableScrollViewListener {

    private Button btn_pz;
    private Button btn_xc;
    private ImageView img_view;
    private LQRPhotoSelectUtils mLqrPhotoSelectUtils;

    private ObservableScrollView mObservableScrollView;
    private TextView mImageView;
    private LinearLayout mHeaderContent;

    private int mHeight;

    @Override
    protected int initView() {
        return R.layout.activity_immerse;
    }

    @Override
    protected void initUI() {
        statusBar();
        btn_pz = findViewById(R.id.btn_pz);
        btn_xc = findViewById(R.id.btn_xc);
        img_view = findViewById(R.id.img_view);
        btn_pz.setOnClickListener(this);
        btn_xc.setOnClickListener(this);
        //初始化控件
        mObservableScrollView = (ObservableScrollView) findViewById(R.id.sv_main_content);
        mImageView = (TextView) findViewById(R.id.tv_main_topContent);
        mHeaderContent = (LinearLayout) findViewById(R.id.re_layout);
        mHeaderContent.getBackground().mutate().setAlpha(0);
        //获取标题栏高度
        ViewTreeObserver viewTreeObserver = mImageView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                mImageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHeight = mImageView.getHeight() - mHeaderContent.getHeight();
                //这里取的高度应该为图片的高度-标题栏 //注册滑动监听
                mObservableScrollView.setOnObservableScrollViewListener(ImmerseActivity.this);
            }
        });

        /**
         * 深颜色
         */
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        /**
         * 浅颜色
         */
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        mLqrPhotoSelectUtils = new LQRPhotoSelectUtils(this, new LQRPhotoSelectUtils.PhotoSelectListener() {
            @Override
            public void onFinish(File outputFile, Uri outputUri) {
                GlideUtils.loadImageView(ImmerseActivity.this, outputUri.getPath(), img_view);
            }
        }, true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    private void statusBar() {
        StatusBarUtils.fullScreen(this);
        StatusBarUtils.FlymeSetStatusBarLightMode(getWindow(), false);
        StatusBarUtils.MIUISetStatusBarLightMode(getWindow(), false);
        StatusBarUtils.setBarTextColor(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    @Override
    public void showSuccess(Object o) {

    }

    @Override
    public void showError(String t) {

    }

    @Override
    public void onObservableScrollViewListener(int l, int t, int oldl, int oldt) {

        if (t <= 0) {
            //状态栏浅颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //顶部图处于最顶部，标题栏透明
            mHeaderContent.setBackgroundColor(Color.argb(0, 255, 255, 255));
        } else if (t > 0 && t < mHeight) {
            //状态栏深颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //滑动过程中，渐变
            float scale = (float) t / mHeight;
            //算出滑动距离比例
            float alpha = (255 * scale);
            //得到透明度
            mHeaderContent.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
        } else {
            //状态栏深颜色
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //过顶部图区域，标题栏定色
            mHeaderContent.setBackgroundColor(Color.argb(255, 255, 255, 255));
        }
    }
}