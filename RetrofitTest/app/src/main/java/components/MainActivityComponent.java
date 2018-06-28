package components;

import dagger.Component;
import module.ActivityModule;
import module.MainActivityModule;
import module.PerActivity;
import retrofit2.Retrofit;
import utils.ToastUtil;
import water.retrofittest.MainActivity;

/**
 * 类描述：创建Main的注入器
 * Component需要引用到目标类的实例，Component会查找目标类中用Inject注解标注的属性，
 * 查找到相应的属性后会接着查找该属性对应的用Inject标注的构造函数（这时候就发生联系了），
 * 剩下的工作就是初始化该属性的实例并把实例进行赋值。因此我们也可以给Component叫另外一个名字注入器（Injector）
 *
 * PerActivity:自定义设置注入器的生命周期
 * dependencies:继承自AppComponent全局管理的Component
 * 创建人： shi
 * 创建时间:2017/7/7 16:12
 */
@PerActivity
@Component(dependencies = AppComponent.class ,  modules = {MainActivityModule.class , ActivityModule.class})
public interface MainActivityComponent extends BaseActivityComponent{
    void inject(MainActivity activity);

}
