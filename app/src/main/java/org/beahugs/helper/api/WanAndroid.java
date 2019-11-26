package org.beahugs.helper.api;

import org.beahugs.helper.bean.PublicNumbersBean;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @Author: wangyibo
 * @Version: 1.0
 */
public interface WanAndroid {

    @GET("/wxarticle/chapters/json")
    Flowable<PublicNumbersBean> FgetListofPublicNumbers();

    @GET("/wxarticle/chapters/json")
    Observable<PublicNumbersBean> getListofPublicNumbers();

}
