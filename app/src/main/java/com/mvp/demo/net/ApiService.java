package com.mvp.demo.net;

import com.mvp.demo.base.BaseData;
import com.mvp.demo.bean.LoginBean;
import com.mvp.demo.bean.RedBean;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Wang Yi Bo
 * @date 2018/5/23
 * 作用:
 */

public interface ApiService {
    /**
     * 登录接口
     * @param strJson
     * @param callback
     * @return
     */
    @POST("Customer/Logins")
    Call<BaseData<String>> login(@Body RequestBody strJson);

    /**
     * 查询红包
     * @param strJson
     * @return
     */
    @POST("YPRedPackage/GetMyTicket")
    Call<String> selectRed(@Body RequestBody strJson);
}
