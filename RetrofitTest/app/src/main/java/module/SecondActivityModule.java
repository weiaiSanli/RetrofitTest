package module;

import contract.MainContract;
import contract.SecondContract;
import dagger.Module;
import dagger.Provides;
import model.SecondModel;

/**
 * description:创建view层,跟modeld的注解,直接在presenter中根据注解得到实例对象
 * Creat by shiqiang on 2018/5/3 0003 10:34
 */
@Module
public class SecondActivityModule  {

    private SecondContract.View mView ;

    public SecondActivityModule(SecondContract.View mView) {
        this.mView = mView;
    }

    @Provides
    public SecondContract.View provideActivity(){
        return mView ;
    }

    @Provides
    public SecondContract.Model getSecondModel() {
        return new SecondModel();
    }
}
