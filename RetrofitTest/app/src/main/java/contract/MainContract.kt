package contract

/**
 * 类描述：使用MVPHelper插件自动生成的
 * 创建人： shi
 * 创建时间:2017/7/7 15:54
 */
interface MainContract {
    interface Model

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
