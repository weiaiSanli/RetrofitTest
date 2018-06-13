package model;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import bean.UpdateNetBean;
import contract.ThreeActivityContract;
import info.LoginNetInfo;
import netework.ResponseSubscriber;
import netework.UpdateFractory;

public class ThreeActivityModel implements ThreeActivityContract.Model<LoginNetInfo> {


    @Override
    public void loginNet(String userName, String pwd, final LoginNetInfo info) {
        HashMap<String , String> map = new HashMap();
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
