package components;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Component;
import module.AppModule;
import utils.ToastUtil;

/**
 * 类描述：创建单例的将AppModule注入到App里面
 * 创建人： 史强
 * 创建时间:2017/7/6 15:03
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContext();
    ToastUtil getToastUtil();

}
