package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.SplashActivity_ViewBinding */
public class SplashActivity_ViewBinding implements Unbinder {
    private SplashActivity target;

    public SplashActivity_ViewBinding(SplashActivity splashActivity) {
        this(splashActivity, splashActivity.getWindow().getDecorView());
    }

    public SplashActivity_ViewBinding(SplashActivity splashActivity, View view) {
        this.target = splashActivity;
        splashActivity.llSplash = (FrameLayout) Utils.findOptionalViewAsType(view, C1130R.C1133id.llSplash, "field 'llSplash'", FrameLayout.class);
        splashActivity.ivSplash = (ImageView) Utils.findOptionalViewAsType(view, C1130R.C1133id.ivSplash, "field 'ivSplash'", ImageView.class);
    }

    public void unbind() {
        SplashActivity splashActivity = this.target;
        if (splashActivity != null) {
            this.target = null;
            splashActivity.llSplash = null;
            splashActivity.ivSplash = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
