package water.retrofittest.autodisponse;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.concurrent.TimeUnit;

import contract.AutoTestContract;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import presenter.AutoTestPresenter;
import water.retrofittest.MvpBaseActivity;
import water.retrofittest.R;

/**
 * created by shi on 2018/9/27/027
 */
public class AutoDisponseTestActivity extends MvpBaseActivity<AutoTestPresenter> implements AutoTestContract.View, View.OnClickListener {

    private TextView tv_number;
    private String TAG = "shiq_";

    @Override
    protected AutoTestPresenter createPresenter() {
        return new AutoTestPresenter(this);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_auto;
    }

    @Override
    protected void initView() {

        tv_number = findViewById(R.id.tv_number);
        Button bt_acty = findViewById(R.id.bt_acty);
        Button bt_pre = findViewById(R.id.bt_pre);
        bt_acty.setOnClickListener(this);
        bt_pre.setOnClickListener(this);

    }

    @Override
    protected void initData() {

        //数据绑定一定要在oncreate方法中
        getLifecycle().addObserver(mvpPresenter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.bt_acty:
                setAutoInActy();
                break;

            case R.id.bt_pre:

                mvpPresenter.setAutoPresenter();

                break;


        }
    }

    /**
     * 在activity中使用
     */
    private void setAutoInActy() {
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "run: this is subject is uninstall");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(AutoDispose.<Long>autoDisposable(AndroidLifecycleScopeProvider.from(this))) //当acty在Ondestory方法中自定取消订阅的
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "accept: this is current time: " + aLong );
                        tv_number.setText("time:" + aLong);
                    }
                });
    }
}
