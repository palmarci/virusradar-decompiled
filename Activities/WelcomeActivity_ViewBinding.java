package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.github.clans.fab.FloatingActionMenu;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.WelcomeActivity_ViewBinding */
public class WelcomeActivity_ViewBinding implements Unbinder {
    private WelcomeActivity target;

    public WelcomeActivity_ViewBinding(WelcomeActivity welcomeActivity) {
        this(welcomeActivity, welcomeActivity.getWindow().getDecorView());
    }

    public WelcomeActivity_ViewBinding(WelcomeActivity welcomeActivity, View view) {
        this.target = welcomeActivity;
        welcomeActivity.viewPager = (ViewPager) Utils.findOptionalViewAsType(view, C1130R.C1133id.view_pager, "field 'viewPager'", ViewPager.class);
        welcomeActivity.dotsLayout = (LinearLayout) Utils.findOptionalViewAsType(view, C1130R.C1133id.layoutDots, "field 'dotsLayout'", LinearLayout.class);
        welcomeActivity.btnSkip = (Button) Utils.findOptionalViewAsType(view, C1130R.C1133id.btn_skip, "field 'btnSkip'", Button.class);
        welcomeActivity.btnNext = (Button) Utils.findOptionalViewAsType(view, C1130R.C1133id.btn_next, "field 'btnNext'", Button.class);
        welcomeActivity.famLocalization = (FloatingActionMenu) Utils.findOptionalViewAsType(view, C1130R.C1133id.famLocalization, "field 'famLocalization'", FloatingActionMenu.class);
    }

    public void unbind() {
        WelcomeActivity welcomeActivity = this.target;
        if (welcomeActivity != null) {
            this.target = null;
            welcomeActivity.viewPager = null;
            welcomeActivity.dotsLayout = null;
            welcomeActivity.btnSkip = null;
            welcomeActivity.btnNext = null;
            welcomeActivity.famLocalization = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
