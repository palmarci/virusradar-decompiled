package p008hu.gov.virusradar.Activities.Dialog;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.Dialog.NoNetworkView_ViewBinding */
public class NoNetworkView_ViewBinding implements Unbinder {
    private NoNetworkView target;
    private View view7f080184;

    public NoNetworkView_ViewBinding(NoNetworkView noNetworkView) {
        this(noNetworkView, noNetworkView.getWindow().getDecorView());
    }

    public NoNetworkView_ViewBinding(final NoNetworkView noNetworkView, View view) {
        this.target = noNetworkView;
        View findViewById = view.findViewById(C1130R.C1133id.tvRefresh);
        if (findViewById != null) {
            this.view7f080184 = findViewById;
            findViewById.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    noNetworkView.refreshClickListener();
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
