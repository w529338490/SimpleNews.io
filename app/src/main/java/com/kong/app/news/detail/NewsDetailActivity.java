package com.kong.app.news.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.kong.R;
import com.kong.app.news.base.ToolBarActivity;

public class NewsDetailActivity extends ToolBarActivity implements DetailContract.View {

    public static final String URL = "key_url";
    public static final String TITLE = "key_title";

    private String mTitle;
    private String mUrl;

    private LinearLayout mLinearLayout;
    private WebView mWebView;
    private DetailContract.Presenter mNewsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_detail;
    }

    private void initView() {
        setTitle(mTitle);
        mLinearLayout = (LinearLayout) findViewById(R.id.detail_webview_root);
        mWebView = (WebView)findViewById(R.id.detail_webview_content);

        mNewsDetailPresenter = new NewsDetailPresenter(this);
        mNewsDetailPresenter.init(mWebView);
        mNewsDetailPresenter.loadUrl(mUrl);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null){
            mTitle = intent.getStringExtra(TITLE);
            mUrl = intent.getStringExtra(URL);
        }
    }

    @Override
    protected void onPause() {
        if (mWebView != null) mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mLinearLayout.removeView(mWebView);
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        }
        mWebView = null;
        super.onDestroy();
    }

    @Override
    public void showLoadErrorMessage(String description) {
        mWebView.setVisibility(View.GONE);
        Snackbar.make(mWebView,R.string.load_fail,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mNewsDetailPresenter = presenter;
    }
}
