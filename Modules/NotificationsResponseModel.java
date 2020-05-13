package p008hu.gov.virusradar.Modules;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* renamed from: hu.gov.virusradar.Modules.NotificationsResponseModel */
public class NotificationsResponseModel {
    private static final String keyNotifications = "notifications";
    private static final String keyTotal = "total";
    @SerializedName("notifications")
    public ArrayList<NotificationModel> Notifications;
    @SerializedName("total")
    public Integer Total;
}
