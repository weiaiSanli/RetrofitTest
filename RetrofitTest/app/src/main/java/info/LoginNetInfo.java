package info;

/**
 * description:
 * Creat by shiqiang on 2018/4/25 0025 17:50
 */

public interface LoginNetInfo<T> {

    void loginNetSuccess(T t);

    void loginNetError(String error);

}
