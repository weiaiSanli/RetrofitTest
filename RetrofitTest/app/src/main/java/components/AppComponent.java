package components;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import module.AppModule;
import retrofit2.Retrofit;
import utils.ToastUtil;

/**
 * 类描述：创建单例的将AppModule注入到App里面
 * Component也是一个注解类，一个类要想是Component，必须用Component注解来标注该类，并且该类是接口或抽象类
 *
 * 创建人： shi
 * 创建时间:2017/7/6 15:03
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContext(); //提供一个接口,供其他依赖AppComponent的注入器使用AppModule中的对象实例
    ToastUtil getToastUtil();
    Retrofit getretrofit() ;

}
