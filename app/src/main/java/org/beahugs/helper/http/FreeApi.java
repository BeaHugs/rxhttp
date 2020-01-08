package org.beahugs.helper.http;

import org.beahugs.helper.bean.BannerBean;

import java.util.List;

import io.reactivex.Observable;
import org.beahugs.helper.network.RxUtils;
import retrofit2.http.GET;


public class FreeApi {

    public static Service api() {
        return RxUtils.createApi(Service.class);
        //return Api.api(Service.class);
    }




    public interface Service {

        /**
         * 获取天气
         */
        @GET("/banner/json")
        Observable<ResponsBean<List<BannerBean>>> getBannerList();

    }
}
