package components;

import dagger.Component;
import module.ActivityModule;
import module.MainActivityModule;
import module.PerActivity;
import water.retrofittest.MainActivity;

/**
 * 类描述：创建Main的注入器
 * PerActivity:设置注入器的生命周期
 * dependencies:继承自AppComponent全局管理的Component
 * 创建人： 史强
 * 创建时间:2017/7/7 16:12
 */
@PerActivity
@Component(dependencies = AppComponent.class ,  modules = {MainActivityModule.class , ActivityModule.class})
public interface MainActivityComponent extends BaseActivityComponent{
    void inject(MainActivity activity);
}
