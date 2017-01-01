package link.ebbinghaus.planning.ui.view.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yurikami.lib.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import link.ebbinghaus.planning.R;
import link.ebbinghaus.planning.app.constant.Constant;

public class CommonWebViewActivity extends BaseActivity {
    public static final String INTENT_NAME_URL = Constant.PACKAGE_NAME + ".Url";

    @Bind(R.id.wv_common) WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);
        ButterKnife.bind(this);


        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.loadUrl(getIntent().getStringExtra(INTENT_NAME_URL));
    }

    /**
     * 携带参数启动本Activity
     *
     * @param context   启动者上下文
     * @param targetUrl 传送的数据
     */
    public static void startAction(Context context, String targetUrl) {
        Intent intent = new Intent(context, CommonWebViewActivity.class);
        intent.putExtra(INTENT_NAME_URL, targetUrl);
        context.startActivity(intent);
    }
}
