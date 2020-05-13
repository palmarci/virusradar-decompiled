package p008hu.gov.virusradar.Activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.AboutActivity_ViewBinding */
public class AboutActivity_ViewBinding implements Unbinder {
    private AboutActivity target;
    private View view7f0800ab;
    private View view7f0800ae;
    private View view7f0800af;
    private View view7f0800b2;
    private View view7f0800c4;

    public AboutActivity_ViewBinding(AboutActivity aboutActivity) {
        this(aboutActivity, aboutActivity.getWindow().getDecorView());
    }

    public AboutActivity_ViewBinding(final AboutActivity aboutActivity, View view) {
        this.target = aboutActivity;
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.llBack, "method 'onBack'");
        this.view7f0800c4 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutActivity.onBack();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, C1130R.C1133id.ivInnovacios, "method 'onInnovacios'");
        this.view7f0800ae = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutActivity.onInnovacios();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, C1130R.C1133id.ivBiztributor, "method 'onBiztributor'");
        this.view7f0800ab = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutActivity.onBiztributor();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, C1130R.C1133id.ivKifu, "method 'onKifu'");
        this.view7f0800af = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutActivity.onKifu();
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, C1130R.C1133id.ivNextsense, "method 'onNextsense'");
        this.view7f0800b2 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                aboutActivity.onNextsense();
            }
        });
    }

    public void unbind() {
        if (this.target != null) {
            this.target = null;
            this.view7f0800c4.setOnClickListener(null);
            this.view7f0800c4 = null;
            this.view7f0800ae.setOnClickListener(null);
            this.view7f0800ae = null;
            this.view7f0800ab.setOnClickListener(null);
            this.view7f0800ab = null;
            this.view7f0800af.setOnClickListener(null);
            this.view7f0800af = null;
            this.view7f0800b2.setOnClickListener(null);
            this.view7f0800b2 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
