package module;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import utils.ToastUtil;

/**
 * 类描述：App的 module
 * 创建人： 史强
 * 创建时间:2017/7/6 14:53
 */
@Module
public class AppModule {

     Context context;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }



    @Provides @Singleton
    public ToastUtil provideToastUtil(){
        return new ToastUtil(context);
    }

//    @Provides @Singleton
//    public Test provideTest(){
//        return new Test();
//    }

}
