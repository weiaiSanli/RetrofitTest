package module;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * 类描述：提供baseactivity的module
 * 创建人： shiqiang
 * 创建时间:2017/7/6 14:57
 */
@Module
public class ActivityModule {

    private final Activity activity;
    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity(){
        return activity;
    }



}
