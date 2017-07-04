package water.retrofittest;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import bean.UpdateNetBean;
import netework.LoginUpdate;
import netework.ResponseSubscriber;
import netework.RetrofitServiceFactory;
import netework.UpdateFractory;
import netework.UseCase;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import utils.MyToast;

public class MainActivity extends AppCompatActivity {

    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        map = new HashMap<>();
        map.put("userName" , "xingfushuizhan");
        map.put("passWord" , "123456");

        UpdateFractory.getBuild()
                .map(map)
                .name("updateNetCall")
                .build()
                .execute(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                        System.out.println("服务器繁忙,请稍后重试");
                        MyToast.show(MainActivity.this,"服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {

                        int infoCode = updateNetBean.getInfoCode();
                        if (infoCode == 200){
                            MyToast.show(MainActivity.this,"登录成功");
                        }else{
                            MyToast.show(MainActivity.this,"商户号或密码错误");
                        }

                    }
                });

    }


    /**
     * 带有请求头token的请求
     */
    @Override
    protected void onResume() {
        super.onResume();

        //通过网络请求拿到token
        RetrofitServiceFactory.getAppService().tokenCall()
                .flatMap(new Func1<String, Observable<UpdateNetBean>>() { //使用flatMap将得到的token添加到Observable()中

                    private ArrayMap<String, String> tokenMap;

                    @Override
                    public Observable<UpdateNetBean> call(String s) {
                        tokenMap = new ArrayMap<>();
                        tokenMap.put("token", s);
                        tokenMap.put("userName", "唯爱");

                        //将token加入进来并请求网络,转化为Observable对象
                        return UpdateFractory.getBuild()
                                .name("needTokenCall")
                                .map(tokenMap)
                                .build().buildUseCaseObservable();
                    }

                 })
                .observeOn(AndroidSchedulers.mainThread()) //注意线程切换
                .subscribe(new ResponseSubscriber<UpdateNetBean>() {
            @Override
            public void onFailure(Throwable e) {
                e.printStackTrace();
                MyToast.show(MainActivity.this,"服务器繁忙,请稍后重试");
            }

            @Override
            public void onSuccess(UpdateNetBean updateNetBean) {

                int infoCode = updateNetBean.getInfoCode();
                if (infoCode == 200){
                    MyToast.show(MainActivity.this,"登录成功");
                }else{
                    MyToast.show(MainActivity.this,"商户号或密码错误");
                }
            }
        });
    }
}
