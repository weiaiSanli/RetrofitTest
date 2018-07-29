package water.retrofittest;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import components.AppComponent;
import components.DaggerFourthActivityComponent;
import contract.FourthContract;
import module.ActivityModule;
import module.FourthActivityModule;
import presenter.FourthPresenter;

public class FourthActivity extends BaseActivity implements FourthContract.View, View.OnClickListener {

    @Inject
    FourthPresenter presenter;
    private TextView tv;
    private Button bt;

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


        Toast.makeText(activity, "this is FourthActivity" + activity.getLocalClassName(), Toast.LENGTH_SHORT).show();

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

        presenter.loginNet();
    }
}