package presenter;

import java.util.Map;

import netework.UseCase;
import retrofit2.Retrofit;
import rx.Observable;
import utils.NetworkApi;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/10 17:09
 */
public class MainRetrofit extends UseCase {


    private Retrofit retrofit ;
    private Map<String , String> map ;

    public MainRetrofit(Retrofit retrofit, Map<String, String> map) {
        this.retrofit = retrofit;
        this.map = map;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return retrofit.create(NetworkApi.class).updateNetCall(map);
    }
}
