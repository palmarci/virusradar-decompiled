package p008hu.gov.virusradar.Activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.github.clans.fab.FloatingActionMenu;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.ConsentActivity_ViewBinding */
public class ConsentActivity_ViewBinding implements Unbinder {
    private ConsentActivity target;
    private View view7f08004d;

    public ConsentActivity_ViewBinding(ConsentActivity consentActivity) {
        this(consentActivity, consentActivity.getWindow().getDecorView());
    }

    public ConsentActivity_ViewBinding(final ConsentActivity consentActivity, View view) {
        this.target = consentActivity;
        consentActivity.famLocalization = (FloatingActionMenu) Utils.findOptionalViewAsType(view, C1130R.C1133id.famLocalization, "field 'famLocalization'", FloatingActionMenu.class);
        View findViewById = view.findViewById(C1130R.C1133id.btnAgree);
        if (findViewById != null) {
            this.view7f08004d = findViewById;
            findViewById.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    consentActivity.agreeListener();
                }
            });
        }
    }

    public void unbind() {
        ConsentActivity consentActivity = this.target;
        if (consentActivity != null) {
            this.target = null;
            consentActivity.famLocalization = null;
            View view = this.view7f08004d;
            if (view != null) {
                view.setOnClickListener(null);
                this.view7f08004d = null;
                return;
            }
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
