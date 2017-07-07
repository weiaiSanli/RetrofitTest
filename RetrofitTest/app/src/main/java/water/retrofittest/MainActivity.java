package water.retrofittest;

import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import bean.UpdateNetBean;
import components.AppComponent;
import components.DaggerMainActivityComponent;
import contract.MainContract;
import module.ActivityModule;
import module.MainActivityModule;
import netework.LoginUpdate;
import netework.ResponseSubscriber;
import netework.RetrofitServiceFactory;
import netework.UpdateFractory;
import netework.UseCase;
import presenter.MainPresenter;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import utils.MyToast;

public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener {


    @Inject
    MainPresenter presenter ;
    private TextView tv;
    private Button bt;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        tv = (TextView) findViewById(R.id.tv_end);
        bt = (Button) findViewById(R.id.bt_login);

        bt.setOnClickListener(this);


    }

    /**
     * 第二种方式导入presenter
     * 重写基类的setupActivityComponent()方法
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .activityModule(new ActivityModule(this)).build()
                .inject(this);
    }




    @Override
    protected void initData() {
        /**
         * 第一种方式导入presenter
         */
      /*  DaggerMainActivityComponent.builder()
                .appComponent(getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .activityModule(new ActivityModule(this)).build()
                .inject(this);*/



    }




    /**
     * 带有请求头token的请求封装,注意网络请求是在订阅的时候开始的.subscribe()方法的时候
     */
    @Override
    protected void onResume() {
        super.onResume();

      /*  //通过网络请求拿到token
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
        });*/
    }

    @Override
    public String getUserName() {
        return "xingfushuizhan";
    }

    @Override
    public String getPassWord() {
        return "123456";
    }

    @Override
    public void loginSuccess(String success) {

        System.out.println(success + "caicai");
        tv.setText(success);
    }

    @Override
    public void error(String error) {
        System.out.println(error + "caicai");
        tv.setText(error);
    }

    @Override
    public void onClick(View v) {

        presenter.loginNet();



    }
}
