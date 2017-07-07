package utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 类描述：
 * 创建人： 史强
 * 创建时间:2017/7/6 14:59
 */
public class ToastUtil {

    private Context mContext;

    public ToastUtil(Context mContext) {
        this.mContext = mContext;
    }


    public void showToast(String message){
        Toast.makeText(mContext,message,Toast.LENGTH_LONG).show();
    }
}
