# RetrofitTest
user Retrofit request network
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
Map<String, String> map = new HashMap<>();
        map.put("userName" , "xingfushuizhan");
        map.put("passWord" , "123456");

        UseCase addressNetUpdate = new LoginUpdate(map);
        addressNetUpdate.execute(new ResponseSubscriber<UpdateNetBean>() {
            @Override
            public void onFailure(Throwable e) {

                e.printStackTrace();
                System.out.println("服务器繁忙,请稍后重试");
                MyToast.show(MainActivity.this,"服务器繁忙,请稍后重试");
            }

            @Override
            public void onSuccess(UpdateNetBean updateNetBean) {

                int infoCode = updateNetBean.getInfoCode();
                if (infoCode == 200){
                    MyToast.show(MainActivity.this,"登录成功");
                }else{
                    MyToast.show(MainActivity.this,"商户号或密码错误");
                }

            }
        });

```
* 创建Update

```
public class LoginUpdate extends UseCase {
    public Map<String, String> map;

    public LoginUpdate(Map<String,String> map){
        super();
        this.map = map;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return RetrofitServiceFactory.getAppService().updateNetCall(map);
    }
}


```

* 在NetworkApi创建请求地址的配置
```

    /**
     * 登录界面的请求数据 addressAdd
     */

    @GET("delapp/userLogin.action")
    Observable<UpdateNetBean> updateNetCall(@QueryMap Map<String, String> map);
```

### 使用Dagger2

* 学习网址

  ```
  http://blog.csdn.net/u012943767/article/details/51897247
  http://www.jianshu.com/p/cd2c1c9f68d4
  http://www.jianshu.com/p/39d1df6c877d (这哥们在P层封装写的比较不错) 
  ```

1. 新建一个mudole标明注解

   ```

   ```

   ​

   ```
   /**
   类名使用自己的Activity+Module
   参数为Activity实现的View接口
   */
   @Module
   public class MainActivityModule {

       private final MainContract.View mView;

       public MainActivityModule(MainContract.View mView) {

           this.mView = mView;
       }

       /**
       注意注解为Provides并且方法名已provide+P层的类名
        * 将其返回,相当与new Employeepresenter(mView)
        * @return
        */
       @Provides
       MainContract.View provideMainPresenter(){
           return mView ;
       }
   }
   ```

2. 创建注入器Component

   ```
   /**
    * 类描述：创建Main的注入器
    * PerActivity:设置注入器的生命周期
    * dependencies:继承自AppComponent全局管理的Component
    * ActivityModule是BaseActivity基类的Module
    * 创建人： 史强
    * 创建时间:2017/7/7 16:12
    */
   @PerActivity
   @Component(dependencies = AppComponent.class ,  modules = {MainActivityModule.class , ActivityModule.class})
   public interface MainActivityComponent extends BaseActivityComponent{
       void inject(MainActivity activity);
   }
   ```

3. 在MainPersenter中inject

   ```
   /**
    * 类描述：使用inject将p引入到M
    * 创建人： 史强
    * 创建时间:2017/7/7 15:54
    */
   public class MainPresenter implements MainContract.Presenter {
     private Map<String, String> map;
     private MainContract.View mView;
     //使用inject标注需要注入
      @Inject
      MainPresenter( MainContract.View mIview) {

          this.mView = mIview ;
      }
   }
   ```

4. 在Activity中注入MainPresenter

   ```
   @Inject
      MainPresenter presenter ;   
   ```


   5. 使用Studio工具栏中的Build下的make Project自动生成所需要的DaggerMainActivityComponent管理类(使用过AIDL,Databinding的应该不会陌生)

      ​

   6. 重写BaseActivity的setupActivityComponent关联

      ```
       /**
           * 第二种方式导入presenter
           * 重写基类的setupActivityComponent()方法
           */
          @Override
          protected void setupActivityComponent(AppComponent appComponent) {

              DaggerMainActivityComponent.builder()
                      .appComponent(appComponent)
                      .mainActivityModule(new MainActivityModule(this))
                      .activityModule(new ActivityModule(this)).build()
                      .inject(this);
          }
      ```

   7. 运行程序即可