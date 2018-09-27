package water.retrofittest.arouter;

import android.webkit.WebView;

import com.alibaba.android.arouter.facade.annotation.Route;

import water.retrofittest.BaseActivity;
import water.retrofittest.R;

/**
 * created by shi on 2018/9/27/027
 */
@Route(path = "/test/webview")
public class ArouterWebView extends BaseActivity {
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initView() {


        WebView  webview = (WebView) findViewById(R.id.webview);
        webview.loadUrl(getIntent().getStringExtra("url"));

    }

    @Override
    protected void initData() {

    }
}
