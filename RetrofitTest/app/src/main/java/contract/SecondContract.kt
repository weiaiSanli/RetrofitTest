package contract

import info.LoginNetInfo

/**
 * description:
 * Creat by shiqiang on 2018/5/3 0003 10:37
 */

interface SecondContract {
    interface Model{

        fun loginNet(userName: String, pwd: String, t: LoginNetInfo<String>)
    }

    interface View {

        fun getUserName() : String
        fun getPassWord() : String
        fun loginSuccess(success: String)
        fun error(error: String)

    }

    interface Presenter {
        //登录联网
        fun loginNet()
    }
}
