package module;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * 自定义限定符,用于解决注解迷失,当有多个构造方法的时候不知道应该引用哪个而编译错误
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Named {
    String value() default "" ;  //返回一个默认值为空的自定义Named注解
}
