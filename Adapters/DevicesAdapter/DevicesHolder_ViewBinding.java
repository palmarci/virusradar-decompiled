package p008hu.gov.virusradar.Adapters.DevicesAdapter;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Adapters.DevicesAdapter.DevicesHolder_ViewBinding */
public class DevicesHolder_ViewBinding implements Unbinder {
    private DevicesHolder target;

    public DevicesHolder_ViewBinding(DevicesHolder devicesHolder, View view) {
        this.target = devicesHolder;
        devicesHolder.tvAlias = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvAlias, "field 'tvAlias'", TextView.class);
        devicesHolder.tvStartDate = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvStartDate, "field 'tvStartDate'", TextView.class);
        devicesHolder.tvEndDate = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvEndDate, "field 'tvEndDate'", TextView.class);
        devicesHolder.tvDuration = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvDuration, "field 'tvDuration'", TextView.class);
        devicesHolder.tvDistance = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvDistance, "field 'tvDistance'", TextView.class);
        devicesHolder.tvNotificationMessage = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvNotificationMessage, "field 'tvNotificationMessage'", TextView.class);
    }

    public void unbind() {
        DevicesHolder devicesHolder = this.target;
        if (devicesHolder != null) {
            this.target = null;
            devicesHolder.tvAlias = null;
            devicesHolder.tvStartDate = null;
            devicesHolder.tvEndDate = null;
            devicesHolder.tvDuration = null;
            devicesHolder.tvDistance = null;
            devicesHolder.tvNotificationMessage = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
