package water.retrofittest;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

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


        //初始化sdk
//        if (isDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
