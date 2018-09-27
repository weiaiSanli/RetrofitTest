package water.retrofittest.arouter;

import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import water.retrofittest.BaseActivity;
import water.retrofittest.R;

/**
 * created by shi on 2018/9/27/027
 */
public class ArounterActy1 extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_arouter;
    }

    @Override
    protected void initView() {

        tv_title = findViewById(R.id.tv_title);
        Button bt_confirm = findViewById(R.id.bt_confirm);
        Button bt_url_intercept = findViewById(R.id.bt_url_intercept);
        bt_url_intercept.setVisibility(View.VISIBLE);
        bt_confirm.setOnClickListener(this);
        bt_url_intercept.setOnClickListener(this);

    }

    @Override
    protected void initData() {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bt_confirm:
                ActivityOptionsCompat compat = ActivityOptionsCompat.
                        makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);

                ARouter.getInstance().build("/arounter/ArounterActy2")
                        .withString("title", "你好啊,我是第一个界面过来的")
                        .withOptionsCompat(compat)//动画效果
                        .navigation();
                break;

            case R.id.bt_url_intercept:
                ARouter.getInstance()
                        .build("/test/webview")
                        .withString("url", "file:///android_asset/schame-test.html")
                        .navigation();

                break;
        }


    }
}
