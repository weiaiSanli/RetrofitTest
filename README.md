# RetrofitTest
user Retrofit request network
### 导入引用库
```
    compile 'com.squareup.retrofit2:retrofit:2.0.1'
    compile 'io.reactivex:rxjava:1.0.14'
    compile 'com.squareup.retrofit2:converter-gson:2.0.1'
    compile 'com.squareup.okhttp3:logging-interceptor:3.1.2'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.1'
    compile 'io.reactivex:rxandroid:1.1.0'
```

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
