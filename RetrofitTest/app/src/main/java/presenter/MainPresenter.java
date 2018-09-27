package presenter;

import android.util.AndroidException;
import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import bean.UpdateNetBean;
import contract.MainContract;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.ResourceObserver;
import io.reactivex.schedulers.Schedulers;
import netework.ResponseSubscriber;
import retrofit2.Retrofit;
import utils.NetworkApi;

/**
 * 类描述：使用inject将p引入到M
 * 创建人： shi
 * 创建时间:2017/7/7 15:54
 */
public class MainPresenter implements MainContract.Presenter {


    private Map<String, String> map;

    private MainContract.View mView;
    private Retrofit retrofit;

    @Inject
    public MainPresenter(MainContract.View mView, Retrofit retrofit) {

        this.mView = mView ;
        this.retrofit = retrofit;

    }


    @Override
    public void loginNet() {

        map = new HashMap();
        map.put("userName" , mView.getUserName());
        map.put("passWord" , mView.getPassWord());
        System.out.println("MainPresenter中的Retrofit地址为:" + retrofit.toString() + mView.getUserName() + mView.getPassWord());
        retrofit.create(NetworkApi.class).updateNetCall(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {
                        e.printStackTrace();
                        mView.error("服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {
                            mView.loginSuccess(updateNetBean.getMessage());
                    }
                });
    }
}
