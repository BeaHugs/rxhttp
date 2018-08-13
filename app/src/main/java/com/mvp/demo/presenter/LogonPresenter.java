package com.mvp.demo.presenter;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mvp.demo.base.IBaseDataBack;
import com.mvp.demo.model.LogonModel;
import com.mvp.demo.base.BasePresenter;
import com.mvp.demo.base.BaseData;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.base.BaseActivity;
import com.mvp.demo.utils.MyApplication;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Wang Yi Bo
 * @date 2018/5/24
 * 作用:
 */

public class LogonPresenter<V extends IBaseDataBack> extends BasePresenter {

   private LogonModel<BaseData<LoginBean>> logonModel;

    public LogonPresenter(V mview) {
        super(mview);
        logonModel = new LogonModel<BaseData<LoginBean>>(MyApplication.getDefault());
    }
    @Override
    public void loadData(Map<String, Object> map, Application application) {
       logonModel.loadData(map, new Callback<BaseData<LoginBean>>() {
           @Override
           public void onResponse(Call<BaseData<LoginBean>> call, Response<BaseData<LoginBean>> response) {
               mview.showSuccess(response.body().ReturnMessage);
           }

           @Override
           public void onFailure(Call<BaseData<LoginBean>> call, Throwable t) {

           }
       });
    }

    @Override
    public void loadData2(Map<String, Object> map, Application application) {
        logonModel.loadData2(map, new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("xxx",response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
