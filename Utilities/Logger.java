package p008hu.gov.virusradar.Utilities;

import android.util.Log;

/* renamed from: hu.gov.virusradar.Utilities.Logger */
public class Logger {
    private static final String MAIN_LABEL = "MAIN_LOGGER>>>";
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public static void log(String str) {
        if (VirusTraceApp.globalEnvironment.debugging) {
            Log.d(MAIN_LABEL, String.format("%s >>> %s", new Object[]{Util.formatDate(Long.valueOf(System.currentTimeMillis()), TIME_FORMAT), str}));
        }
    }
}
