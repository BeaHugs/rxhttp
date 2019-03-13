package com.mvp.demo.net;


import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

/**
 * @author Wang Yi Bo
 * @date 2018/5/23
 * 作用:
 */

public interface ApiService {


    @POST()
    @FormUrlEncoded
    Flowable<ResponseBody> requestParams(@Url String url, @FieldMap Map<String, Object> params);

    @POST()
    Flowable<ResponseBody> requestFile(@Url String url, @Body RequestBody params);
}
