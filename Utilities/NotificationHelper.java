package p008hu.gov.virusradar.Utilities;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import androidx.core.app.NotificationCompat.Builder;
import p008hu.gov.virusradar.Activities.MainActivity;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;

/* renamed from: hu.gov.virusradar.Utilities.NotificationHelper */
public class NotificationHelper {
    private static final int BLUETOOTH_NOTIFICATION_ID = 2345;
    private static final String MAIN_CHANNEL = "mainChannel";
    private static final int SERVICE_NOTIFICATION_ID = 1234;
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public static void startServiceNotification(Service service, Long l, Long l2) {
        String serviceContentTitle = getServiceContentTitle();
        NotificationManager notificationManager = (NotificationManager) service.getSystemService("notification");
        String str = MAIN_CHANNEL;
        Builder builder = new Builder(service, str);
        builder.setSmallIcon(C1130R.C1132drawable.notification_icon);
        builder.setContentTitle(serviceContentTitle);
        builder.setOngoing(true);
        builder.setOnlyAlertOnce(true);
        builder.setVibrate(null);
        builder.setContentIntent(constructPendingIntent(service, MainActivity.class));
        if (VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(str, "App", 4));
            builder.setChannelId(str);
        }
        notificationManager.cancel(BLUETOOTH_NOTIFICATION_ID);
        service.startForeground(SERVICE_NOTIFICATION_ID, builder.build());
    }

    public static void startNoBluetoothNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
        String str = MAIN_CHANNEL;
        Builder builder = new Builder(context, str);
        builder.setSmallIcon(C1130R.C1132drawable.notification_icon);
        builder.setContentTitle(VirusTraceApp.getInstance().getString(C1130R.string.service_is_closed));
        builder.setContentText(VirusTraceApp.getInstance().getString(C1130R.string.bluetooth_is_off));
        builder.setOnlyAlertOnce(true);
        builder.setContentIntent(constructPendingIntent(context, MainActivity.class));
        if (VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(str, "App", 4));
            builder.setChannelId(str);
        }
        notificationManager.cancel(SERVICE_NOTIFICATION_ID);
        notificationManager.notify(BLUETOOTH_NOTIFICATION_ID, builder.build());
    }

    private static String getServiceContentTitle() {
        if (!VirusTraceApp.globalEnvironment.debugging) {
            return VirusTraceApp.getInstance().getString(C1130R.string.scanner_active);
        }
        String string = VirusTraceApp.getInstance().getString(C1130R.string.device_detected);
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(DeviceListModel.getInstance() == null ? 0 : DeviceListModel.getInstance().size());
        return String.format(string, objArr);
    }

    private static PendingIntent constructPendingIntent(Context context, Class<? extends Activity> cls) {
        Intent intent = new Intent(context, cls);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.addFlags(335544320);
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
}
