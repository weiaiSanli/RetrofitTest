package water.retrofittest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import components.AppComponent;
import components.DaggerMainActivityComponent;
import contract.MainContract;
import module.ActivityModule;
import module.MainActivityModule;
import presenter.MainPresenter;
import retrofit2.Retrofit;
import utils.ToastUtil;
import utils.UserContentURL;

/**注意: Component会首先从Module维度中查找类实例，若找到就用Module维度创建类实例，并停止查找Inject维度。
 * 否则才是从Inject维度查找类实例。所以创建类实例级别Module维度要高于Inject维度。
 *
 * description:第一种引入的方式,在module中有SecondPresenter实例的引用,不在找寻inject,
 * 所以presenter为MainActivityModule中provideLoginPresenter()返回的对象
 * Creat by shi on 2018/5/3 0003 10:30
 */


public class MainActivity extends BaseActivity implements MainContract.View, View.OnClickListener {

    //注解(Annotation)来标注目标类中所依赖的其他类，同样用注解来标注所依赖的其他类的构造函数，那注解的名字就叫Inject
    //它告诉Dagger这个 构造方法，成员变量或者函数方法需要依赖注入。这样，Dagger就会构造一个这个类的实例并满足他们的依赖。
    @Inject
    MainPresenter presenter; //使用了Inject会先去module中找寻返回MainPresenter的对象
    private TextView tv;
    private Button btLogin;
    private Button btNext;

    @Inject
    Retrofit retrofit; //都是AppModule中初始化的retrofit对象,注入器依赖都能使用依赖类中的对象


    @Inject
    ToastUtil toastUtil; //在AppComponent中已经定义过,而且MainActivityComponent依赖了AppComponent,相当于父类中的实例都可以直接使用



    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        tv = (TextView) findViewById(R.id.tv_end);
        btLogin = (Button) findViewById(R.id.bt_login);
        btNext = (Button) findViewById(R.id.bt_nex);
        btLogin.setOnClickListener(this);
        btNext.setOnClickListener(this);

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

        toastUtil.showToast("我是谁?");
        System.out.println("MainActivity中的Retrofit地址为:" + retrofit.toString() + "基类的地址为:" + mRetrofit.toString());

        /**
         * 当前打印的结果为:同一个对象地址:
         * System.out: MainActivity中的Retrofit地址为:retrofit2.Retrofit@e8da350
         * System.out: MainPresenter中的Retrofit地址为:retrofit2.Retrofit@e8da350
         *
         * MainActivity中的Retrofit地址为:retrofit2.Retrofit@26bf74fa基类的地址为:retrofit2.Retrofit@26bf74fa
         */
    }

    /**
     * 带有请求头token的请求封装,注意网络请求是在订阅的时候开始的.subscribe()方法的时候
     */
    @Override
    protected void onResume() {
        super.onResume();

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
        tv.setText(success);
    }

    @Override
    public void error(String error) {
        tv.setText(error);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bt_login:
                presenter.loginNet();
                break;

            case R.id.bt_nex:
                startActivity(SecondMvpActivity.class);
                break;
        }
    }
}
