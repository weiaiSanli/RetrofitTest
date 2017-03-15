package water.retrofittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import bean.UpdateNetBean;
import netework.LoginUpdate;
import netework.ResponseSubscriber;
import netework.UseCase;
import utils.MyToast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Map<String, String> map = new HashMap<>();
        map.put("userName" , "xingfushuizhan");
        map.put("passWord" , "123456");

        UseCase addressNetUpdate = new LoginUpdate(map);
        addressNetUpdate.execute(new ResponseSubscriber<UpdateNetBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
                System.out.println("服务器繁忙,请稍后重试");
                MyToast.show(MainActivity.this,"服务器繁忙,请稍后重试");
            }

            @Override
            public void onSuccess(UpdateNetBean updateNetBean) {

                int infoCode = updateNetBean.getInfoCode();
                if (infoCode == 200){
                    MyToast.show(MainActivity.this,"登录成功");
                }else{
                    MyToast.show(MainActivity.this,"商户号或密码错误");
                }

            }
        });

    }



}
