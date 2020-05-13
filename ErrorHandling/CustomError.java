package p008hu.gov.virusradar.ErrorHandling;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: hu.gov.virusradar.ErrorHandling.CustomError */
public class CustomError extends Exception {
    public int errorCode;
    public HashMap<String, ArrayList<String>> errorHashmap;
    public String message;

    public CustomError(final String str, int i) {
        this.errorCode = i;
        if (isValidJSON(str)) {
            ErrorModel errorModel = (ErrorModel) new Gson().fromJson(str, ErrorModel.class);
            if (errorModel.Errors != null) {
                this.errorHashmap = errorModel.ToHashMap();
                return;
            } else if (errorModel.Message != null) {
                this.message = errorModel.Message;
            }
        }
        this.errorHashmap = new HashMap<String, ArrayList<String>>() {
            {
                put("", new ArrayList<String>() {
                    {
                        add(str);
                    }
                });
            }
        };
    }

    public CustomError(String str) {
        this.errorCode = 9999;
        new ArrayList().add(str);
    }

    public static boolean isValidJSON(String str) {
        try {
            new JSONObject(str);
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }
}
