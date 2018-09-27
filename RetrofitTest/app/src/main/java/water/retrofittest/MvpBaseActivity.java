package water.retrofittest;

import android.os.Bundle;

import presenter.BasePresenter;
import presenter.MvpBasePresenter;

public abstract class MvpBaseActivity<P extends MvpBasePresenter> extends BaseActivity {

    public P mvpPresenter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mvpPresenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

}
