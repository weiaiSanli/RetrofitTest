package module;

import contract.MainContract;
import dagger.Module;
import dagger.Provides;
import presenter.MainPresenter;
import retrofit2.Retrofit;
import water.retrofittest.MainActivity;

/**
 * 类描述：Main的mudole对P层注入
 * 创建人： shi
 * 创建时间:2017/7/7 16:15
 */
@Module
public class MainActivityModule {

    /*private final MainContract.View mView;

    public MainActivityModule(MainContract.View mView) {

        this.mView = mView;
    }

    *//**
     * 将其返回,相当与new Employeepresenter(mView)
     * @return
     *//*
    @Provides
    MainContract.View provideMainPresenter(){
        return mView ;
    }
*/

    //第二种方式提供Activity构造函数

    private MainContract.View mView ;

    public MainActivityModule(MainContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public MainContract.View provideActivity(){
        return mView ;
    }

    @Provides
    public MainPresenter provideLoginPresenter(Retrofit retrofit){

        return new MainPresenter(mView,retrofit);

    }




}
