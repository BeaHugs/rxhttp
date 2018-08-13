package com.mvp.demo.net;

import com.mvp.demo.BuildConfig;

/**
 * @author Wang Yi Bo
 * @date 2018/5/23
 * 作用:
 */

public class ApiUrl {
    public static String HTTPHOST(){
        if (BuildConfig.LOG_DEBUG){
            return BuildConfig.address;
        }
        return BuildConfig.address;
    }
}
