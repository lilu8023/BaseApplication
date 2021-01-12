package com.lilu.application.function.statuslayout;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Description:
 *
 * @author lilu on 2020/12/30
 * No one knows this better than me
 */
public interface TestHttpApiService {

    @GET("/wxarticle/chapters/json")
    Observable<String> getPublic();

}
