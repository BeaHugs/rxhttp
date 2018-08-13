package com.mvp.demo;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.presenter.LogonPresenter;
import com.mvp.demo.utils.DemoFragment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator Wang  on 2018/3/9.
 */

public class MainActivity extends BaseActivity<LoginBean, LogonPresenter> implements View.OnClickListener {

    @BindView(R.id.dialog_layout_text_input)
    EditText dialogLayoutTextInput;
    private Button btn_click;
    private Button btn_select;
    private TextView tvById;
    private TextView tv_text;

    @Override
    protected int initView() {
        return R.layout.activity_main;
    }

    @SuppressLint("CommitTransaction")
    @Override
    protected void initUI() {

        try {
            URL url = new URL("http://219.143.6.91:8766");
            String host = url.getHost();
            Log.i("xxx",host);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        tvById = findViewById(R.id.tv);
        btn_click = findViewById(R.id.btn_click);
        btn_select = findViewById(R.id.btn_select);


        DemoFragment demoFragment = new DemoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.frame, demoFragment).commit();


        DecimalFormat myformat = new DecimalFormat();
        myformat.applyPattern("##,###.00");
        tvById.setText(myformat.format(999999999));

        tv_text = findViewById(R.id.tv_text);
        btn_select.setOnClickListener(this);
        btn_click.setOnClickListener(this);

        SpannableStringBuilder ssb = new SpannableStringBuilder(tvById.getText().toString());
        ForegroundColorSpan red = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan yellow = new ForegroundColorSpan(Color.YELLOW);
        ssb.setSpan(red, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ssb.setSpan(yellow, 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvById.setText(ssb);


        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

//        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//
//// 接受软键盘输入的编辑文本或其它视图
//
//        inputMethodManager.showSoftInput(dialogLayoutTextInput,InputMethodManager.SHOW_FORCED);
//        dialogLayoutTextInput.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        //getKeyBoard(tv_text);

        String assetsData = getAssetsData("sort.json");
        Log.i("json",assetsData);







    }
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private Button btn_Options;
    private Button btn_CustomOptions;
    private Button btn_CustomTime;

    private TimePickerView pvTime, pvCustomTime, pvCustomLunar;
    private OptionsPickerView pvOptions, pvCustomOptions, pvNoLinkOptions;
    //private ArrayList<CardBean> cardItem = new ArrayList<>();

    private ArrayList<String> food = new ArrayList<>();
    private ArrayList<String> clothes = new ArrayList<>();
    private ArrayList<String> computer = new ArrayList<>();

    @Override
    protected void initData() {
        InputStream abpath = getClass().getResourceAsStream("/assets/sort");


        String path = new String(InputStreamToByte(abpath ));
        Log.i("xxx",path);
    }

    private byte[] InputStreamToByte(InputStream is) {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;

        try {
            while ((ch = is.read()) != -1) {
                bytestream.write(ch);
            }
            byte imgdata[] = bytestream.toByteArray();
            bytestream.close();
            return imgdata;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void initListener() {

    }

    @Override
    public LogonPresenter getPresenter() {
        return new LogonPresenter(this);
    }

    @Override
    public boolean isRegisterEventBus() {
        return false;
    }

public void getdata(){
        Log.i("xxx","aaaaaaaaaaaa");
}
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                Map map = new HashMap();
                Map map1 = new HashMap();//http://192.168.100.15:8080/userInformation
                map1.put("LoginName", "14300000000");
                map1.put("LoginPass", "123456");
                map.put("AppointEntity", map1);
                mpre.loadData(map, getApplication());
                break;
            case R.id.btn_select:
                Map mapp = new HashMap<>();
                mapp.put("PageSize", 15);
                mapp.put("PageIndex", 1);
                Map<String, Object> childMap = new HashMap<>();
                childMap.put("Type", 1);
                mapp.put("AppointEntity", childMap);
                mpre.loadData2(mapp, getApplication());
                break;
        }
    }


    @Override
    public void showSuccess(LoginBean loginBean) {
        tv_text.setText(loginBean.toString());
    }

    @Override
    public void showError(String t) {
        tv_text.setText(t);
    }

    //从资源文件中获取分类json
    public String getAssetsData(String path) {
        String result = "";
        try {
            //获取输入流
            InputStream mAssets = getAssets().open(path);
            //获取文件的字节数
            int lenght = mAssets.available();
            //创建byte数组
            byte[] buffer = new byte[lenght];
            //将文件中的数据写入到字节数组中
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("fuck", e.getMessage());
            return result;
        }
    }

}
