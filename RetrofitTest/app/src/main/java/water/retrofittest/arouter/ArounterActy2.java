package water.retrofittest.arouter;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import water.retrofittest.BaseActivity;
import water.retrofittest.R;

/**
 * created by shi on 2018/9/27/027
 */

@Route(path = "/arounter/ArounterActy2")
public class ArounterActy2 extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_arouter;
    }

    @Override
    protected void initView() {

        tv_title = findViewById(R.id.tv_title);
        Button bt_confirm = findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(this);
        bt_confirm.setVisibility(View.GONE);

    }

    @Override
    protected void initData() {

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        tv_title.setText(title);


    }

    @Override
    public void onClick(View view) {


    }
}
