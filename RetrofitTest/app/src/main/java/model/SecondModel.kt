package model

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.ArrayMap
import java.util.HashMap
import bean.UpdateNetBean
import contract.SecondContract
import info.LoginNetInfo
import netework.ResponseSubscriber
import netework.UpdateFractory

/**
 * description:
 * Creat by shiqiang on 2018/5/3 0003 10:37
 */

class SecondModel : SecondContract.Model {


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun loginNet(userName: String, pwd: String, info: LoginNetInfo<String>) {

        val map = ArrayMap<String , String>()
        map.put("userName", userName)
        map.put("passWord", pwd)
        UpdateFractory.getBuild()
                .map(map)
                .name("updateNetCall")
                .build()
                .execute(object : ResponseSubscriber<UpdateNetBean>() {
                    override fun onFailure(e: Throwable) {

                        e.printStackTrace()
                        println("服务器繁忙,请稍后重试")
                        info.loginNetError("服务器繁忙,请稍后重试")
                    }

                    override fun onSuccess(updateNetBean: UpdateNetBean) {

                        val infoCode = updateNetBean.infoCode
                        if (infoCode == 200) {
                            info.loginNetSuccess("登录成功")
                        } else {
                            info.loginNetError("商户号或密码错误")
                        }

                    }
                })


    }
}
