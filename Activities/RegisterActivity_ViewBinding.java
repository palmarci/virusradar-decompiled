package p008hu.gov.virusradar.Activities;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.chaos.view.PinView;
import p005br.com.sapereaude.maskedEditText.MaskedEditText;
import p008hu.gov.virusradar.C1130R;

/* renamed from: hu.gov.virusradar.Activities.RegisterActivity_ViewBinding */
public class RegisterActivity_ViewBinding implements Unbinder {
    private RegisterActivity target;
    private View view7f080050;
    private View view7f080051;
    private View view7f0800c7;

    public RegisterActivity_ViewBinding(RegisterActivity registerActivity) {
        this(registerActivity, registerActivity.getWindow().getDecorView());
    }

    public RegisterActivity_ViewBinding(final RegisterActivity registerActivity, View view) {
        this.target = registerActivity;
        registerActivity.etPrefix = (EditText) Utils.findOptionalViewAsType(view, C1130R.C1133id.etPrefix, "field 'etPrefix'", EditText.class);
        registerActivity.etPhone = (MaskedEditText) Utils.findOptionalViewAsType(view, C1130R.C1133id.etPhone, "field 'etPhone'", MaskedEditText.class);
        View findViewById = view.findViewById(C1130R.C1133id.btnSendPhone);
        registerActivity.btnSendPhone = (TextView) Utils.castView(findViewById, C1130R.C1133id.btnSendPhone, "field 'btnSendPhone'", TextView.class);
        if (findViewById != null) {
            this.view7f080050 = findViewById;
            findViewById.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    registerActivity.sendPhoneListener();
                }
            });
        }
        registerActivity.llSmsCode = (LinearLayout) Utils.findOptionalViewAsType(view, C1130R.C1133id.llSmsCode, "field 'llSmsCode'", LinearLayout.class);
        registerActivity.pinView = (PinView) Utils.findOptionalViewAsType(view, C1130R.C1133id.pinView, "field 'pinView'", PinView.class);
        View findViewById2 = view.findViewById(C1130R.C1133id.btnSendSms);
        registerActivity.btnSendSms = (TextView) Utils.castView(findViewById2, C1130R.C1133id.btnSendSms, "field 'btnSendSms'", TextView.class);
        if (findViewById2 != null) {
            this.view7f080051 = findViewById2;
            findViewById2.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    registerActivity.sendSmsListener();
                }
            });
        }
        registerActivity.tvCode = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvCode, "field 'tvCode'", TextView.class);
        registerActivity.line = view.findViewById(C1130R.C1133id.line);
        registerActivity.tvCodePhoneNumber = (TextView) Utils.findOptionalViewAsType(view, C1130R.C1133id.tvCodePhoneNumber, "field 'tvCodePhoneNumber'", TextView.class);
        View findViewById3 = view.findViewById(C1130R.C1133id.llContainerMain);
        if (findViewById3 != null) {
            this.view7f0800c7 = findViewById3;
            findViewById3.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    registerActivity.containerListener();
                }
            });
        }
    }

    public void unbind() {
        RegisterActivity registerActivity = this.target;
        if (registerActivity != null) {
            this.target = null;
            registerActivity.etPrefix = null;
            registerActivity.etPhone = null;
            registerActivity.btnSendPhone = null;
            registerActivity.llSmsCode = null;
            registerActivity.pinView = null;
            registerActivity.btnSendSms = null;
            registerActivity.tvCode = null;
            registerActivity.line = null;
            registerActivity.tvCodePhoneNumber = null;
            View view = this.view7f080050;
            if (view != null) {
                view.setOnClickListener(null);
                this.view7f080050 = null;
            }
            View view2 = this.view7f080051;
            if (view2 != null) {
                view2.setOnClickListener(null);
                this.view7f080051 = null;
            }
            View view3 = this.view7f0800c7;
            if (view3 != null) {
                view3.setOnClickListener(null);
                this.view7f0800c7 = null;
                return;
            }
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
