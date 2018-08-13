package model;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import bean.UpdateNetBean;
import contract.ThreeActivityContract;
import info.LoginNetInfo;
import netework.ResponseSubscriber;
import netework.UpdateFractory;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class ThreeActivityModel implements ThreeActivityContract.Model<LoginNetInfo> {


    private Subscription subscribe;


    @Override
    public void loginNet(String userName, String pwd, final LoginNetInfo info) {
        HashMap<String , String> map = new HashMap();
        map.put("userName" , userName);
        map.put("passWord" , pwd);

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

    @Override
    public void loginInterverNet() {

        //没有取消一直都存在
        subscribe = Observable.interval(2, TimeUnit.SECONDS)
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        System.out.println("我是接受到的消息" + aLong);
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (!subscribe.isUnsubscribed()){
            subscribe.unsubscribe();
        }
    }
}
