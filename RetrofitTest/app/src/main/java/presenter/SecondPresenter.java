package presenter;

import javax.inject.Inject;

import contract.SecondContract;
import info.LoginNetInfo;

/**
 * description:
 * Creat by shiqiang on 2018/5/3 0003 10:37
 */

public class SecondPresenter implements SecondContract.Presenter {


    private SecondContract.View mView ;
    private SecondContract.Model mModel ;

    @Inject
    public SecondPresenter(SecondContract.View mView, SecondContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void loginNet() {

        mModel.loginNet(mView.getUserName(), mView.getUserName(), new LoginNetInfo<String>() {
            @Override
            public void loginNetSuccess(String s) {

                mView.error("服务器繁忙,请稍后重试");

            }

            @Override
            public void loginNetError(String error) {

                mView.loginSuccess("登录成功");
            }
        });





    }
}
