package presenter;

import javax.inject.Inject;

import contract.SecondContract;
import info.LoginNetInfo;

/**
 * description:
 * Creat by shi on 2018/5/3 0003 10:37
 */

public class SecondPresenter implements SecondContract.Presenter {


    private SecondContract.View mView ;
    private SecondContract.Model mModel ;

    /**
     * 直接通过Inject找到实例,不在通过module找寻,注解直接可以使用SecondActivityModule中引用对象
     * @param mView
     * @param mModel
     */
    @Inject
    public SecondPresenter(SecondContract.View mView, SecondContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void loginNet() {

        mModel.loginNet(mView.getUserName(), mView.getPassWord(), new LoginNetInfo<String>() {
            @Override
            public void loginNetSuccess(String s) {


                mView.loginSuccess("登录成功");
            }

            @Override
            public void loginNetError(String error) {

                mView.error("服务器繁忙,请稍后重试");
            }
        });





    }
}
