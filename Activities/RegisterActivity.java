package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import com.chaos.view.PinView;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import p005br.com.sapereaude.maskedEditText.MaskedEditText;
import p008hu.gov.virusradar.Activities.Dialog.ProgressBarDialog;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.C1127ErrorHandling;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.ConfigurationModel;
import p008hu.gov.virusradar.Modules.Input.ConfirmUserInput;
import p008hu.gov.virusradar.Modules.Input.RegisterDeviceTokenInput;
import p008hu.gov.virusradar.Modules.Input.RegisterUserInput;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.UserService;
import p008hu.gov.virusradar.Utilities.EncriptionUtil;
import p008hu.gov.virusradar.Utilities.FirebaseTokenNotificationUtil;
import p008hu.gov.virusradar.Utilities.PermissionUtil;
import p008hu.gov.virusradar.Utilities.SmsReceiver;
import p008hu.gov.virusradar.Utilities.SmsReceiver.IOnSmsReceivedListener;
import p008hu.gov.virusradar.Utilities.Util;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.RegisterActivity */
public class RegisterActivity extends AppCompatActivity implements IOnSmsReceivedListener {
    private static final int PHONE_NUMBER_LENGTH = 11;
    private static final int PIN_LENGTH = 4;
    @BindView(2131230800)
    TextView btnSendPhone;
    @BindView(2131230801)
    TextView btnSendSms;
    Context context;
    @BindView(2131230854)
    MaskedEditText etPhone;
    TextWatcher etPhoneTextWatch = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 11) {
                RegisterActivity registerActivity = RegisterActivity.this;
                registerActivity.setBtnEnabling(registerActivity.btnSendPhone, 1.0f, true);
                return;
            }
            RegisterActivity registerActivity2 = RegisterActivity.this;
            registerActivity2.setBtnEnabling(registerActivity2.btnSendPhone, 0.5f, false);
        }
    };
    @BindView(2131230855)
    EditText etPrefix;
    private boolean isSmsReceptionEnabled = false;
    @BindView(2131230910)
    View line;
    @BindView(2131230927)
    LinearLayout llSmsCode;
    private String phoneNumber;
    @BindView(2131230987)
    PinView pinView;
    TextWatcher pinViewTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() == 4) {
                RegisterActivity registerActivity = RegisterActivity.this;
                registerActivity.setBtnEnabling(registerActivity.btnSendSms, 1.0f, true);
                return;
            }
            RegisterActivity registerActivity2 = RegisterActivity.this;
            registerActivity2.setBtnEnabling(registerActivity2.btnSendSms, 0.5f, false);
        }
    };
    private SmsReceiver smsReceiver;
    @BindView(2131231088)
    TextView tvCode;
    @BindView(2131231089)
    TextView tvCodePhoneNumber;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_register);
        ButterKnife.bind((Activity) this);
        this.context = this;
        setEditTextListeners();
        this.pinView.addTextChangedListener(this.pinViewTextWatcher);
        this.etPhone.addTextChangedListener(this.etPhoneTextWatch);
        this.isSmsReceptionEnabled = getResources().getBoolean(C1130R.bool.sms_reception_enabled);
        if (this.isSmsReceptionEnabled && !PermissionUtil.isSmsPermissionGranted(this)) {
            PermissionUtil.requestSmsPermission(this);
        }
        this.smsReceiver = new SmsReceiver(this);
        this.etPrefix.setText(ConfigurationModel.getConfiguration().phonePrefix);
    }

    public void register() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "");
        StringBuilder sb = new StringBuilder();
        sb.append(this.etPrefix.getText().toString());
        sb.append(this.etPhone.getRawText());
        this.phoneNumber = EncriptionUtil.encryptMessage(sb.toString().trim());
        UserService.register(new RegisterUserInput(this.phoneNumber), new IResponseCallback<ResponseBody>() {
            public void onSuccess(ResponseBody responseBody) {
                ProgressBarDialog.dismissDialog();
                RegisterActivity.this.setViewAfterRegister();
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(RegisterActivity.this, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        RegisterActivity.this.register();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void setViewAfterRegister() {
        this.etPhone.setEnabled(false);
        this.etPrefix.setTextColor(ContextCompat.getColor(this.context, C1130R.C1131color.coloGreyLight));
        this.etPhone.setTextColor(ContextCompat.getColor(this.context, C1130R.C1131color.colorGreyText));
        this.line.setVisibility(8);
        this.btnSendPhone.setVisibility(8);
        StringBuilder sb = new StringBuilder();
        sb.append(this.etPrefix.getText().toString());
        sb.append(MaskedEditText.SPACE);
        sb.append(this.etPhone.getText());
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this.context, C1130R.C1131color.colorGreyText)), 0, 4, 33);
        this.tvCodePhoneNumber.setText(spannableString);
        this.llSmsCode.setVisibility(0);
        this.btnSendSms.setVisibility(0);
    }

    public void confirm() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "");
        UserService.confirm(new ConfirmUserInput(this.phoneNumber, this.pinView.getText().toString()), new IResponseCallback<ArrayList<String>>() {
            public void onSuccess(ArrayList<String> arrayList) {
                ProgressBarDialog.dismissDialog();
                RegisterActivity.this.pinView.setEnabled(false);
                AliasManager.getInstance().addAll(arrayList);
                RegisterActivity.this.btnSendSms.setVisibility(8);
                RegisterActivity.this.registerDevice();
                RegisterActivity.this.startPermissionActivity();
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(RegisterActivity.this, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        RegisterActivity.this.confirm();
                    }
                });
            }
        });
    }

    public void registerDevice() {
        UserService.registerDevice(new RegisterDeviceTokenInput(FirebaseTokenNotificationUtil.getFirebaseFromSharedPref(), Integer.valueOf(2)), new IResponseCallback<ResponseBody>() {
            public void onError(CustomError customError) {
            }

            public void onSuccess(ResponseBody responseBody) {
            }
        });
    }

    @OnClick({2131230800})
    @Optional
    public void sendPhoneListener() {
        Util.hideKeyboard(this.etPhone, getApplicationContext());
        register();
    }

    @OnClick({2131230801})
    @Optional
    public void sendSmsListener() {
        Util.hideKeyboard(this.etPhone, getApplicationContext());
        confirm();
    }

    @OnClick({2131230919})
    @Optional
    public void containerListener() {
        Util.hideKeyboard(this.etPhone, getApplicationContext());
    }

    private void setEditTextListeners() {
        this.etPhone.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                Util.hideKeyboard(RegisterActivity.this.etPhone, RegisterActivity.this.getApplicationContext());
                RegisterActivity.this.register();
                return true;
            }
        });
        this.pinView.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                Util.hideKeyboard(RegisterActivity.this.etPhone, RegisterActivity.this.getApplicationContext());
                RegisterActivity.this.confirm();
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void setBtnEnabling(View view, float f, boolean z) {
        view.setAlpha(f);
        view.setEnabled(z);
        view.setClickable(z);
    }

    /* access modifiers changed from: private */
    public void startPermissionActivity() {
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isSmsReceptionEnabled && PermissionUtil.isSmsPermissionGranted(this)) {
            try {
                registerReceiver(this.smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        try {
            unregisterReceiver(this.smsReceiver);
        } catch (Exception unused) {
        }
    }

    public void onSmsReceived(String str) {
        this.pinView.setText(str);
    }
}
