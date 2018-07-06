package components;

import android.app.Activity;


import dagger.Component;
import module.ActivityModule;
import module.PerActivity;
import utils.ToastUtil;
import water.retrofittest.BaseActivity;

/**
 * 类描述：基类的注入器
 * 创建人： shi
 * 创建时间:2017/7/6 16:22
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface BaseActivityComponent {
    Activity getActivity();
}
