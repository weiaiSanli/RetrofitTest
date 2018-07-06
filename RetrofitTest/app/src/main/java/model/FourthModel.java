package model;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import contract.FourthContract;
import utils.Constant;
import utils.ResultCallBack;

public class FourthModel implements FourthContract.Model {

    @Inject
    public FourthModel() {
    }


    @Override
    public void loginNet(@NotNull String userName, @NotNull String pwd) {
        System.out.println("接受到的消息为:" + Constant.Companion.getUserName() + "---" + Constant.Companion.getPassWord());

        if (Constant.Companion.getUserName().equals(userName) && Constant.Companion.getPassWord().equals(pwd)){
            System.out.println("成功了");
//            resultCallBack.onSuccess("成功了");
        }else{
//            resultCallBack.onFailure("登录失败");
        }
    }
}
