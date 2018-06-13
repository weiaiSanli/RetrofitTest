package water.retrofittest;

import android.os.Bundle;

import presenter.BasePresenter;

public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity {

    public P mvpPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }
}
