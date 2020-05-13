package p008hu.gov.virusradar.ErrorHandling;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: hu.gov.virusradar.ErrorHandling.ErrorCompactModel */
public class ErrorCompactModel implements Serializable {
    private static final String keyErrors = "Errors";
    private static final String keyKey = "Key";
    @SerializedName("Errors")
    public ArrayList<String> Errors;
    @SerializedName("Key")
    public String Key;
}
