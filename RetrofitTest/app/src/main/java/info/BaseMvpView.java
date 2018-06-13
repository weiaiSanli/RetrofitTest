package info;

/**
 * creat by: shi  2018-6-13 11:07:20
 * 用于activity的基类,主要是toast,加载动画的显示等
 */
public interface BaseMvpView {

    void toast(String toast);
    void showLoading();
    void hideLoading();
}
