package contract;

/**
 * 类描述：使用MVPHelper插件自动生成的
 * 创建人： 史强
 * 创建时间:2017/7/7 15:54
 */
public interface MainContract {
    interface Model {
    }

    interface View {

        String getUserName();
        String getPassWord();
        void loginSuccess(String success);
        void error(String error);

    }

    interface Presenter {
        //登录联网
        void loginNet();
    }
}
