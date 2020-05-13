package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.javiersantos.appupdater.AppUpdaterUtils;
import com.github.javiersantos.appupdater.AppUpdaterUtils.UpdateListener;
import com.github.javiersantos.appupdater.enums.AppUpdaterError;
import com.github.javiersantos.appupdater.enums.UpdateFrom;
import com.github.javiersantos.appupdater.objects.Update;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Modules.ConfigurationModel;
import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.LanguagesService;
import p008hu.gov.virusradar.Services.UserService;
import p008hu.gov.virusradar.Utilities.AppLanguagesUtil;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p008hu.gov.virusradar.Utilities.PermissionUtil;
import p008hu.gov.virusradar.Utilities.Util;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.SplashActivity */
public class SplashActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static int SPLASH_TIME_OUT = 3000;
    AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(this).setUpdateFrom(UpdateFrom.GOOGLE_PLAY).withListener((UpdateListener) new UpdateListener() {
        public void onSuccess(Update update, Boolean bool) {
            if (Util.checkForUpdate(SplashActivity.this.context, update.getLatestVersion())) {
                SplashActivity.this.startUpdateActivity();
            } else {
                SplashActivity.this.getConfiguration();
            }
        }

        public void onFailed(AppUpdaterError appUpdaterError) {
            SplashActivity.this.getConfiguration();
        }
    });
    Context context;
    @BindView(2131230903)
    ImageView ivSplash;
    @BindView(2131230928)
    FrameLayout llSplash;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_splash);
        this.context = this;
        ButterKnife.bind((Activity) this);
        Util.setLocale(this, AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this));
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Util.setLocale(this, AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this));
        this.appUpdaterUtils.start();
    }

    /* access modifiers changed from: private */
    public void startApp() {
        if (AuthenticationUtil.isFirstTimeLaunch()) {
            startWelcomeActivity();
        } else if (!AuthenticationUtil.isUserLogged()) {
            startConsentActivity();
        } else if (!PermissionUtil.areAllPermissionsGranted((SplashActivity) this.context) || !PermissionUtil.requestBatteryOptimisationDisable((SplashActivity) this.context, false)) {
            startPermissionActivity();
        } else {
            startMainActivity();
        }
    }

    private void startWelcomeActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void startPermissionActivity() {
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void startConsentActivity() {
        Intent intent = new Intent(this, ConsentActivity.class);
        intent.addFlags(335544320);
        startActivity(intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void startUpdateActivity() {
        Intent intent = new Intent(this.context, UpdateActivity.class);
        intent.addFlags(268468224);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void getConfiguration() {
        UserService.getConfiguration(new IResponseCallback<ConfigurationModel>() {
            public void onSuccess(ConfigurationModel configurationModel) {
                configurationModel.save();
                SplashActivity.this.changeUserLanguage();
            }

            public void onError(CustomError customError) {
                SplashActivity.this.changeUserLanguage();
            }
        });
    }

    /* access modifiers changed from: private */
    public void changeUserLanguage() {
        ChangeLanguageInput changeLanguageInput = new ChangeLanguageInput();
        changeLanguageInput.Language = AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this).culture;
        LanguagesService.changeLanguage(changeLanguageInput, new IResponseCallback() {
            public /* synthetic */ void lambda$onSuccess$0$SplashActivity$3() {
                SplashActivity.this.startApp();
            }

            public void onSuccess(Object obj) {
                new Handler().postDelayed(new Runnable() {
                    public final void run() {
                        C11153.this.lambda$onSuccess$0$SplashActivity$3();
                    }
                }, (long) SplashActivity.SPLASH_TIME_OUT);
            }

            public /* synthetic */ void lambda$onError$1$SplashActivity$3() {
                SplashActivity.this.startApp();
            }

            public void onError(CustomError customError) {
                new Handler().postDelayed(new Runnable() {
                    public final void run() {
                        C11153.this.lambda$onError$1$SplashActivity$3();
                    }
                }, (long) SplashActivity.SPLASH_TIME_OUT);
            }
        });
    }
}
