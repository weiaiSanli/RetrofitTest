package water.retrofittest;

import android.app.Application;
import components.AppComponent;
import components.DaggerAppComponent;
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

    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

}
