package p008hu.gov.virusradar.Utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.WebView;
import com.crashlytics.android.Crashlytics;
import p008hu.gov.virusradar.C1130R;
import p009io.fabric.sdk.android.Fabric;
import p009io.github.inflationx.calligraphy3.CalligraphyConfig.Builder;
import p009io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import p009io.github.inflationx.viewpump.ViewPump;

/* renamed from: hu.gov.virusradar.Utilities.VirusTraceApp */
public class VirusTraceApp extends Application {
    public static final String DEFAULT_FONT_PATH = "fonts/inter_regular.ttf";
    public static GlobalEnvironment globalEnvironment;
    private static Context mInstance;
    public static SharedPreferences sharedPref;

    public void onCreate() {
        super.onCreate();
        new WebView(this).destroy();
        mInstance = this;
        sharedPref = getSharedPreferences(mInstance.getResources().getString(C1130R.string.VirusTracePrefFile), 0);
        setupGlobalEnvironment();
        setupCalligraphy();
        Fabric.with(this, new Crashlytics());
        AppLanguagesUtil.setAppLanguageModels(mInstance);
    }

    private static void setupGlobalEnvironment() {
        globalEnvironment = new GlobalEnvironment();
        if (mInstance.getResources().getBoolean(C1130R.bool.is_production)) {
            globalEnvironment.globalURL = mInstance.getString(C1130R.string.global_url);
            globalEnvironment.debugging = false;
            return;
        }
        globalEnvironment.globalURL = mInstance.getString(C1130R.string.global_url_test);
        globalEnvironment.debugging = true;
    }

    private void setupCalligraphy() {
        ViewPump.init(ViewPump.builder().addInterceptor(new CalligraphyInterceptor(new Builder().setDefaultFontPath(DEFAULT_FONT_PATH).setFontAttrId(C1130R.attr.fontPath).build())).build());
    }

    public static synchronized Context getInstance() {
        Context context;
        synchronized (VirusTraceApp.class) {
            context = mInstance;
        }
        return context;
    }
}
