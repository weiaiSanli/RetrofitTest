package contract;

public interface FourthContract {
    interface Model <T>{

        void loginNet(String userName , String pwd , T t);
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
