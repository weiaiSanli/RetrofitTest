package presenter;

/**
 * 用于改善后的标准的mvp,在module层请求数据
 */
public abstract class MvpBasePresenter<V,T> extends BasePresenter<V> {

    protected T mvpModule;

    //创建view
    public void attachView(V mvpView) {
        super.attachView(mvpView);
        mvpModule = creatModule();
    }

    //给与子类实现创建module的实例
    protected abstract T creatModule();

    public void detachView() {
        super.detachView();
        this.mvpView = null;
    }

}