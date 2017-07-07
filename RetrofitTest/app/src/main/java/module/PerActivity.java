package module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

import dagger.Provides;

/**
 * 类描述：指定注入跟Activity生命周期相同
 * 创建人： 史强
 * 创建时间:2017/7/7 16:09
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}

