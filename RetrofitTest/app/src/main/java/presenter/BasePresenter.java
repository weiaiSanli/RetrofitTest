package presenter;

public class BasePresenter<V> {

    protected V mvpView;

    public void attachView(V mvpView) {
        this.mvpView = mvpView ;
    }

    public void detachView() {
        this.mvpView = null ;
    }

}
