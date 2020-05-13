package p008hu.gov.virusradar.ErrorHandling;

import android.content.Context;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import p008hu.gov.virusradar.Activities.Dialog.NoNetworkView;
import p008hu.gov.virusradar.Activities.Dialog.NoServiceView;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p008hu.gov.virusradar.Utilities.Util;
import p008hu.gov.virusradar.Utilities.ValidateTextView;

/* renamed from: hu.gov.virusradar.ErrorHandling.ErrorHandling */
public class C1127ErrorHandling {
    private static final String empty_string = "";

    public static void handlingError(Context context, CustomError customError, INoNetworkListener iNoNetworkListener) {
        handlingError(context, customError, null, iNoNetworkListener);
    }

    public static void handlingError(Context context, CustomError customError, ArrayList<ValidateTextView> arrayList, INoNetworkListener iNoNetworkListener) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ValidateTextView validateTextView = (ValidateTextView) it.next();
                validateTextView.validate((ArrayList) customError.errorHashmap.get(validateTextView.getTag()));
                customError.errorHashmap.remove(validateTextView.getTag());
            }
        }
        int i = customError.errorCode;
        if (i == 400) {
            createMessage(context, customError);
        } else if (i == 401) {
            AuthenticationUtil.clearAuthentication(context);
        } else if (i == 500) {
            new NoServiceView(context, iNoNetworkListener).show();
        } else if (i != 666) {
            new NoServiceView(context, iNoNetworkListener).show();
        } else {
            new NoNetworkView(context, iNoNetworkListener).show();
        }
    }

    private static void createMessage(Context context, CustomError customError) {
        if (customError.errorHashmap != null && customError.errorHashmap.size() != 0) {
            String str = "";
            for (Entry value : customError.errorHashmap.entrySet()) {
                Iterator it = ((ArrayList) value.getValue()).iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(str2);
                    sb.append("\n");
                    str = sb.toString();
                }
            }
            if (customError.message != null) {
                Util.startErrorDialog(customError.message, context);
            } else {
                Util.startErrorDialog(str, context);
            }
        }
    }
}
