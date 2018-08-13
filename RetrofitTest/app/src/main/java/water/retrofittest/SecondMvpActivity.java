package water.retrofittest;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import components.AppComponent;
import components.DaggerSecondActivityComponent;
import contract.SecondContract;
import module.ActivityModule;
import module.SecondActivityModule;
import presenter.SecondPresenter;
import utils.UserContentURL;

/**注意: Component会首先从Module维度中查找类实例，若找到就用Module维度创建类实例，并停止查找Inject维度。
 * 否则才是从Inject维度查找类实例。所以创建类实例级别Module维度要高于Inject维度。
 *
 * description:第二种引入的方式,在module中没有SecondPresenter实例的引用,就会在Inject里面找寻
 * Creat by shiqiang on 2018/5/3 0003 10:30
 */

public class SecondMvpActivity extends BaseActivity implements SecondContract.View, View.OnClickListener {



    @Inject
    SecondPresenter presenter ;
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

    /**
     * 第二种方式导入presenter
     * 重写基类的setupActivityComponent()方法
     */
    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerSecondActivityComponent.builder()
                .appComponent(appComponent)
                .secondActivityModule(new SecondActivityModule(this))
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
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

        presenter.loginNet();
    }

}
