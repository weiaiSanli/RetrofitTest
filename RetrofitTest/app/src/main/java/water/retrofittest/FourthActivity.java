package water.retrofittest;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import components.AppComponent;
import components.DaggerFourthActivityComponent;
import contract.FourthContract;
import module.ActivityModule;
import module.FourthActivityModule;
import module.Named;
import presenter.FourthPresenter;

public class FourthActivity extends BaseActivity implements FourthContract.View, View.OnClickListener {

    @Inject
    @Named("presenter")
    FourthPresenter presenter;

//    @Inject
//    @Named("presenter2")
//    FourthPresenter presenter2;


    private TextView tv;
    private Button bt;

    @Inject
    Activity activity ; //公共的activity,得到的对象就是当前的FourthActivity
    @Inject
    Context context ;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    /**
     * 第二种方式导入presenter
     * 重写基类的setupActivityComponent()方法
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

        DaggerFourthActivityComponent.builder()
                .appComponent(appComponent)
                .fourthActivityModule(new FourthActivityModule(this))
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }


    @Override
    protected void initView() {
        tv = (TextView) findViewById(R.id.tv_end);
        bt = (Button) findViewById(R.id.bt_login);
        bt.setOnClickListener(this);
    }

    @Override
    protected void initData() {

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

        Log.e("TAG", success);
        tv.setText(success);
    }

    @Override
    public void error(String error) {
        Log.e("TAG", error);
        tv.setText(error);
    }


    @Override
    public void onClick(View v) {

        System.out.println("我是当前的activity" + activity.getClass().getName() + "context:" + context.getPackageName());

        presenter.loginNet();
    }
}