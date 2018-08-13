package contract;

import info.BaseMvpView;

//跟第二种方式的接口保持一致
public interface ThreeActivityContract {

    interface Model <T>{

        void loginNet(String userName , String pwd , T t);

        void loginInterverNet();

        void onDestroy();
    }

    interface View extends BaseMvpView{

        String getUserName();
        String getPassWord();
        void loginSuccess(String success);
        void error(String error);
    }

    interface Presenter {
        //登录联网
        void loginNet();

        void loginInterverNet();
    }
}
