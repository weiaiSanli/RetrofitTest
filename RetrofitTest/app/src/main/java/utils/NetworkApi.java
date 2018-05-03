package utils;

import java.util.Map;

import bean.UpdateNetBean;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;


/**
 * Created by shiqiang on 2017/3/14.
 */
public interface NetworkApi {


    /**
     * 登录界面的请求数据 addressAdd
     */

    @GET("delapp/userLogin.action")
    Observable<UpdateNetBean> updateNetCall(@QueryMap Map<String, String> map);


    /**
     * 带有token请求的
     */
    @GET("token")
    Observable<String> tokenCall();


    /**
     * 需要token请求的
     */

    @GET("delapp/userLogin.action")
    Observable<UpdateNetBean> needTokenCall(@QueryMap Map<String, String> map);


    /**
     * post请求时候使用
     */

    @FormUrlEncoded
    @POST("goods/bids")
    Observable<UpdateNetBean> oneSingleBidCall(
            @FieldMap Map<String, String> map);



}
