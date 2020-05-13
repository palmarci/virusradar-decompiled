package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import p008hu.gov.virusradar.Activities.Dialog.SpecialPermissionsDialog;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.IPermissionDialog;
import p008hu.gov.virusradar.Utilities.PermissionUtil;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.PermissionActivity */
public class PermissionActivity extends AppCompatActivity {
    private boolean batteryProcessing = false;
    Context context;
    private boolean isBatteryRequested = false;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_permission);
        this.context = this;
        ButterKnife.bind((Activity) this);
        SpecialPermissionsDialog.show(this);
    }

    @OnClick({2131230799})
    @Optional
    public void proceedListener() {
        if (!PermissionUtil.areAllPermissionsGranted((PermissionActivity) this.context) || !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            PermissionUtil.requestAllPermissions((PermissionActivity) this.context);
        } else if (PermissionUtil.requestBatteryOptimisationDisable((PermissionActivity) this.context, true)) {
            startMainActivity();
        } else {
            this.isBatteryRequested = true;
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (PermissionUtil.areAllPermissionsGranted(this)) {
            if (!BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                BluetoothAdapter.getDefaultAdapter().enable();
            }
            if (PermissionUtil.requestBatteryOptimisationDisable(this, true)) {
                startMainActivity();
            } else {
                this.isBatteryRequested = true;
            }
        } else {
            PermissionUtil.displayCustomPermissionDialog(this.context, new IPermissionDialog() {
                public final void onResult(boolean z) {
                    PermissionActivity.this.lambda$onRequestPermissionsResult$0$PermissionActivity(z);
                }
            }, getString(C1130R.string.grant_permissions));
        }
    }

    public /* synthetic */ void lambda$onRequestPermissionsResult$0$PermissionActivity(boolean z) {
        if (!z) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.batteryProcessing = this.isBatteryRequested;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.batteryProcessing) {
            return;
        }
        if (PermissionUtil.requestBatteryOptimisationDisable(this, false)) {
            startMainActivity();
        } else {
            finish();
        }
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }
}
