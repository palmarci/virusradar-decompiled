package p008hu.gov.virusradar.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: hu.gov.virusradar.Utilities.InternetConnectivityUtil */
public class InternetConnectivityUtil {
    private static final String STRING_MOBILE = "MOBILE";
    private static final String STRING_WIFI = "WIFI";

    public static boolean isNetworkAvailable(Context context) {
        boolean z;
        boolean z2;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            z = false;
            z2 = false;
        } else {
            z2 = activeNetworkInfo.getTypeName().equalsIgnoreCase(STRING_WIFI) && activeNetworkInfo.isConnected();
            z = activeNetworkInfo.getTypeName().equalsIgnoreCase(STRING_MOBILE) && activeNetworkInfo.isConnected();
        }
        if (z2 || z) {
            return true;
        }
        return false;
    }
}
