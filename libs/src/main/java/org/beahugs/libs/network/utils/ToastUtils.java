package org.beahugs.libs.network.utils;

import android.widget.Toast;

import org.beahugs.libs.network.RxHttpUtils;


/**
 * @Author: wangyibo
 * @Version: 1.0
 */

public class ToastUtils {

    private static Toast mToast;

    /**
     * Toast提示
     *
     * @param msg 提示内容
     */
    public static void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(RxHttpUtils.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
