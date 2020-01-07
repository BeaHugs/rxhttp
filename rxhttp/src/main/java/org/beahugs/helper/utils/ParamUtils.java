package org.beahugs.helper.utils;


import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public class ParamUtils {

    private Map<String, Object> params;


    public ParamUtils addParams(String key, Object value) {
        if (params == null) {
            params = new TreeMap<>();
        }

        params.put(key, value);
        return this;
    }

    public Map getParams() {
        if (params == null) {
            return null;
        }

        return params;
    }

    public void clearParams() {
        if (params != null)
            params.clear();
    }
}
