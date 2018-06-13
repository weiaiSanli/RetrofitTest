package info;

/**
 * description:用于P层与M层通信接口回调
 * Creat by shiqiang on 2018/4/25 0025 17:50
 */

public interface LoginNetInfo<T> {

    void loginNetSuccess(T t);

    void loginNetError(String error);

}
