package module;

import contract.FourthContract;
import contract.SecondContract;
import dagger.Module;
import dagger.Provides;
import model.FourthModel;
import model.SecondModel;
import presenter.FourthPresenter;

/**
 * description:创建view层,跟modeld的注解,直接在presenter中根据注解得到实例对象
 * Creat by shiqiang on 2018/5/3 0003 10:34
 */
@Module
public class FourthActivityModule {

    private FourthContract.View mView ;

    public FourthActivityModule(FourthContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public FourthContract.View provideActivity(){
        return mView ;
    }


    @Provides
    public FourthContract.Model provideModel(){
        return new FourthModel();
    }


}
