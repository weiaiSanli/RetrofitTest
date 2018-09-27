package water.retrofittest;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import water.retrofittest.autodisponse.AutoDisponseTestActivity;

public class SplashActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {

        Button bt_main = (Button) findViewById(R.id.bt_main);
        Button bt_second = (Button) findViewById(R.id.bt_second);
        Button bt_third = (Button) findViewById(R.id.bt_third);
        Button bt_fourth = (Button) findViewById(R.id.bt_fourth);
        Button bt_auto = (Button) findViewById(R.id.bt_auto);
        bt_main.setOnClickListener(this);
        bt_second.setOnClickListener(this);
        bt_third.setOnClickListener(this);
        bt_fourth.setOnClickListener(this);
        bt_auto.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.bt_main:
                startActivity(MainActivity.class);
                break;

            case R.id.bt_second:
                startActivity(SecondMvpActivity.class);
                break;

            case R.id.bt_third:
                startActivity(ThreeActivity.class);
                break;

            case R.id.bt_fourth:
                startActivity(FourthActivity.class);
                break;

                case R.id.bt_auto:
                startActivity(AutoDisponseTestActivity.class);
                break;


        }
    }
}
