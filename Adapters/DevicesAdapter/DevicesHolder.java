package p008hu.gov.virusradar.Adapters.DevicesAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import butterknife.BindView;
import butterknife.ButterKnife;

/* renamed from: hu.gov.virusradar.Adapters.DevicesAdapter.DevicesHolder */
public class DevicesHolder extends ViewHolder {
    @BindView(2131231085)
    public TextView tvAlias;
    @BindView(2131231093)
    public TextView tvDistance;
    @BindView(2131231094)
    public TextView tvDuration;
    @BindView(2131231095)
    public TextView tvEndDate;
    @BindView(2131231102)
    public TextView tvNotificationMessage;
    @BindView(2131231113)
    public TextView tvStartDate;

    public DevicesHolder(View view, Context context) {
        super(view);
        ButterKnife.bind((Object) this, view);
    }
}
