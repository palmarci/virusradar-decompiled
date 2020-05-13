package p008hu.gov.virusradar.Utilities;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import p008hu.gov.virusradar.GCM.FirebaseMessageHandler;

/* renamed from: hu.gov.virusradar.Utilities.FirebaseTokenNotificationUtil */
public class FirebaseTokenNotificationUtil {
    private static final String keyFirebaseToken = "FirebaseToken";

    public static void saveFirebaseTokenInSharedPref(String str) {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putString(keyFirebaseToken, str);
        edit.commit();
    }

    public static String getFirebaseFromSharedPref() {
        SharedPreferences sharedPreferences = VirusTraceApp.sharedPref;
        String str = keyFirebaseToken;
        String str2 = "";
        if (!sharedPreferences.getString(str, str2).equals(str2)) {
            return VirusTraceApp.sharedPref.getString(str, str2);
        }
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            public void onComplete(Task<InstanceIdResult> task) {
                if (task.isSuccessful()) {
                    FirebaseMessageHandler.sendRegistrationToServer(((InstanceIdResult) task.getResult()).getToken(), Integer.valueOf(2));
                }
            }
        });
        return str2;
    }

    public static void clearFirebaseTokenFromPref() {
        Editor edit = VirusTraceApp.sharedPref.edit();
        edit.putString(keyFirebaseToken, null);
        edit.commit();
    }
}
