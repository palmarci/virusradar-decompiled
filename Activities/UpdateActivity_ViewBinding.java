package p008hu.gov.virusradar.Activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.UpdateActivity_ViewBinding */
public class UpdateActivity_ViewBinding implements Unbinder {
    private UpdateActivity target;
    private View view7f08018e;

    public UpdateActivity_ViewBinding(UpdateActivity updateActivity) {
        this(updateActivity, updateActivity.getWindow().getDecorView());
    }

    public UpdateActivity_ViewBinding(final UpdateActivity updateActivity, View view) {
        this.target = updateActivity;
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.tvUpgrade, "method 'openGooglePlay'");
        this.view7f08018e = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                updateActivity.openGooglePlay(view);
            }
        });
    }

    public void unbind() {
        if (this.target != null) {
            this.target = null;
            this.view7f08018e.setOnClickListener(null);
            this.view7f08018e = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
