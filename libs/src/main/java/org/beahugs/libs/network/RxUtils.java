package org.beahugs.libs.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import org.beahugs.libs.network.base.OkHttpInfo;
import org.beahugs.libs.network.cookie.CookieJarImpl;
import org.beahugs.libs.network.cookie.store.CookieStore;
import org.beahugs.libs.network.updownload.DownloadHelper;
import org.beahugs.libs.network.base.BaseApiCacheFactory;
import org.beahugs.libs.network.manage.RxHttpManager;
import org.beahugs.libs.network.updownload.UploadHelper;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
/**
 * @Author: wangyibo
 * @Version: 1.0
 */

public class RxUtils {

    @SuppressLint("StaticFieldLeak")
    private static RxUtils instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RxUtils getInstance() {
        if (instance == null) {
            synchronized (RxUtils.class) {
                if (instance == null) {
                    instance = new RxUtils();
                }
            }

        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public RxUtils init(Application app) {
        context = app;
        return this;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxUtils.getInstance().init(this) 初始化！");
        }
    }


    public BaseApiCacheFactory config() {
        checkInitialize();
        return BaseApiCacheFactory.getInstance();
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public static <K> K createApi(Class<K> cls) {
        return BaseApiCacheFactory.getInstance().createApi(cls);
    }

    /**
     * 切换baseUrl
     *
     * @param baseUrlKey   域名的key
     * @param baseUrlValue 域名的url
     * @param cls          class
     * @param <K>          k
     * @return k
     */
    public static <K> K createApi(String baseUrlKey, String baseUrlValue, Class<K> cls) {
        return BaseApiCacheFactory.getInstance().createApi(baseUrlKey, baseUrlValue, cls);
    }


    /**
     * 下载文件
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadHelper.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadHelper.uploadImage(uploadUrl, filePath);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths) {
        return UploadHelper.uploadImages(uploadUrl, filePaths);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param fileName  后台接收文件流的参数名
     * @param paramsMap 参数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> filePaths) {
        return UploadHelper.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths);
    }

    /**
     * 获取全局的CookieJarImpl实例
     */
    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpInfo.getInstance().getOkHttpClient().cookieJar();
    }

    /**
     * 获取全局的CookieStore实例
     */
    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    /**
     * 获取所有cookie
     */
    public static List<Cookie> getAllCookie() {
        CookieStore cookieStore = getCookieStore();
        List<Cookie> allCookie = cookieStore.getAllCookie();
        return allCookie;
    }

    /**
     * 获取某个url所对应的全部cookie
     */
    public static List<Cookie> getCookieByUrl(String url) {
        CookieStore cookieStore = getCookieStore();
        HttpUrl httpUrl = HttpUrl.parse(url);
        List<Cookie> cookies = cookieStore.getCookie(httpUrl);
        return cookies;
    }


    /**
     * 移除全部cookie
     */
    public static void removeAllCookie() {
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeAllCookie();
    }

    /**
     * 移除某个url下的全部cookie
     */
    public static void removeCookieByUrl(String url) {
        HttpUrl httpUrl = HttpUrl.parse(url);
        CookieStore cookieStore = getCookieStore();
        cookieStore.removeCookie(httpUrl);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * 取消某个或某些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}
