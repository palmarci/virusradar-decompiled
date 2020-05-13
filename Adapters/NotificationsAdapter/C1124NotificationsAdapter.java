package p008hu.gov.virusradar.Adapters.NotificationsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.ArrayList;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.IFilterListener;
import p008hu.gov.virusradar.Modules.NotificationModel;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Adapters.NotificationsAdapter.NotificationsAdapter */
public class C1124NotificationsAdapter extends Adapter<NotificationsHolder> {
    private Context context;
    private IFilterListener listener;
    private ArrayList<NotificationModel> notifications = new ArrayList<>();

    public C1124NotificationsAdapter(Context context2) {
        this.context = context2;
    }

    public void setListener(IFilterListener iFilterListener) {
        this.listener = iFilterListener;
    }

    public NotificationsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new NotificationsHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C1130R.layout.item_notification, viewGroup, false), viewGroup.getContext());
    }

    public void onBindViewHolder(NotificationsHolder notificationsHolder, int i) {
        NotificationModel notificationModel = (NotificationModel) this.notifications.get(i);
        notificationsHolder.tvDate.setText(Util.formatDateHour(notificationModel.CreatedOn));
        notificationsHolder.tvNotificationTitle.setText(notificationModel.Title);
        notificationsHolder.tvNotificationMessage.setText(notificationModel.Message);
    }

    public int getItemCount() {
        return this.notifications.size();
    }

    public void addNotifications(ArrayList<NotificationModel> arrayList) {
        this.notifications = arrayList;
        notifyDataSetChanged();
    }
}
