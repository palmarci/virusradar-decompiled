package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.NotificationsActivity_ViewBinding */
public class NotificationsActivity_ViewBinding implements Unbinder {
    private NotificationsActivity target;
    private View view7f0800c4;

    public NotificationsActivity_ViewBinding(NotificationsActivity notificationsActivity) {
        this(notificationsActivity, notificationsActivity.getWindow().getDecorView());
    }

    public NotificationsActivity_ViewBinding(final NotificationsActivity notificationsActivity, View view) {
        this.target = notificationsActivity;
        notificationsActivity.rvNotifications = (RecyclerView) Utils.findOptionalViewAsType(view, C1130R.C1133id.rvNotifications, "field 'rvNotifications'", RecyclerView.class);
        notificationsActivity.tvNoItems = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvNoItems, "field 'tvNoItems'", TextView.class);
        notificationsActivity.footerView = (LinearLayout) Utils.findOptionalViewAsType(view, C1130R.C1133id.footerView, "field 'footerView'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, C1130R.C1133id.llBack, "method 'onBack'");
        this.view7f0800c4 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                notificationsActivity.onBack();
            }
        });
    }

    public void unbind() {
        NotificationsActivity notificationsActivity = this.target;
        if (notificationsActivity != null) {
            this.target = null;
            notificationsActivity.rvNotifications = null;
            notificationsActivity.tvNoItems = null;
            notificationsActivity.footerView = null;
            this.view7f0800c4.setOnClickListener(null);
            this.view7f0800c4 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
