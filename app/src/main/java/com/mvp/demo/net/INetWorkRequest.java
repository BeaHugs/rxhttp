package com.mvp.demo.net;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * @author Wang Yi Bo
 * @date 2018/5/22
 * 作用:
 */

public interface INetWorkRequest {

    public String XEquipment_Key = "X-Equipment";
    public String XEquipment_Value = "终端类型";

    public String Accept_Key = "Accept";
    public String Accept_Value = "application/json";

    public String ContentType_Value = "application/json; charset=utf-8";


//    String getAccept();
 //   String getContentType();
//    String getXEquipment();

     String getDesStr(Map<String, Object> map);


    //请求参数
    Map<String,Object> parameter();


    //请求参数
    public void response(Flowable<ResponseBody> responseBodyFlowable, HttpObserver iRequestCallBack);

    public void upFile(String url, Map<String, Object> map, HttpObserver iRequestCallBack);

    public void inRequest(String url, Map<String, Object> map, HttpObserver iRequestCallBack);


}
