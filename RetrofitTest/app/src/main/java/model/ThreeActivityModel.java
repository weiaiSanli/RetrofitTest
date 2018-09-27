package model;

import android.annotation.SuppressLint;

import org.reactivestreams.Subscription;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import bean.UpdateNetBean;
import contract.ThreeActivityContract;
import info.LoginNetInfo;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import netework.ResponseSubscriber;
import netework.UpdateFractory;

public class ThreeActivityModel implements ThreeActivityContract.Model<LoginNetInfo> {


    private Disposable subscribe;


    @Override
    public void loginNet(String userName, String pwd, final LoginNetInfo info) {
        HashMap<String, String> map = new HashMap();
        map.put("userName", userName);
        map.put("passWord", pwd);

        UpdateFractory.getBuild()
                .map(map)
                .name("updateNetCall")
                .build()
                .execute(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                        System.out.println("服务器繁忙,请稍后重试");
                        info.loginNetError("服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {
                        info.loginNetSuccess(updateNetBean.getMessage());
                    }
                });


    }

    @SuppressLint("CheckResult")
    @Override
    public void loginInterverNet() {

        //没有取消一直都存在


        subscribe = Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                               @Override
                               public void accept(Long aLong) throws Exception {
                                   System.out.println("我是接受到的消息" + aLong);
                               }
                           }
                );
    }

    @Override
    public void onDestroy() {
        if (!subscribe.isDisposed()) {
            subscribe.dispose();
        }
    }
}
