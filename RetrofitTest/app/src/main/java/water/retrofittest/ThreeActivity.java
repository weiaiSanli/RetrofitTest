package water.retrofittest;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import contract.ThreeActivityContract;
import presenter.ThreeActivityPresenter;

public class ThreeActivity extends MvpBaseActivity<ThreeActivityPresenter> implements ThreeActivityContract.View, View.OnClickListener {

    @Override
    protected ThreeActivityPresenter createPresenter() {
        return new ThreeActivityPresenter(this);
    }

    private String TAG = "SecondMvpActivity" ;
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

        Log.e("TAG" , success);
        tv.setText(success);
    }

    @Override
    public void error(String error) {
        Log.e("TAG" , error);
        tv.setText(error);
    }


    @Override
    public void onClick(View v) {
        mvpPresenter.loginNet();
    }

    @Override
    public void toast(String toast) {
        showToast(toast); //调取BaseActivity中的toast方法
    }

    @Override
    public void showLoading() {
        showToast("正在加载中..."); //调取BaseActivity中的toast方法
    }

    @Override
    public void hideLoading() {
        showToast("加载完成!");
    }
}
