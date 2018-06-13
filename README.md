

# RetrofitTest

user Retrofit request network 

<font color=#00ffff size=12> 注意: </font> 

```
下载运行项目如果 DaggerSecondActivityComponent 找不到点击studio中运行app旁边的Make project(Ctrl + F9)自动编译生成一下即可.
```



### 导入引用库
```
    //Retrofit+Rxjava
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'io.reactivex:rxjava:1.0.14'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.1.2'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.1'
    compile 'io.reactivex:rxandroid:1.1.0'
    
    //Dagger2的使用(可能会导致65K报错,注意里面的解决方法)
    provided 'org.glassfish:javax.annotation:10.0-b28'
    compile 'com.google.dagger:dagger:2.5'
    compile 'com.google.dagger:dagger-compiler:2.5'
```

* 项目Build中加入 

  ```
  apply plugin: 'com.neenbedankt.android-apt'
  
  classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
  ```

  ​

### 加入权限

```
 <!--联网的请求-->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```
### 如何使用
* 创建Map发送请求数据

```
 HashMap<String , String>  map = new HashMap();
        map.put("userName" , userName);
        map.put("passWord" , pwd);

		//使用构建者模式封装的请求类
        UpdateFractory.getBuild()
                .map(map)
                .name("updateNetCall")
                .build()
                .execute(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                        System.out.println("服务器繁忙,请稍后重试");
                        info.loginNetError("服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {

                        int infoCode = updateNetBean.getInfoCode();
                        if (infoCode == 200){
                            info.loginNetSuccess("登录成功");
                        }else{
                            info.loginNetError("商户号或密码错误");
                        }

                    }
                });

```
* 在NetworkApi创建请求地址的配置
```

    /**
     * 登录界面的请求数据 addressAdd
     */

    @GET("delapp/userLogin.action")
    Observable<UpdateNetBean> updateNetCall(@QueryMap Map<String, String> map);
```

## 使用MVP



### 简单的MVP封装,不使用Dagger(demo:ThreeActivity的实现)

1. 创建一个MVPActivity的基类,给与子类实现绑定Presenter

   ```
   public abstract class MvpBaseActivity<P extends BasePresenter> extends BaseActivity {
   
       public P mvpPresenter ;
       
       @Override
       protected void onCreate(Bundle savedInstanceState) {
           mvpPresenter = createPresenter();
           super.onCreate(savedInstanceState);
       }
   
       protected abstract P createPresenter();
   
       @Override
       protected void onDestroy() {
           super.onDestroy();
           if (mvpPresenter != null) {
               mvpPresenter.detachView();
           }
       }
   }
   ```

2. Presenter的两种基类

   1. 只绑定view接口,用于简单的请求,只在presenter跟view层直接进行逻辑处理

   ```
   public class BasePresenter<V> {
   
       protected V mvpView;
   
       public void attachView(V mvpView) {
           this.mvpView = mvpView ;
       }
   
       public void detachView() {
           this.mvpView = null ;
       }
   
   }
   
   ```

   2. 在MvpBasePresenter中绑定Module层

      ```
      public abstract class MvpBasePresenter<V,T> extends BasePresenter<V> {
      
          protected T mvpModule;
      
          //创建view
          public void attachView(V mvpView) {
              super.attachView(mvpView);
              mvpModule = creatModule();
          }
      
          //给与子类实现创建module的实例
          protected abstract T creatModule();
      
          public void detachView() {
              super.detachView();
              this.mvpView = null;
          }
      }
      ```

      

### 使用Dagger2(demo:SecondMvpActivity的实现)

* 学习网址

  ```
  http://blog.csdn.net/u012943767/article/details/51897247
  http://www.jianshu.com/p/cd2c1c9f68d4
  http://www.jianshu.com/p/39d1df6c877d (这哥们在P层封装写的比较不错) 
  ```

1. 新建一个mudole标明注解

   ```
   /**
    * description:创建view层,跟modeld的注解,直接在presenter中根据注解得到实例对象
    * Creat by shiqiang on 2018/5/3 0003 10:34
    */
   @Module
   public class SecondActivityModule  {
   
       private SecondContract.View mView ;
   
       public SecondActivityModule(SecondContract.View mView) {
           this.mView = mView;
       }
   
   	//返回view的实例对象
       @Provides
       public SecondContract.View provideActivity(){
           return mView ;
       }
   	
   	//返回model的实例对象
       @Provides
       public SecondContract.Model getSecondModel() {
           return new SecondModel();
       }
   }
   
   ```

   

2. 创建注入器Component

   ```
   /**
    * 类描述：创建Main的注入器
    * PerActivity:设置注入器的生命周期
    * dependencies:继承自AppComponent全局管理的Component
    * 创建人： shi
    * 创建时间:2017/7/7 16:12
    */
   @PerActivity
   @Component(dependencies = AppComponent.class ,  modules = {SecondActivityModule.class , ActivityModule.class})
   public interface SecondActivityComponent extends BaseActivityComponent{
       void inject(SecondMvpActivity activity);
   
   }
   ```

3. 在SecondPresenter中inject view层的实例以及model的实例

   ```
    private SecondContract.View mView ;
       private SecondContract.Model mModel ;
   	//拿到第1步中的SecondActivityModule注解
       @Inject
       public SecondPresenter(SecondContract.View mView, SecondContract.Model mModel) {
           this.mView = mView;
           this.mModel = mModel;
       }
   ```

4. 在Activity中注入MainPresenter

   ```
    @Inject
    SecondPresenter presenter ; 
   ```


   5. 使用Studio工具栏中的Build下的make Project自动生成所需要的DaggerSecondActivityComponent管理类(使用过AIDL,Databinding的应该不会陌生)

      

   6. 重写BaseActivity的setupActivityComponent关联

      ```
       /**
           * 第二种方式导入presenter
           * 重写基类的setupActivityComponent()方法
           */
          @Override
          protected void setupActivityComponent(AppComponent appComponent) {
      
              DaggerSecondActivityComponent.builder()
                      .appComponent(appComponent)
                      .secondActivityModule(new SecondActivityModule(this))
                      .activityModule(new ActivityModule(this))
                      .build()
                      .inject(this);
          }
      ```

   7. 运行程序即可