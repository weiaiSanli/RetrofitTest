package module;

import android.content.Context;
import android.util.Log;


import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.ToastUtil;
import utils.UserContentURL;

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

        //添加日志的缓存
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("shi","retrofitBack = "+message);
                System.out.println("当前网络:" + message);
            }
        });
        retrofit = new Retrofit.Builder()
                .baseUrl(UserContentURL.URL_SERVER) //刚刚添加进来的请求头
                .client(new OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .connectTimeout(10000, TimeUnit.SECONDS)
                        .writeTimeout(10000, TimeUnit.SECONDS)
                        .readTimeout(10000, TimeUnit.SECONDS)
                        .build())  //使用缓存,Interceptor截获每次网络请求用于缓存数据
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加Rxjava
                .addConverterFactory(GsonConverterFactory.create())  //添加Gson解析
                .build();

        System.out.println("我被初始化了"  + retrofit.toString());
        return retrofit;
    }

//    @Provides @Singleton
//    public Test provideTest(){
//        return new Test();
//    }

}
