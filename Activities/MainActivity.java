package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.github.clans.fab.FloatingActionMenu.OnMenuToggleListener;
import java.util.ArrayList;
import java.util.Iterator;
import okhttp3.ResponseBody;
import p008hu.gov.virusradar.Activities.Dialog.ProgressBarDialog;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.C1127ErrorHandling;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Listeners.IFilterListener;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.ConfigurationModel;
import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import p008hu.gov.virusradar.Modules.LanguageModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel.IOnDeviceChangeListener;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceModel;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.LanguagesService;
import p008hu.gov.virusradar.Services.UserService;
import p008hu.gov.virusradar.Utilities.AppLanguagesUtil;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p008hu.gov.virusradar.Utilities.BackgroundServices.MonitoringService;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper;
import p008hu.gov.virusradar.Utilities.InternetConnectivityUtil;
import p008hu.gov.virusradar.Utilities.SharedPrefsUtil;
import p008hu.gov.virusradar.Utilities.Util;
import p008hu.gov.virusradar.Utilities.VirusTraceApp;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.MainActivity */
public class MainActivity extends AppCompatActivity implements IOnDeviceChangeListener {
    private static final int TRIGGER_DELAY = 5000;
    private static String keyServiceState = "serviceState";
    /* access modifiers changed from: private */
    public Context context;
    @BindView(2131230861)
    FloatingActionMenu famLocalization;
    private BroadcastReceiver gpsReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches("android.location.PROVIDERS_CHANGED")) {
                MainActivity.this.checkLocationStatus();
            }
        }
    };
    /* access modifiers changed from: private */
    public Boolean isOn = Boolean.valueOf(true);
    @BindView(2131230892)
    ImageView ivBluetooth;
    @BindView(2131230896)
    ImageView ivLocaleArrow;
    @BindView(2131230897)
    ImageView ivLocation;
    @BindView(2131230901)
    ImageView ivOnOff;
    @BindView(2131230920)
    LinearLayout llLanguageSelector;
    private String logDataUncoverBias = "";
    private String logDataUncoverKey = "aaa";
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                MainActivity.this.checkBluetoothStatus();
            }
        }
    };
    OnMenuToggleListener onFabMenuToggleListener = new OnMenuToggleListener() {
        public void onMenuToggle(boolean z) {
            if (z) {
                MainActivity.this.ivLocaleArrow.animate().rotation(270.0f).setDuration(150).setInterpolator(new LinearInterpolator()).start();
            } else {
                MainActivity.this.ivLocaleArrow.animate().rotation(90.0f).setDuration(150).setInterpolator(new LinearInterpolator()).start();
            }
        }
    };
    OnClickListener onLocalMenuClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (MainActivity.this.famLocalization.isOpened()) {
                MainActivity.this.famLocalization.close(true);
            } else {
                MainActivity.this.famLocalization.open(true);
            }
        }
    };
    @BindView(2131230983)
    ProgressBar pbTrigger;
    @BindView(2131231075)
    Toolbar toolbar;
    @BindView(2131231084)
    TextView tvActiveUser;
    @BindView(2131231086)
    TextView tvAppActive;
    @BindView(2131231087)
    TextView tvButtonMessage;
    @BindView(2131231091)
    TextView tvCurrentLocale;
    @BindView(2131231098)
    TextView tvLocationStatus;
    @BindView(2131231099)
    TextView tvMessage;
    @BindView(2131231106)
    TextView tvOnOff;
    @BindView(2131231109)
    TextView tvResult;
    @BindView(2131231111)
    TextView tvSend;
    @BindView(2131231112)
    TextView tvSettings;
    @BindView(2131231114)
    TextView tvStatus;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_main);
        ButterKnife.bind((Activity) this);
        this.context = this;
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setTitle((CharSequence) "");
        this.isOn = Boolean.valueOf(SharedPrefsUtil.getBoolean(keyServiceState, this.isOn.booleanValue()) && AdvertisingHelper.isBluetoothOn());
        checkBluetoothStatus();
        checkLocationStatus();
        setupService(false);
        this.famLocalization.setOnMenuToggleListener(this.onFabMenuToggleListener);
        this.llLanguageSelector.setOnClickListener(this.onLocalMenuClickListener);
        registerReceiver(this.mReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
        registerReceiver(this.gpsReceiver, new IntentFilter("android.location.PROVIDERS_CHANGED"));
        this.ivOnOff.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                MainActivity.this.lambda$onCreate$0$MainActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$0$MainActivity(View view) {
        triggerMonitoringService();
    }

    /* access modifiers changed from: private */
    public void checkBluetoothStatus() {
        if (AdvertisingHelper.isBluetoothOn()) {
            this.ivBluetooth.setImageResource(C1130R.C1132drawable.bluetooth_on);
            this.tvStatus.setText(C1130R.string.f93on);
        } else {
            this.ivBluetooth.setImageResource(C1130R.C1132drawable.bluetooth_off);
            this.tvStatus.setText(C1130R.string.off);
        }
        showMessage();
    }

    /* access modifiers changed from: private */
    public void checkLocationStatus() {
        if (Secure.getInt(this.context.getContentResolver(), "location_mode", 0) != 0) {
            this.ivLocation.setImageResource(C1130R.C1132drawable.location_on);
            this.tvLocationStatus.setText(C1130R.string.location_on);
        } else {
            this.ivLocation.setImageResource(C1130R.C1132drawable.location_off);
            this.tvLocationStatus.setText(C1130R.string.location_off);
        }
        showMessage();
    }

    @OnClick({2131231111})
    public void onSendClick() {
        verifySubmit();
    }

    public void verifySubmit() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "tag");
        UserService.getConfiguration(new IResponseCallback<ConfigurationModel>() {
            public void onSuccess(ConfigurationModel configurationModel) {
                ProgressBarDialog.dismissDialog();
                configurationModel.save();
                if (configurationModel.verifySubmit.booleanValue()) {
                    MainActivity.this.startPinDialog();
                } else {
                    MainActivity.this.startAgreeDialog();
                }
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                if (ConfigurationModel.getConfiguration().verifySubmit.booleanValue()) {
                    MainActivity.this.startPinDialog();
                } else {
                    MainActivity.this.startAgreeDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void startPinDialog() {
        Util.startPinDialog(this.context, getString(C1130R.string.confirm_send_data), new IFilterListener() {
            public void getNewValue(Object obj) {
                MainActivity.this.uploadRecords((String) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public void startAgreeDialog() {
        Util.startAgreeDialog(this.context, getString(C1130R.string.confirm_send_data), new INoNetworkListener() {
            public void onClickRefresh() {
                MainActivity.this.startConfirmDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void startConfirmDialog() {
        Util.startConfirmDialog(this.context, getString(C1130R.string.message_after_send), new INoNetworkListener() {
            public void onClickRefresh() {
                MainActivity.this.uploadRecords("");
            }
        });
    }

    public void uploadRecords(final String str) {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "tag");
        UserService.uploadEncounters(DeviceListModel.getInstance().mapToUserEncounter(str), new IResponseCallback<ResponseBody>() {
            public void onSuccess(ResponseBody responseBody) {
                ProgressBarDialog.dismissDialog();
                MainActivity.this.startDoneDialog();
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(MainActivity.this, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        MainActivity.this.uploadRecords(str);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void startDoneDialog() {
        Context context2 = this.context;
        Util.startDoneDialog(context2, context2.getString(C1130R.string.data_sent), null);
    }

    @OnClick({2131230892})
    public void onBluetooth() {
        easterEgg('b');
    }

    @OnClick({2131230915})
    public void onActiveUsers() {
        easterEgg('a');
    }

    @OnClick({2131230897})
    public void onLocation() {
        easterEgg('l');
    }

    private void easterEgg(char c) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.logDataUncoverBias);
        sb.append(c);
        this.logDataUncoverBias = sb.toString();
        if (VirusTraceApp.globalEnvironment.debugging && this.logDataUncoverBias.length() >= this.logDataUncoverKey.length()) {
            String str = this.logDataUncoverBias;
            this.logDataUncoverBias = str.substring(str.length() - this.logDataUncoverKey.length());
            if (this.logDataUncoverBias.equals(this.logDataUncoverKey)) {
                this.tvResult.setVisibility(0);
            }
        }
    }

    @OnClick({2131231109})
    public void onResultClick() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
    }

    @OnClick({2131231112})
    public void onSettingsClick() {
        if (AdvertisingHelper.isBluetoothOn()) {
            Intent intent = new Intent();
            intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setAction("android.settings.BLUETOOTH_SETTINGS");
        startActivity(intent2);
    }

    @OnClick({2131230917})
    public void bluetoothClick() {
        Intent intent = new Intent();
        intent.setAction("android.settings.BLUETOOTH_SETTINGS");
        startActivity(intent);
    }

    @OnClick({2131230921})
    public void locationClick() {
        Intent intent = new Intent();
        intent.setAction("android.settings.LOCATION_SOURCE_SETTINGS");
        startActivity(intent);
    }

    public void setupFamLocalization() {
        if (AppLanguagesUtil.appLanguageModels != null && AppLanguagesUtil.appLanguageModels.size() > 0) {
            this.famLocalization.setVisibility(0);
            this.famLocalization.setIconAnimated(false);
            this.famLocalization.setClosedOnTouchOutside(true);
            LanguageModel defaultLocaleFromSharedPreferences = AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this.context);
            this.tvCurrentLocale.setText(defaultLocaleFromSharedPreferences.shortTitle);
            this.llLanguageSelector.setVisibility(0);
            Iterator it = AppLanguagesUtil.appLanguageModels.iterator();
            while (it.hasNext()) {
                LanguageModel languageModel = (LanguageModel) it.next();
                if (!defaultLocaleFromSharedPreferences.shortTitle.equalsIgnoreCase(languageModel.shortTitle)) {
                    this.famLocalization.addMenuButton(getLocalizationFab(languageModel));
                }
            }
        }
    }

    private FloatingActionButton getLocalizationFab(final LanguageModel languageModel) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(this.context);
        floatingActionButton.setButtonSize(1);
        floatingActionButton.setColorNormal(ContextCompat.getColor(this.context, C1130R.C1131color.colorWhite));
        floatingActionButton.setColorPressed(ContextCompat.getColor(this.context, C1130R.C1131color.colorPrimary));
        floatingActionButton.setImageBitmap(Util.textAsBitmap(languageModel.shortTitle, 12, ContextCompat.getColor(this.context, C1130R.C1131color.colorPrimary)));
        floatingActionButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.changeUserLanguage(languageModel);
            }
        });
        return floatingActionButton;
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
                Util.setLocale(MainActivity.this.context, languageModel);
                MainActivity.this.startMainActivity();
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(MainActivity.this.context, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        MainActivity.this.changeUserLanguage(languageModel);
                    }
                });
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mReceiver);
        unregisterReceiver(this.gpsReceiver);
    }

    public void recreate() {
        startActivity(getIntent());
        finish();
    }

    @OnClick({2131230926})
    public void shareClick() {
        try {
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            intent.putExtra("android.intent.extra.SUBJECT", this.context.getString(C1130R.string.virus_radar));
            StringBuilder sb = new StringBuilder();
            sb.append(this.context.getString(C1130R.string.share_message));
            sb.append("\n\n");
            sb.append(VirusTraceApp.globalEnvironment.globalURL);
            sb.append(this.context.getString(C1130R.string.share_key));
            intent.putExtra("android.intent.extra.TEXT", sb.toString());
            startActivity(Intent.createChooser(intent, this.context.getString(C1130R.string.choose)));
        } catch (Exception unused) {
            String str = "";
            Log.d(str, str);
        }
    }

    private void showNotification() {
        Intent intent = new Intent(this, NotificationsActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
    }

    private void showAbout() {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
    }

    private void showPrivacy() {
        if (InternetConnectivityUtil.isNetworkAvailable(this)) {
            Intent intent = new Intent(this, PrivacyPolicyActivity.class);
            intent.setFlags(335544320);
            startActivity(intent);
            return;
        }
        Toast.makeText(this, getString(C1130R.string.no_internet_connection), 0).show();
    }

    private void deletePermission() {
        Util.startConfirmDeleteDialog(this.context, getString(C1130R.string.delete_message), new INoNetworkListener() {
            public void onClickRefresh() {
                MainActivity.this.deleteProfile();
            }
        });
    }

    /* access modifiers changed from: private */
    public void deleteProfile() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "tag");
        UserService.deleteProfile(new IResponseCallback() {
            public void onSuccess(Object obj) {
                ProgressBarDialog.dismissDialog();
                MonitoringService.stopService();
                AdvertisingHelper.clearModels();
                AliasManager.getInstance().deleteAll();
                AuthenticationUtil.clearAuthentication(MainActivity.this.context);
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(MainActivity.this.context, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        MainActivity.this.deleteProfile();
                    }
                });
            }
        });
    }

    private void showMessage() {
        int i = Secure.getInt(this.context.getContentResolver(), "location_mode", 0);
        if (!AdvertisingHelper.isBluetoothOn() || i == 0) {
            this.tvMessage.setVisibility(0);
            this.tvSettings.setVisibility(0);
            this.tvSend.setVisibility(8);
            this.isOn = Boolean.valueOf(false);
        } else {
            this.tvMessage.setVisibility(4);
            this.tvSettings.setVisibility(8);
            this.tvSend.setVisibility(0);
        }
        setupService(false);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1130R.C1135menu.menu, menu);
        if (menu instanceof MenuBuilder) {
            ((MenuBuilder) menu).setOptionalIconsVisible(true);
        }
        MenuItem item = menu.getItem(3);
        String string = getString(C1130R.string.delete);
        SpannableString spannableString = new SpannableString(string);
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.context, C1130R.C1131color.delete_menu)), 0, string.length(), 0);
        item.setTitle(spannableString);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1130R.C1133id.about /*2131230730*/:
                showAbout();
                return true;
            case C1130R.C1133id.delete /*2131230836*/:
                deletePermission();
                return true;
            case C1130R.C1133id.notifications /*2131230972*/:
                showNotification();
                return true;
            case C1130R.C1133id.privacy /*2131230989*/:
                showPrivacy();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    /* access modifiers changed from: private */
    public void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void triggerMonitoringService() {
        if (AdvertisingHelper.isBluetoothOn()) {
            this.isOn = Boolean.valueOf(!this.isOn.booleanValue());
            SharedPrefsUtil.saveBoolean(keyServiceState, this.isOn.booleanValue());
            setupService(true);
        }
    }

    private void setupService(boolean z) {
        this.ivOnOff.setEnabled(false);
        this.pbTrigger.setVisibility(0);
        if (this.isOn.booleanValue()) {
            MonitoringService.startService(this.context);
        } else {
            MonitoringService.stopService();
        }
        new Handler().postDelayed(new Runnable() {
            public final void run() {
                MainActivity.this.lambda$setupService$1$MainActivity();
            }
        }, z ? 5000 : 0);
    }

    public /* synthetic */ void lambda$setupService$1$MainActivity() {
        this.pbTrigger.setVisibility(8);
        this.tvButtonMessage.setText(this.isOn.booleanValue() ? C1130R.string.press_button : C1130R.string.press_button_on);
        this.ivOnOff.setImageResource(this.isOn.booleanValue() ? C1130R.C1132drawable.f87on : C1130R.C1132drawable.off);
        this.tvOnOff.setText(this.isOn.booleanValue() ? C1130R.string.app_on : C1130R.string.app_off);
        this.tvAppActive.setText(this.isOn.booleanValue() ? C1130R.string.is_active : C1130R.string.is_not_active);
        this.tvActiveUser.setText("0");
        this.ivOnOff.setEnabled(true);
        if (this.isOn.booleanValue()) {
            DeviceListModel.getInstance().setOnDeviceChangeListener(this);
        }
    }

    public void onActiveDevicesChange(final ArrayList<DeviceModel> arrayList) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (MainActivity.this.isOn.booleanValue()) {
                    MainActivity.this.tvActiveUser.setText(String.valueOf(arrayList.size()));
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Util.setLocale(this, AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this));
    }
}
