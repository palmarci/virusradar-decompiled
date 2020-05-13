package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.webkit.WebView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.PrivacyPolicyActivity_ViewBinding */
public class PrivacyPolicyActivity_ViewBinding implements Unbinder {
    private PrivacyPolicyActivity target;
    private View view7f0800c4;

    public PrivacyPolicyActivity_ViewBinding(PrivacyPolicyActivity privacyPolicyActivity) {
        this(privacyPolicyActivity, privacyPolicyActivity.getWindow().getDecorView());
    }

    public PrivacyPolicyActivity_ViewBinding(final PrivacyPolicyActivity privacyPolicyActivity, View view) {
        this.target = privacyPolicyActivity;
        privacyPolicyActivity.wvPrivacy = (WebView) Utils.findOptionalViewAsType(view, C1130R.C1133id.wvPrivacy, "field 'wvPrivacy'", WebView.class);
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.llBack, "method 'onBack'");
        this.view7f0800c4 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                privacyPolicyActivity.onBack();
            }
        });
    }

    public void unbind() {
        PrivacyPolicyActivity privacyPolicyActivity = this.target;
        if (privacyPolicyActivity != null) {
            this.target = null;
            privacyPolicyActivity.wvPrivacy = null;
            this.view7f0800c4.setOnClickListener(null);
            this.view7f0800c4 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
