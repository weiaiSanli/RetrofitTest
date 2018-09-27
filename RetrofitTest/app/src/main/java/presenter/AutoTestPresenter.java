package presenter;

import android.util.Log;
import java.util.concurrent.TimeUnit;
import contract.AutoTestContract;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import model.AutoTestModel;

/**
 * created by shi on 2018/9/27/027
 */
public class AutoTestPresenter extends MvpBasePresenter<AutoTestContract.View , AutoTestContract.Model> implements AutoTestContract.Presenter {


    private String TAG = "AutoTestPresenter" ;
    public AutoTestPresenter(AutoTestContract.View view) {
        attachView(view);
    }


    @Override
    protected AutoTestContract.Model creatModule() {
        return new AutoTestModel();
    }

    @Override
    public void setAutoPresenter() {
        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "run: this is subject is uninstall");
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(this.<Long>bindLifecycle()) //当acty在Ondestory方法中自定取消订阅的
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "accept: this is current time: " + aLong );
                    }
                });
    }
}
