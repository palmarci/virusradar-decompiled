package p008hu.gov.virusradar.Utilities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: hu.gov.virusradar.Utilities.SmsReceiver */
public class SmsReceiver extends BroadcastReceiver {
    private static final String TAG = "###SMSReceiver";
    private IOnSmsReceivedListener listener;

    /* renamed from: hu.gov.virusradar.Utilities.SmsReceiver$IOnSmsReceivedListener */
    public interface IOnSmsReceivedListener {
        void onSmsReceived(String str);
    }

    public SmsReceiver(IOnSmsReceivedListener iOnSmsReceivedListener) {
        this.listener = iOnSmsReceivedListener;
    }

    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            try {
                Object[] objArr = (Object[]) extras.get("pdus");
                if (objArr != null) {
                    for (Object obj : objArr) {
                        SmsMessage createFromPdu = SmsMessage.createFromPdu((byte[]) obj);
                        createFromPdu.getDisplayOriginatingAddress();
                        String verificationCode = getVerificationCode(createFromPdu.getDisplayMessageBody());
                        if (!(this.listener == null || verificationCode == null)) {
                            this.listener.onSmsReceived(verificationCode);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getVerificationCode(String str) {
        Matcher matcher = Pattern.compile(".*(\\b([0-9]{4})\\b)").matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
