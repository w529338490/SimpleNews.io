package com.kong.app.news.detail;

import android.webkit.WebView;

import com.kong.lib.share.common.mvp.BasePresenter;
import com.kong.lib.share.common.mvp.BaseView;


/**
 * Created by whiskeyfei on 16/11/6.
 */

public interface DetailContract {

    interface View extends BaseView<Presenter> {
        void showLoadErrorMessage(String description);
    }

    interface Presenter extends BasePresenter {
        void init(WebView webView);

        void loadUrl(String newUrl);
    }
}
