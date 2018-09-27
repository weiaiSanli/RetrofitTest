package water.retrofittest;

import android.app.Application;
import components.AppComponent;
import components.DaggerAppComponent;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import module.AppModule;


/**
 * Created by shi on 2015/12/22.
 */
public class App extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        //导入AppComponent注入器:此为全局的,每个界面分别依赖appComponent即可使用AppModule中的全局单例模式
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build();

        /**
         * RxJava2 当取消订阅后(dispose())，RxJava抛出的异常后续无法接收(此时后台线程仍在跑，可能会抛出IO等异常),全部由RxJavaPlugin接收，需要提前设置ErrorHandler
         * 详情：http://engineering.rallyhealth.com/mobile/rxjava/reactive/2017/03/15/migrating-to-rxjava-2.html#Error Handling
         */

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
            }
        });

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
