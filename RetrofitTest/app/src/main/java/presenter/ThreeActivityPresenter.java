package presenter;

import android.text.TextUtils;

import contract.ThreeActivityContract;
import info.LoginNetInfo;
import model.ThreeActivityModel;

public class ThreeActivityPresenter extends MvpBasePresenter<ThreeActivityContract.View , ThreeActivityContract.Model> implements ThreeActivityContract.Presenter {

    public ThreeActivityPresenter(ThreeActivityContract.View mView) {
        attachView(mView);
    }

    @Override
    protected ThreeActivityContract.Model creatModule() {
        return new ThreeActivityModel();
    }

    @Override
    public void loginNet() {

        String userName = mvpView.getUserName();
        String passWord = mvpView.getPassWord();

        if (TextUtils.isEmpty(userName)){
            mvpView.toast("用户名不能为空!");
            return;
        }

        if (TextUtils.isEmpty(passWord)){
            mvpView.toast("密码不能为空!");
            return;
        }

        mvpView.showLoading(); //加载弹出框
        mvpModule.loginNet(userName, passWord, new LoginNetInfo<String>() {
            @Override
            public void loginNetSuccess(String success) {
                mvpView.loginSuccess(success);
                mvpView.hideLoading();
            }

            @Override
            public void loginNetError(String error) {
                mvpView.hideLoading();
                mvpView.error(error);
            }
        });





    }
}
