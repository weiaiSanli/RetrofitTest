package model;

import java.util.HashMap;
import bean.UpdateNetBean;
import contract.SecondContract;
import info.LoginNetInfo;
import netework.ResponseSubscriber;
import netework.UpdateFractory;

/**
 * description:
 * Creat by shiqiang on 2018/5/3 0003 10:37
 */

public class SecondModel implements SecondContract.Model<LoginNetInfo> {


    @Override
    public void loginNet(String userName , String pwd , final LoginNetInfo info) {

        HashMap<String , String>  map = new HashMap();
        map.put("userName" , userName);
        map.put("passWord" , pwd);

        UpdateFractory.getBuild()
                .map(map)
                .name("updateNetCall")
                .build()
                .execute(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                        System.out.println("服务器繁忙,请稍后重试");
                        info.loginNetError("服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {

                        int infoCode = updateNetBean.getInfoCode();
                        if (infoCode == 200){
                            info.loginNetSuccess("登录成功");
                        }else{
                            info.loginNetError("商户号或密码错误");
                        }

                    }
                });




    }
}
