package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.Iterator;
import p008hu.gov.virusradar.Activities.Dialog.ProgressBarDialog;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.C1127ErrorHandling;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import p008hu.gov.virusradar.Modules.LanguageModel;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.LanguagesService;
import p008hu.gov.virusradar.Utilities.AppLanguagesUtil;
import p008hu.gov.virusradar.Utilities.Util;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.ConsentActivity */
public class ConsentActivity extends AppCompatActivity {
    private static final int fabTextSizeDp = 14;
    Context context;
    @BindView(2131230861)
    FloatingActionMenu famLocalization;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_consent);
        this.context = this;
        ButterKnife.bind((Activity) this);
    }

    @OnClick({2131230797})
    @Optional
    public void agreeListener() {
        startBaseActivity();
    }

    private void startBaseActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void setupFabLocalization() {
        this.famLocalization.setIconAnimated(false);
        this.famLocalization.setClosedOnTouchOutside(true);
        LanguageModel defaultLocaleFromSharedPreferences = AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this.context);
        this.famLocalization.getMenuIconView().setImageBitmap(Util.textAsBitmap(defaultLocaleFromSharedPreferences.shortTitle, 14, -1));
        Iterator it = AppLanguagesUtil.appLanguageModels.iterator();
        while (it.hasNext()) {
            LanguageModel languageModel = (LanguageModel) it.next();
            if (!defaultLocaleFromSharedPreferences.shortTitle.equalsIgnoreCase(languageModel.shortTitle)) {
                this.famLocalization.addMenuButton(getLocalizationFab(languageModel));
            }
        }
    }

    private FloatingActionButton getLocalizationFab(LanguageModel languageModel) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(this.context);
        floatingActionButton.setButtonSize(1);
        floatingActionButton.setColorNormal(-1);
        floatingActionButton.setColorPressed(C1130R.C1131color.fab_pressed_color);
        floatingActionButton.setImageBitmap(Util.textAsBitmap(languageModel.shortTitle, 14, ContextCompat.getColor(this.context, C1130R.C1131color.colorPrimary)));
        floatingActionButton.setOnClickListener(getOnFabOptionClickListener(languageModel));
        return floatingActionButton;
    }

    private OnClickListener getOnFabOptionClickListener(final LanguageModel languageModel) {
        return new OnClickListener() {
            public void onClick(View view) {
                ConsentActivity.this.changeUserLanguage(languageModel);
            }
        };
    }

    /* access modifiers changed from: private */
    public void changeUserLanguage(final LanguageModel languageModel) {
        ChangeLanguageInput changeLanguageInput = new ChangeLanguageInput();
        changeLanguageInput.Language = languageModel.culture;
        this.famLocalization.close(true);
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "tag");
        LanguagesService.changeLanguage(changeLanguageInput, new IResponseCallback() {
            public void onSuccess(Object obj) {
                ProgressBarDialog.dismissDialog();
                AppLanguagesUtil.setDefaultFromUser(languageModel);
                Util.setLocale(ConsentActivity.this.context, languageModel);
                ConsentActivity.this.recreate();
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(ConsentActivity.this.context, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        ConsentActivity.this.changeUserLanguage(languageModel);
                    }
                });
            }
        });
    }

    public void recreate() {
        startActivity(getIntent());
        finish();
    }
}
