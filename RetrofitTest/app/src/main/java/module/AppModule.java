package module;

import android.content.Context;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.ToastUtil;

/**
 * 类描述：App的 module:用Singleton标记全局的单例模式.在app里面引入
 * 创建人： shi
 * 创建时间:2017/7/6 14:53
 */
@Module
public class AppModule {

    private Context context;
    private Retrofit retrofit;

    public AppModule(Context context){
        this.context = context;
    }

    @Provides @Singleton
    public Context provideContext(){
        return context;
    }



    @Provides @Singleton
    public ToastUtil provideToastUtil(){
        return new ToastUtil(context);
    }

    @Provides @Singleton
    public Retrofit provideRetrofit(){

        //刚刚添加进来的请求头
//                .client(getOkHttps())  //使用缓存,Interceptor截获每次网络请求用于缓存数据
//添加Rxjava
//添加Gson解析
        retrofit = new Retrofit.Builder()
                .baseUrl("http://dsn.ttkaifa.com/diaochan/") //刚刚添加进来的请求头
//                .client(getOkHttps())  //使用缓存,Interceptor截获每次网络请求用于缓存数据
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())  //添加Rxjava
                .addConverterFactory(GsonConverterFactory.create())  //添加Gson解析
                .build();
        return retrofit;
    }

//    @Provides @Singleton
//    public Test provideTest(){
//        return new Test();
//    }

}
