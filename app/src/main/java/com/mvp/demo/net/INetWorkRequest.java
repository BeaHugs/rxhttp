package com.mvp.demo.net;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @author Wang Yi Bo
 * @date 2018/5/22
 * 作用:
 */

public interface INetWorkRequest {
    String getAccept();
    String getContentType();
    String getXEquipment();

    String getDesStr(Map<String,Object> map);

    RequestBody getRequestBody(Map<String,Object> map);
}
