package com.qks.beautyexchange.ui.activity.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.qks.beautyexchange.R;

/**
 * 用来显示HTML的Activity
 * 为了防止内存泄漏,单独放在一个进程中
 * 使用静态方法openHtml 打开此Activity
 * 此Activity对HTML的部分问题已经做了处理
 * ----2016/12/20
 * 经过测试,如果使用独立进程,Application 中 的onCreate方法会执行两次,所以最好在onCreate方法中做判断,以免重复初始化.
 * Created by admin on 2016/4/13.
 */
public class BaseHtmlActivity extends AppCompatActivity{

    /**
     * Intent中的Key
     */
    public static final String KEY_HTML_URL = "url";

    /**
     * 需要显示的HTML的地址
     */
    private String mHtmlUrl;

    /**
     * 用来装WebView的ViewGroup
     */
    private LinearLayout mLayout;

    /**
     * WebView对象
     */
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html);

        Bundle bundle = getIntent().getExtras();
        mHtmlUrl = bundle.getString(KEY_HTML_URL,"http://www.baidu.com");

        mLayout = (LinearLayout) findViewById(R.id.html_layout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(params);
        mLayout.addView(mWebView);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setLoadsImagesAutomatically(true);

        //调用JS方法,安卓版本大于17 加上注解@JavaScriptInterface
        webSettings.setJavaScriptEnabled(true);

        saveData(webSettings);
        newWin(webSettings);

        //不同版本,硬件加速问题
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 && shouldOpenHardware()){
            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }
        //三星手机硬件加速关闭后导致H5弹出的对话框出现不消失情况
        if("samsung".equalsIgnoreCase(Build.BRAND)&& Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        }

        //解决部分机型上,点击输入框,软键盘没有弹起的情况
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(mWebView != null){
                    mWebView.requestFocus();
                }
                return false;
            }
        });
        mWebView.setWebChromeClient(webChromeClient);
        mWebView.setWebViewClient(webViewClident);
        mWebView.loadUrl(mHtmlUrl);

    }

    private void saveData(WebSettings webSettings){
        //有时候网页需要自己保存一些关键数据,Android WebView 需要自己设置
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setAppCacheEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        webSettings.setAppCachePath(appCachePath);
    }
    private void newWin(WebSettings webSettings){
        //html中的_bank标签就是新建窗口打开，有时会打不开，需要加以下
        //然后 复写 WebChromeClient的onCreateWindow方法
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    }

    public static void openHtml(Context context,String htmlUrl){
        Intent intent = new Intent("com.qks.beautyexchange.basehtmlactivity");
        intent.putExtra(BaseHtmlActivity.KEY_HTML_URL,"http://baidu.com");
        context.startActivity(intent);
    }

    public boolean shouldOpenHardware(){
        //获取当前手机的牌子
        if("samsung".equalsIgnoreCase(Build.BRAND)){
            return false;
        }
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            return true;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()){
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        if(mWebView != null){
            mWebView.stopLoading();
            mWebView.loadDataWithBaseURL(null,"","text/html","utf-8",null);
            mWebView.clearHistory();
            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
        //经过测试 如果使用关闭之后就杀掉进程的方式 那么这样 启动速度太慢了,暂且不杀进程 不知道有没有问题
//        System.exit(0);
    }

    WebViewClient webViewClident = new WebViewClient(){
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        /**
         * 自定义WebView加载错误界面
         * @param view
         * @param request
         * @param error
         */
        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
        }

        /**
         * 自定义加载不信任网页SSL错误处理
         * @param view
         * @param handler
         * @param error
         */
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    };

    WebChromeClient webChromeClient = new WebChromeClient(){
        //=========HTML5定位==========================================================
        //需要先加入权限
        //<uses-permission android:name="android.permission.INTERNET"/>
        //<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
        //<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
        @Override
        public void onReceivedIcon(WebView view, Bitmap icon) {
            super.onReceivedIcon(view, icon);
        }

        @Override
        public void onGeolocationPermissionsHidePrompt() {
            super.onGeolocationPermissionsHidePrompt();
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(final String origin, final GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);//注意个函数，第二个参数就是是否同意定位权限，第三个是是否希望内核记住
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
        //=========HTML5定位==========================================================


        //=========多窗口的问题==========================================================
        @Override
        public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
            WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
            transport.setWebView(mWebView);
            resultMsg.sendToTarget();
            return true;
        }
        //=========多窗口的问题==========================================================

    };
}
