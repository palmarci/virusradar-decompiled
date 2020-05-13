package p008hu.gov.virusradar.Utilities;

import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;

/* renamed from: hu.gov.virusradar.Utilities.SharedPrefsUtil */
public class SharedPrefsUtil {
    public static void saveBoolean(String str, boolean z) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    public static void saveInt(String str, int i) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putInt(str, i);
        edit.apply();
    }

    public static void saveString(String str, String str2) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public static void saveObject(String str, Object obj) {
        saveString(str, new Gson().toJson(obj));
    }

    public static boolean getBoolean(String str, boolean z) {
        return VirusTraceApp.sharedPref.contains(str) ? VirusTraceApp.sharedPref.getBoolean(str, z) : z;
    }

    public static Integer getInt(String str) {
        if (VirusTraceApp.sharedPref.contains(str)) {
            return Integer.valueOf(VirusTraceApp.sharedPref.getInt(str, 0));
        }
        return null;
    }

    public static String getString(String str) {
        if (VirusTraceApp.sharedPref.contains(str)) {
            return VirusTraceApp.sharedPref.getString(str, null);
        }
        return null;
    }

    public static Object getObject(String str, Class cls) {
        return new Gson().fromJson(getString(str), cls);
    }

    public static void delete(String... strArr) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        for (String remove : strArr) {
            edit.remove(remove);
        }
        edit.apply();
    }
}
