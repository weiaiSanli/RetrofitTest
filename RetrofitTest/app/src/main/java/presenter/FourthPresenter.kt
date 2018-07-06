package presenter

import contract.FourthContract

/**
 * created by shi on 2018/7/6/006
 *
 */
class FourthPresenter constructor(private var mView : FourthContract.View ,private var model : FourthContract.Model<String>) : FourthContract.Presenter {


    override fun loginNet() {

        model.loginNet(mView.getUserName() , mView.getPassWord() , null)


    }
}