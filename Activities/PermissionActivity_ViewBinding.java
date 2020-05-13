package p008hu.gov.virusradar.Activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.PermissionActivity_ViewBinding */
public class PermissionActivity_ViewBinding implements Unbinder {
    private PermissionActivity target;
    private View view7f08004f;

    public PermissionActivity_ViewBinding(PermissionActivity permissionActivity) {
        this(permissionActivity, permissionActivity.getWindow().getDecorView());
    }

    public PermissionActivity_ViewBinding(final PermissionActivity permissionActivity, View view) {
        this.target = permissionActivity;
        View findViewById = view.findViewById(C1130R.C1133id.btnProceed);
        if (findViewById != null) {
            this.view7f08004f = findViewById;
            findViewById.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    permissionActivity.proceedListener();
                }
            });
        }
    }

    public void unbind() {
        if (this.target != null) {
            this.target = null;
            View view = this.view7f08004f;
            if (view != null) {
                view.setOnClickListener(null);
                this.view7f08004f = null;
                return;
            }
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
