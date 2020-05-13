package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p008hu.gov.virusradar.Activities.Dialog.ProgressBarDialog;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Utilities.VirusTraceApp;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.PrivacyPolicyActivity */
public class PrivacyPolicyActivity extends AppCompatActivity {
    Context context;
    @BindView(2131231135)
    WebView wvPrivacy;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_privacy_policy);
        this.context = this;
        ButterKnife.bind((Activity) this);
        setWebView();
    }

    private void setWebView() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "");
        this.wvPrivacy.getSettings().setJavaScriptEnabled(true);
        this.wvPrivacy.setScrollBarStyle(0);
        this.wvPrivacy.setVerticalScrollBarEnabled(false);
        this.wvPrivacy.setHorizontalScrollBarEnabled(false);
        this.wvPrivacy.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
                super.onReceivedError(webView, webResourceRequest, webResourceError);
                ProgressBarDialog.dismissDialog();
            }

            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
                ProgressBarDialog.dismissDialog();
            }

            public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
                super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
                ProgressBarDialog.dismissDialog();
            }

            public void onPageFinished(WebView webView, String str) {
                ProgressBarDialog.dismissDialog();
            }
        });
        WebView webView = this.wvPrivacy;
        StringBuilder sb = new StringBuilder();
        sb.append(VirusTraceApp.globalEnvironment.globalURL);
        sb.append(getString(C1130R.string.privacy_url));
        webView.loadUrl(sb.toString());
    }

    @OnClick({2131230916})
    public void onBack() {
        onBackPressed();
    }
}
