package p008hu.gov.virusradar.Activities.Dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.Dialog.NoServiceView_ViewBinding */
public class NoServiceView_ViewBinding implements Unbinder {
    private NoServiceView target;
    private View view7f080184;

    public NoServiceView_ViewBinding(NoServiceView noServiceView) {
        this(noServiceView, noServiceView.getWindow().getDecorView());
    }

    public NoServiceView_ViewBinding(final NoServiceView noServiceView, View view) {
        this.target = noServiceView;
        View findViewById = view.findViewById(C1130R.C1133id.tvRefresh);
        if (findViewById != null) {
            this.view7f080184 = findViewById;
            findViewById.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    noServiceView.refreshClickListener();
                }
            });
        }
    }

    public void unbind() {
        if (this.target != null) {
            this.target = null;
            View view = this.view7f080184;
            if (view != null) {
                view.setOnClickListener(null);
                this.view7f080184 = null;
                return;
            }
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
