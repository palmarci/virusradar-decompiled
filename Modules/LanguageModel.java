package p008hu.gov.virusradar.Modules;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Locale;

/* renamed from: hu.gov.virusradar.Modules.LanguageModel */
public class LanguageModel implements Serializable {
    private static final String keyId = "Id";
    private static final String keyShortTitle = "ShortTitle";
    private static final String keyTitle = "Title";
    public String culture;
    public int flagImage;
    @SerializedName("Id")

    /* renamed from: id */
    public int f83id;
    public boolean isDefault;
    @SerializedName("ShortTitle")
    public String shortTitle;
    @SerializedName("Title")
    public String title;

    public Locale getLocale() {
        return new Locale(this.shortTitle.toLowerCase(), this.shortTitle);
    }
}
