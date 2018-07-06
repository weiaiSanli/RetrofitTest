package presenter

import javax.inject.Inject

import contract.SecondContract
import info.LoginNetInfo
import utils.ResultCallBack

/**
 * description:
 * Creat by shi on 2018/5/3 0003 10:37
 */

class SecondPresenter @Inject constructor() : SecondContract.Presenter {

    /**
     * 直接通过Inject找到实例,不在通过module找寻,注解直接可以使用SecondActivityModule中引用对象
     */
    @Inject
    lateinit var mView: SecondContract.View
    @Inject
    lateinit var mModel: SecondContract.Model


    /*@Inject
    public SecondPresenter(SecondContract.View mView, SecondContract.Model mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }*/

    override fun loginNet() {

        mModel.loginNet(mView.getUserName(), mView.getUserName(), object : LoginNetInfo<String> {
            override fun loginNetSuccess(s: String) {

                mView.error("服务器繁忙,请稍后重试")

            }

            override fun loginNetError(error: String) {

                mView.loginSuccess("登录成功")
            }
        })


    }
}
