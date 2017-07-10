package presenter;

import android.util.ArrayMap;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import bean.UpdateNetBean;
import contract.MainContract;
import netework.ResponseSubscriber;
import netework.UpdateFractory;
import retrofit2.Retrofit;
import utils.MyToast;
import water.retrofittest.MainActivity;

/**
 * 类描述：使用inject将p引入到M
 * 创建人： 史强
 * 创建时间:2017/7/7 15:54
 */
public class MainPresenter implements MainContract.Presenter {


    private Map<String, String> map;

    private MainContract.View mView;
    private Retrofit retrofit;

    public MainPresenter(MainActivity activity, Retrofit retrofit) {

        this.mView = activity ;
        this.retrofit = retrofit ;

    }

   /* @Inject
    MainPresenter( MainContract.View mIview) {

        this.mView = mIview ;
    }*/

    @Override
    public void loginNet() {
        System.out.println(retrofit.getClass().getName()+ "-------");
        map = new HashMap();
        map.put("userName" , mView.getUserName());
        map.put("passWord" , mView.getPassWord());

        new MainRetrofit(this.retrofit , map)
//        UpdateFractory.getBuild()
//                .map(map)
//                .name("updateNetCall")
//                .build()
                .execute(new ResponseSubscriber<UpdateNetBean>() {
                    @Override
                    public void onFailure(Throwable e) {

                        e.printStackTrace();
                        System.out.println("服务器繁忙,请稍后重试");
                        mView.error("服务器繁忙,请稍后重试");
                    }

                    @Override
                    public void onSuccess(UpdateNetBean updateNetBean) {

                        int infoCode = updateNetBean.getInfoCode();
                        if (infoCode == 200){
                            mView.loginSuccess("登录成功");
                        }else{
                            mView.error("商户号或密码错误");
                        }

                    }
                });







    }
}
