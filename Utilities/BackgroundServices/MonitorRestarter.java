package p008hu.gov.virusradar.Utilities.BackgroundServices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Utilities.Logger;

/* renamed from: hu.gov.virusradar.Utilities.BackgroundServices.MonitorRestarter */
public class MonitorRestarter extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Logger.log("RESTARTER");
        if (AliasManager.getInstance().hasAlias()) {
            MonitoringService.startService(context);
        }
    }
}
