package p008hu.gov.virusradar.Utilities;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.view.ContextThemeWrapper;
import androidx.core.app.ActivityCompat;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.IPermissionDialog;

/* renamed from: hu.gov.virusradar.Utilities.PermissionUtil */
public class PermissionUtil {
    private static final Intent[] POWERMANAGER_INTENTS;
    public static final int REQUEST_CODE_ASK_ALL_PERMISSIONS = 6789;
    public static final int REQUEST_CODE_ASK_CAMERA_PERMISSIONS = 123;
    public static final int REQUEST_CODE_ASK_LOCATION_PERMISSIONS = 234;
    public static final int REQUEST_CODE_ASK_SMS_PERMISSIONS = 456;
    public static final int REQUEST_CODE_ASK_STORAGE_PERMISSIONS = 345;

    static {
        String str = "com.huawei.systemmanager";
        String str2 = "com.coloros.safecenter";
        String str3 = "com.iqoo.secure";
        POWERMANAGER_INTENTS = new Intent[]{new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")), new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")), new Intent().setComponent(new ComponentName(str, "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity")), new Intent().setComponent(new ComponentName(str, "com.huawei.systemmanager.optimize.process.ProtectActivity")), new Intent().setComponent(new ComponentName(str, "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")), new Intent().setComponent(new ComponentName(str2, "com.coloros.safecenter.permission.startup.StartupAppListActivity")), new Intent().setComponent(new ComponentName(str2, "com.coloros.safecenter.startupapp.StartupAppListActivity")), new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")), new Intent().setComponent(new ComponentName(str3, "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")), new Intent().setComponent(new ComponentName(str3, "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")), new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")), new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")), new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.MainActivity"))};
    }

    private static Intent getSettingsIntent() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", VirusTraceApp.getInstance().getPackageName(), null));
        return intent;
    }

    public static boolean arePermissionsGranted(String[] strArr) {
        if (VERSION.SDK_INT < 23) {
            return true;
        }
        boolean z = true;
        for (String checkSelfPermission : strArr) {
            z &= VirusTraceApp.getInstance().checkSelfPermission(checkSelfPermission) == 0;
        }
        return z;
    }

    public static void requestAllPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.WAKE_LOCK", "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"}, REQUEST_CODE_ASK_ALL_PERMISSIONS);
    }

    public static boolean areAllPermissionsGranted(Activity activity) {
        return arePermissionsGranted(new String[]{"android.permission.WAKE_LOCK", "android.permission.BLUETOOTH_ADMIN", "android.permission.BLUETOOTH", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"});
    }

    public static boolean isSmsPermissionGranted(Activity activity) {
        return arePermissionsGranted(new String[]{"android.permission.RECEIVE_SMS", "android.permission.READ_SMS"});
    }

    public static void requestSmsPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.RECEIVE_SMS", "android.permission.READ_SMS"}, REQUEST_CODE_ASK_ALL_PERMISSIONS);
    }

    /* access modifiers changed from: private */
    public static void goToSettings(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }

    public static void displayCustomPermissionDialog(final Context context, final IPermissionDialog iPermissionDialog, String str) {
        Builder builder = new Builder(new ContextThemeWrapper(context, 16974123));
        builder.setMessage(str);
        builder.setPositiveButton("ALLOW", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                PermissionUtil.goToSettings(context);
                IPermissionDialog iPermissionDialog = iPermissionDialog;
                if (iPermissionDialog != null) {
                    iPermissionDialog.onResult(true);
                }
            }
        });
        builder.setNegativeButton("DENY", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                IPermissionDialog iPermissionDialog = iPermissionDialog;
                if (iPermissionDialog != null) {
                    iPermissionDialog.onResult(false);
                }
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    public static boolean requestBatteryOptimisationDisable(Activity activity, boolean z) {
        if (VERSION.SDK_INT >= 23) {
            Intent intent = new Intent();
            String packageName = activity.getPackageName();
            PowerManager powerManager = (PowerManager) activity.getSystemService("power");
            if (powerManager != null && !powerManager.isIgnoringBatteryOptimizations(packageName)) {
                intent.setAction("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS");
                StringBuilder sb = new StringBuilder();
                sb.append("package:");
                sb.append(packageName);
                intent.setData(Uri.parse(sb.toString()));
                if (z) {
                    activity.startActivity(intent);
                }
                return false;
            }
        }
        return true;
    }

    public static void startVendorSpecificBatteryPrompt(Context context) {
        Intent[] intentArr = POWERMANAGER_INTENTS;
        int length = intentArr.length;
        int i = 0;
        while (i < length) {
            Intent intent = intentArr[i];
            if (context.getPackageManager().resolveActivity(intent, 65536) == null) {
                i++;
            } else if (intent.getComponent().toString().contains("miui")) {
                context.startActivity(getSettingsIntent());
                return;
            } else {
                context.startActivity(intent);
                return;
            }
        }
    }

    public static Uri getVideoInstructionsUri(Context context) {
        Intent[] intentArr = POWERMANAGER_INTENTS;
        int length = intentArr.length;
        int i = 0;
        while (true) {
            String str = "/";
            String str2 = "android.resource://";
            if (i < length) {
                Intent intent = intentArr[i];
                if (context.getPackageManager().resolveActivity(intent, 65536) == null || !intent.getComponent().toString().contains("miui")) {
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append(context.getPackageName());
                    sb.append(str);
                    sb.append(C1130R.raw.xiaomi_instructions);
                    return Uri.parse(sb.toString());
                }
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str2);
                sb2.append(context.getPackageName());
                sb2.append(str);
                sb2.append(C1130R.raw.huawei_instructions);
                return Uri.parse(sb2.toString());
            }
        }
    }

    public static boolean hasVendorSpecificRestrictions(Context context) {
        for (Intent resolveActivity : POWERMANAGER_INTENTS) {
            if (context.getPackageManager().resolveActivity(resolveActivity, 65536) != null) {
                return true;
            }
        }
        return false;
    }
}
