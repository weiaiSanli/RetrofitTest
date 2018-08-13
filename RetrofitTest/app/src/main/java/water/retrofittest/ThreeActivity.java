package water.retrofittest;

import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import contract.ThreeActivityContract;
import presenter.ThreeActivityPresenter;
import utils.UserContentURL;

/**
 * LifeCycle数据绑定,v7包26.1.0以上版本AppCompatActivity已经实现了getLifecycle()方法,直接调用即可,类也不在继承自LifecycleOwner
 */
public class ThreeActivity extends MvpBaseActivity<ThreeActivityPresenter> implements ThreeActivityContract.View, View.OnClickListener {


    private Button btNext;

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

        tv = findViewById(R.id.tv_end);
        bt = findViewById(R.id.bt_login);
        btNext = findViewById(R.id.bt_nex);
        btNext.setText("模拟多次请求");
        bt.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        //数据绑定一定要在oncreate方法中
        getLifecycle().addObserver(mvpPresenter);

    }


    @Override
    public String getUserName() {
        return UserContentURL.userName;
    }

    @Override
    public String getPassWord() {
        return UserContentURL.passWord;
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

        switch (v.getId()){
            case R.id.bt_login:

                //请求网址： http://www.kuaidi100.com/query?type=shunfeng&postid=820638342910
                mvpPresenter.loginNet();
                break;

            case R.id.bt_nex:
                mvpPresenter.loginInterverNet();

                break;
        }

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
