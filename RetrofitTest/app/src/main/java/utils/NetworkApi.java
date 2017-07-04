package utils;

import java.util.Map;

import bean.UpdateNetBean;
import retrofit2.http.GET;
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




}
