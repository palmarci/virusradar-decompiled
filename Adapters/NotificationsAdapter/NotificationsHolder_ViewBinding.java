package p008hu.gov.virusradar.Adapters.NotificationsAdapter;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Adapters.NotificationsAdapter.NotificationsHolder_ViewBinding */
public class NotificationsHolder_ViewBinding implements Unbinder {
    private NotificationsHolder target;

    public NotificationsHolder_ViewBinding(NotificationsHolder notificationsHolder, View view) {
        this.target = notificationsHolder;
        notificationsHolder.tvDate = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvDate, "field 'tvDate'", TextView.class);
        notificationsHolder.tvNotificationTitle = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvNotificationTitle, "field 'tvNotificationTitle'", TextView.class);
        notificationsHolder.tvNotificationMessage = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvNotificationMessage, "field 'tvNotificationMessage'", TextView.class);
    }

    public void unbind() {
        NotificationsHolder notificationsHolder = this.target;
        if (notificationsHolder != null) {
            this.target = null;
            notificationsHolder.tvDate = null;
            notificationsHolder.tvNotificationTitle = null;
            notificationsHolder.tvNotificationMessage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
