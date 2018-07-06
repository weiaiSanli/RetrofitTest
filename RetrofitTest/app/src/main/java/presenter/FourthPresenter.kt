package presenter

import contract.FourthContract
import javax.inject.Inject

/**
 * created by shi on 2018/7/6/006
 *
 */
class FourthPresenter @Inject constructor(private var mView : FourthContract.View ,private var model : FourthContract.Model) : FourthContract.Presenter {


    override fun loginNet() {

        model.loginNet(mView.getUserName()?: "幸福水站" , mView.getPassWord()  /*object : ResultCallBack<String>{ //返回object是实现类
            override fun onSuccess(success: String) {

            }

            override fun onFailure(error: String) {
            }

        }*/)


    }
}