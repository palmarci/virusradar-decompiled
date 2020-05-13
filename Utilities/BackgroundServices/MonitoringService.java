package p008hu.gov.virusradar.Utilities.BackgroundServices;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.IBinder;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.TimeFrame;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper.IAdvertisementListener;
import p008hu.gov.virusradar.Utilities.Logger;
import p008hu.gov.virusradar.Utilities.NotificationHelper;
import p008hu.gov.virusradar.Utilities.Util;
import p008hu.gov.virusradar.Utilities.VirusTraceApp;

/* renamed from: hu.gov.virusradar.Utilities.BackgroundServices.MonitoringService */
public class MonitoringService extends Service implements IAdvertisementListener {
    private static final String ACTION_STOP = String.format("%s.%s", new Object[]{MonitoringService.class.getCanonicalName(), "STOP"});
    private static final int SAVE_DELAY = 60000;
    private static final int STOP_TIME_OUT = 30500;
    private static final int UPDATE_DELAY = 30000;
    private static long startTime = System.currentTimeMillis();
    private AdvertisingHelper advertisingHelper;
    private long lastSave = System.currentTimeMillis();
    private long lastUpdate = System.currentTimeMillis();
    private boolean restart = true;
    BroadcastReceiver stopReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            MonitoringService.this.killService();
        }
    };
    private Thread updateThread;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void startService(Context context) {
        if (AliasManager.getInstance().hasAlias()) {
            Intent intent = new Intent(VirusTraceApp.getInstance(), MonitoringService.class);
            if (VERSION.SDK_INT >= 26) {
                context.startForegroundService(intent);
            } else {
                context.startService(intent);
            }
        }
    }

    public static void stopService() {
        VirusTraceApp.getInstance().sendBroadcast(new Intent(ACTION_STOP));
    }

    public void onCreate() {
        super.onCreate();
        NotificationHelper.startServiceNotification(this, Long.valueOf(this.lastUpdate), Long.valueOf(0));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_STOP);
        registerReceiver(this.stopReceiver, intentFilter);
        this.advertisingHelper = new AdvertisingHelper(this, this);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.advertisingHelper.activate();
        return 1;
    }

    public void updateNotificationAsync() {
        Thread thread = this.updateThread;
        if (thread == null || !thread.isAlive()) {
            this.updateThread = new Thread(new Runnable() {
                public final void run() {
                    MonitoringService.this.lambda$updateNotificationAsync$0$MonitoringService();
                }
            });
            this.updateThread.start();
        }
    }

    public /* synthetic */ void lambda$updateNotificationAsync$0$MonitoringService() {
        do {
            updateService();
        } while (Util.sleep(UPDATE_DELAY));
    }

    private void updateService() {
        if (System.currentTimeMillis() - this.lastUpdate <= 30500 || !noBluetoothAction()) {
            NotificationHelper.startServiceNotification(this, Long.valueOf(this.lastUpdate), Long.valueOf(System.currentTimeMillis() - startTime));
            try {
                Logger.log(String.format("UPDATE --> %s", new Object[]{((TimeFrame) ((DeviceModel) DeviceListModel.getInstance().get(0)).timeFrames.get(0)).getDurationMinutes()}));
            } catch (Exception unused) {
            }
        } else {
            Logger.log("STOPPED");
        }
        this.advertisingHelper.checkAndRenewAlias();
        if (System.currentTimeMillis() - this.lastSave > 60000) {
            DeviceListModel.updateStorage();
            this.lastSave = System.currentTimeMillis();
        }
    }

    private boolean noBluetoothAction() {
        if (AdvertisingHelper.isBluetoothOn()) {
            return false;
        }
        killService();
        NotificationHelper.startNoBluetoothNotification(this);
        return true;
    }

    /* access modifiers changed from: private */
    public void killService() {
        this.restart = false;
        try {
            this.updateThread.interrupt();
        } catch (Exception unused) {
        }
        this.advertisingHelper.deactivate();
        stopSelf();
    }

    public static void broadcastDestroyed(Context context) {
        Intent intent = new Intent();
        intent.setAction("restartservice");
        intent.setClass(context, MonitorRestarter.class);
        context.sendBroadcast(intent);
    }

    public void onDeviceListUpdate(DeviceListModel deviceListModel) {
        this.lastUpdate = System.currentTimeMillis();
        updateNotificationAsync();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.stopReceiver);
        if (this.restart) {
            Logger.log("BROADCAST");
            broadcastDestroyed(VirusTraceApp.getInstance());
        }
    }
}
