package p008hu.gov.virusradar.Adapters.NotificationsAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

/* renamed from: hu.gov.virusradar.Adapters.NotificationsAdapter.NotificationsHolder */
public class NotificationsHolder extends ViewHolder {
    @BindView(2131231092)
    public TextView tvDate;
    @BindView(2131231102)
    public TextView tvNotificationMessage;
    @BindView(2131231103)
    public TextView tvNotificationTitle;

    public NotificationsHolder(View view, Context context) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }
}
