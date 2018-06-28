package components;

import dagger.Component;
import module.ActivityModule;
import module.FourthActivityModule;
import module.PerActivity;
import module.SecondActivityModule;
import water.retrofittest.FourthActivity;
import water.retrofittest.SecondMvpActivity;

/**
 * 类描述：创建Main的注入器
 * PerActivity:设置注入器的生命周期
 * dependencies:继承自AppComponent全局管理的Component
 * 创建人： shi
 * 创建时间:2017/7/7 16:12
 */
@PerActivity
@Component(dependencies = AppComponent.class ,  modules = {FourthActivityModule.class , ActivityModule.class})
public interface FourthActivityComponent extends BaseActivityComponent{
    void inject(FourthActivity activity);

}
