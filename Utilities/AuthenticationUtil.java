package p008hu.gov.virusradar.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import p008hu.gov.virusradar.Activities.ConsentActivity;

/* renamed from: hu.gov.virusradar.Utilities.AuthenticationUtil */
public class AuthenticationUtil {
    private static final String AUTH_COOKIE_NAME = "vt_ath";
    private static final String keyAlias = "Alias";
    private static final String keyAliasList = "AliasList";
    private static final String keyFirstTimeLaunch = "IsFirstTimeLaunch";

    public static void saveAlias(String str) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putString(keyAlias, str);
        edit.commit();
    }

    public static String getAlias() {
        SharedPreferences sharedPreferences = VirusTraceApp.sharedPref;
        String str = keyAlias;
        String str2 = "";
        return !sharedPreferences.getString(str, str2).equals(str2) ? VirusTraceApp.sharedPref.getString(str, str2) : str2;
    }

    public static void clearAuthentication(Context context) {
        startConsentActivity(context);
    }

    private static void startConsentActivity(Context context) {
        Intent intent = new Intent(context, ConsentActivity.class);
        intent.addFlags(335544320);
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        context.startActivity(intent);
    }

    public static void saveArrayList(ArrayList<String> arrayList) {
        HashSet hashSet = new HashSet();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hashSet.add((String) it.next());
        }
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putStringSet(keyAliasList, hashSet);
        edit.commit();
    }

    public static void setFirstTimeLaunch(boolean z) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putBoolean(keyFirstTimeLaunch, z);
        edit.commit();
    }

    public static boolean isFirstTimeLaunch() {
        return VirusTraceApp.sharedPref.getBoolean(keyFirstTimeLaunch, true);
    }

    public static boolean isUserLogged() {
        try {
            for (Cookie name : new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(VirusTraceApp.getInstance())).loadForRequest((HttpUrl) Objects.requireNonNull(HttpUrl.get(new URI(VirusTraceApp.globalEnvironment.globalURL))))) {
                if (name.name().equals(AUTH_COOKIE_NAME)) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
