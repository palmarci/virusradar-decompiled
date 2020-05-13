package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.clans.fab.FloatingActionMenu;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.MainActivity_ViewBinding */
public class MainActivity_ViewBinding implements Unbinder {
    private MainActivity target;
    private View view7f0800ac;
    private View view7f0800b1;
    private View view7f0800c3;
    private View view7f0800c5;
    private View view7f0800c9;
    private View view7f0800ce;
    private View view7f080185;
    private View view7f080187;
    private View view7f080188;

    public MainActivity_ViewBinding(MainActivity mainActivity) {
        this(mainActivity, mainActivity.getWindow().getDecorView());
    }

    public MainActivity_ViewBinding(final MainActivity mainActivity, View view) {
        this.target = mainActivity;
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.ivBluetooth, "method 'onBluetooth'");
        mainActivity.ivBluetooth = (ImageView) Utils.castView(findRequiredView, C1130R.C1133id.ivBluetooth, "field 'ivBluetooth'", ImageView.class);
        this.view7f0800ac = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onBluetooth();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, C1130R.C1133id.ivLocation, "method 'onLocation'");
        mainActivity.ivLocation = (ImageView) Utils.castView(findRequiredView2, C1130R.C1133id.ivLocation, "field 'ivLocation'", ImageView.class);
        this.view7f0800b1 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onLocation();
            }
        });
        mainActivity.tvStatus = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvStatus, "field 'tvStatus'", TextView.class);
        mainActivity.tvLocationStatus = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvLocationStatus, "field 'tvLocationStatus'", TextView.class);
        View findRequiredView3 = Utils.findRequiredView(view, C1130R.C1133id.tvSend, "method 'onSendClick'");
        mainActivity.tvSend = (TextView) Utils.castView(findRequiredView3, C1130R.C1133id.tvSend, "field 'tvSend'", TextView.class);
        this.view7f080187 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onSendClick();
            }
        });
        View findRequiredView4 = Utils.findRequiredView(view, C1130R.C1133id.tvSettings, "method 'onSettingsClick'");
        mainActivity.tvSettings = (TextView) Utils.castView(findRequiredView4, C1130R.C1133id.tvSettings, "field 'tvSettings'", TextView.class);
        this.view7f080188 = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onSettingsClick();
            }
        });
        View findRequiredView5 = Utils.findRequiredView(view, C1130R.C1133id.tvResult, "method 'onResultClick'");
        mainActivity.tvResult = (TextView) Utils.castView(findRequiredView5, C1130R.C1133id.tvResult, "field 'tvResult'", TextView.class);
        this.view7f080185 = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onResultClick();
            }
        });
        mainActivity.llLanguageSelector = (LinearLayout) Utils.findOptionalViewAsType(view, C1130R.C1133id.llLanguageSelector, "field 'llLanguageSelector'", LinearLayout.class);
        mainActivity.famLocalization = (FloatingActionMenu) Utils.findOptionalViewAsType(view, C1130R.C1133id.famLocalization, "field 'famLocalization'", FloatingActionMenu.class);
        mainActivity.tvCurrentLocale = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvCurrentLocale, "field 'tvCurrentLocale'", TextView.class);
        mainActivity.ivLocaleArrow = (ImageView) Utils.findOptionalViewAsType(view, C1130R.C1133id.ivLocaleArrow, "field 'ivLocaleArrow'", ImageView.class);
        mainActivity.tvMessage = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvMessage, "field 'tvMessage'", TextView.class);
        mainActivity.toolbar = (Toolbar) Utils.findOptionalViewAsType(view, C1130R.C1133id.toolbar, "field 'toolbar'", Toolbar.class);
        mainActivity.ivOnOff = (ImageView) Utils.findOptionalViewAsType(view, C1130R.C1133id.ivOnOff, "field 'ivOnOff'", ImageView.class);
        mainActivity.tvButtonMessage = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvButtonMessage, "field 'tvButtonMessage'", TextView.class);
        mainActivity.pbTrigger = (ProgressBar) Utils.findOptionalViewAsType(view, C1130R.C1133id.pbTrigger, "field 'pbTrigger'", ProgressBar.class);
        mainActivity.tvActiveUser = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvActiveUser, "field 'tvActiveUser'", TextView.class);
        mainActivity.tvAppActive = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvAppActive, "field 'tvAppActive'", TextView.class);
        mainActivity.tvOnOff = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvOnOff, "field 'tvOnOff'", TextView.class);
        View findRequiredView6 = Utils.findRequiredView(view, C1130R.C1133id.llActiveUsers, "method 'onActiveUsers'");
        this.view7f0800c3 = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.onActiveUsers();
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, C1130R.C1133id.llBluetooth, "method 'bluetoothClick'");
        this.view7f0800c5 = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.bluetoothClick();
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, C1130R.C1133id.llLocation, "method 'locationClick'");
        this.view7f0800c9 = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.locationClick();
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, C1130R.C1133id.llShare, "method 'shareClick'");
        this.view7f0800ce = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                mainActivity.shareClick();
            }
        });
    }

    public void unbind() {
        MainActivity mainActivity = this.target;
        if (mainActivity != null) {
            this.target = null;
            mainActivity.ivBluetooth = null;
            mainActivity.ivLocation = null;
            mainActivity.tvStatus = null;
            mainActivity.tvLocationStatus = null;
            mainActivity.tvSend = null;
            mainActivity.tvSettings = null;
            mainActivity.tvResult = null;
            mainActivity.llLanguageSelector = null;
            mainActivity.famLocalization = null;
            mainActivity.tvCurrentLocale = null;
            mainActivity.ivLocaleArrow = null;
            mainActivity.tvMessage = null;
            mainActivity.toolbar = null;
            mainActivity.ivOnOff = null;
            mainActivity.tvButtonMessage = null;
            mainActivity.pbTrigger = null;
            mainActivity.tvActiveUser = null;
            mainActivity.tvAppActive = null;
            mainActivity.tvOnOff = null;
            this.view7f0800ac.setOnClickListener(null);
            this.view7f0800ac = null;
            this.view7f0800b1.setOnClickListener(null);
            this.view7f0800b1 = null;
            this.view7f080187.setOnClickListener(null);
            this.view7f080187 = null;
            this.view7f080188.setOnClickListener(null);
            this.view7f080188 = null;
            this.view7f080185.setOnClickListener(null);
            this.view7f080185 = null;
            this.view7f0800c3.setOnClickListener(null);
            this.view7f0800c3 = null;
            this.view7f0800c5.setOnClickListener(null);
            this.view7f0800c5 = null;
            this.view7f0800c9.setOnClickListener(null);
            this.view7f0800c9 = null;
            this.view7f0800ce.setOnClickListener(null);
            this.view7f0800ce = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
