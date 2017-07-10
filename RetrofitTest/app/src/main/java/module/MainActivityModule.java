package module;

import contract.MainContract;
import dagger.Module;
import dagger.Provides;
import presenter.MainPresenter;

/**
 * 类描述：Main的mudole对P层注入
 * 创建人： 史强
 * 创建时间:2017/7/7 16:15
 */
@Module
public class MainActivityModule {

    private final MainContract.View mView;

    public MainActivityModule(MainContract.View mView) {

        this.mView = mView;
    }

    /**
     * 将其返回,相当与new Employeepresenter(mView)
     * @return
     */
    @Provides
    MainContract.View provideMainPresenter(){
        return mView ;
    }


    //第二种方式提供Activity构造函数

    /*
    private LoginActivity activity ;

    public LoginActivityModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LoginActivity provideActivity(){
        return activity ;
    }

    @Provides
    public LoginPresenter provideLoginPresenter(){

        return new LoginPresenter(activity);

    }

    */



}
