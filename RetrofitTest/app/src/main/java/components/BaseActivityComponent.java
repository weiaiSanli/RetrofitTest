package components;

import android.app.Activity;


import dagger.Component;
import module.ActivityModule;
import module.PerActivity;

/**
 * 类描述：基类的注入器
 * 创建人： 史强
 * 创建时间:2017/7/6 16:22
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface BaseActivityComponent {
    Activity getActivity();
}
