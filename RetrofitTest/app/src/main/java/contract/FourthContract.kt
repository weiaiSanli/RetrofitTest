package contract

import android.widget.TextView

/**
 * created by shi on 2018/7/6/006
 *
 */
interface FourthContract {
    interface Model {

        fun loginNet(userName: String, pwd: String)
    }

    interface View {

        fun getUserName():String? //可返回null
        fun getPassWord():String //不可返回null ,否则会报错
        fun loginSuccess(success: String)
        fun error(error: String)

    }

    interface Presenter {

        //登录联网
        fun loginNet()
    }

}