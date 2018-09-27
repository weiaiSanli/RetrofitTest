package netework;

import java.util.Map;

import io.reactivex.Observable;


/**
 * Created by shi on 2017/2/23.
 */
public class LoginUpdate extends UseCase {
    public Map<String, String> map;

    public LoginUpdate(Map<String,String> map){
        super();
        this.map = map;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return RetrofitServiceFactory.getAppService().updateNetCall(map);
    }
}
