package p008hu.gov.virusradar.GCM;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.util.Log;
import androidx.core.app.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Calendar;
import p008hu.gov.virusradar.Activities.SplashActivity;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Modules.Input.RegisterDeviceTokenInput;
import p008hu.gov.virusradar.Modules.PushNotificationModel;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.UserService;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p008hu.gov.virusradar.Utilities.FirebaseTokenNotificationUtil;

/* renamed from: hu.gov.virusradar.GCM.FirebaseMessageHandler */
public class FirebaseMessageHandler extends FirebaseMessagingService {
    private static final String MESSAGE_KEY = "message";
    private static final String PARAMETER_KEY = "parameter";
    private static final String SOUND_KEY = "sound";
    private static final String TITLE_KEY = "title";
    private static final String TYPE_KEY = "type";
    public static final String keyNotificationModel = "PushNotificationModel";
    public static final Integer keyPlatform = Integer.valueOf(2);
    private String CHANNEL_ID = "my_channel_01";
    private int importance = 4;
    private CharSequence name = "My Channel";
    private NotificationManager notificationManager;
    private PushNotificationModel pushNotificationModel;

    public void onNewToken(String str) {
        super.onNewToken(str);
        sendRegistrationToServer(str, keyPlatform);
    }

    public static void sendRegistrationToServer(String str, Integer num) {
        RegisterDeviceTokenInput registerDeviceTokenInput = new RegisterDeviceTokenInput(str, num);
        FirebaseTokenNotificationUtil.saveFirebaseTokenInSharedPref(str);
        if (AuthenticationUtil.isUserLogged()) {
            UserService.registerDevice(registerDeviceTokenInput, new IResponseCallback() {
                public void onSuccess(Object obj) {
                    String str = "";
                    Log.d(str, str);
                }

                public void onError(CustomError customError) {
                    String str = "";
                    Log.d(str, str);
                }
            });
        }
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
        this.pushNotificationModel = new PushNotificationModel();
        this.pushNotificationModel.title = (String) remoteMessage.getData().get("title");
        this.pushNotificationModel.message = (String) remoteMessage.getData().get("message");
        this.pushNotificationModel.sound = (String) remoteMessage.getData().get(SOUND_KEY);
        this.pushNotificationModel.parameter = (String) remoteMessage.getData().get(PARAMETER_KEY);
        this.pushNotificationModel.notificationType = Integer.parseInt((String) remoteMessage.getData().get("type"));
        this.pushNotificationModel.f86id = (int) Calendar.getInstance().getTimeInMillis();
        createNotification();
    }

    public void createNotification() {
        if (this.notificationManager == null) {
            this.notificationManager = (NotificationManager) getSystemService("notification");
        }
        if (VERSION.SDK_INT >= 26) {
            this.notificationManager.createNotificationChannel(new NotificationChannel(this.CHANNEL_ID, this.name, this.importance));
        }
        this.notificationManager.notify(0, createNotificationBuilder().build());
    }

    private Builder createNotificationBuilder() {
        return new Builder(this, this.CHANNEL_ID).setChannelId(this.CHANNEL_ID).setSmallIcon(C1130R.C1132drawable.notification_icon).setLargeIcon(BitmapFactory.decodeResource(getResources(), C1130R.mipmap.ic_launcher)).setContentTitle(this.pushNotificationModel.title).setContentText(this.pushNotificationModel.message).setContentIntent(getPendingIntent()).setWhen(System.currentTimeMillis()).setPriority(2).setAutoCancel(true);
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(keyNotificationModel, this.pushNotificationModel);
        return PendingIntent.getActivity(this, 0, intent, 134217728);
    }
}
