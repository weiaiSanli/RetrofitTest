package contract;

import info.LoginNetInfo;

public interface FourthContract {
    interface Model {

        void loginNet(String userName , String pwd , LoginNetInfo loginNetInfo);
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
