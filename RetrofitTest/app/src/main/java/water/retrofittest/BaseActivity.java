package water.retrofittest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import components.AppComponent;

/**
 * Created by shi on 2017/3/23.
 */

public abstract class BaseActivity extends AppCompatActivity {



    protected Context mContext ;

    private int layoutResourceId;


    //两者方式关联App里面的唯一Component管理类,这是第一种,下面是第二种方式,在子类中重写setupActivityComponent()即可
    public AppComponent getAppComponent(){
        return ((App)getApplication()).getAppComponent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这是第二种
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

    protected void setupActivityComponent(AppComponent appComponent){};

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
