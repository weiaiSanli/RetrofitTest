package water.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import components.AppComponent;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import utils.MyToast;
import utils.ToastUtil;

/**
 * Created by shi on 2017/3/23.
 */

public abstract class BaseActivity extends AppCompatActivity {



    protected Context mContext ;




    //两者方式关联App里面的唯一Component管理类,这是第一种,下面是第二种方式,在子类中重写setupActivityComponent()即可
    public AppComponent getAppComponent(){
        return ((App)getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这是第二种:获取App中的component注入器即可使用context,Retrofit等全局对象
        setupActivityComponent(((App)getApplication()).getAppComponent());
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(getLayoutResourceId());

        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
        initListener();
        initData();
        initBundle(savedInstanceState);


    }



    protected void setupActivityComponent(AppComponent appComponent){}

    /**
     * 获取资源布局id
     *
     * @return 资源布局id
     */
    protected abstract int getLayoutResourceId();


    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * Bundle保存activity实例
     */
    protected void initBundle(Bundle bundle){
        System.out.println("我是base里面的Bundle");

    };

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 监听事件处理
     */
    protected void initListener(){};

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 切换线程,使其在子线程中也能执行,有时候不注意在子线程中调取弹出toast的方法了
     * @param toast
     */
    protected void showToast(String toast){

        Observable.just(toast)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String toast) {
                        boolean onMainThread = isOnMainThread();
                        //如果当前是主线程,直接弹出toast,并且不再向下执行了
                        if (onMainThread){
                            MyToast.show(mContext , toast);
                        }
                        return !onMainThread;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String toast) {
                        MyToast.show(mContext , toast);
                    }
                });

    }


    /**
     * 判断是否在当前主线程
     * @return
     */
    public boolean isOnMainThread(){
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据页面跳转
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * startActivityForResult
     *
     * @param clazz       目标Activity
     * @param requestCode 发送判断值
     */
    protected void startActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }







}
