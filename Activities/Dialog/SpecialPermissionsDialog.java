package p008hu.gov.virusradar.Activities.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Utilities.PermissionUtil;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Activities.Dialog.SpecialPermissionsDialog */
public class SpecialPermissionsDialog extends DialogFragment {
    private TextView btnClose;
    private TextView btnSettings;
    private Context context;
    private View doubleTapFragment;
    private VideoView videoView;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.doubleTapFragment = layoutInflater.inflate(C1130R.layout.dialog_special_permissions, viewGroup, false);
        getDialog().getWindow().requestFeature(1);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.context = getContext();
        setupViews();
        return this.doubleTapFragment;
    }

    private void setupViews() {
        this.btnClose = (TextView) this.doubleTapFragment.findViewById(C1130R.C1133id.btnClose);
        this.btnSettings = (TextView) this.doubleTapFragment.findViewById(C1130R.C1133id.btnSettings);
        this.videoView = (VideoView) this.doubleTapFragment.findViewById(C1130R.C1133id.videoView);
        this.videoView.setVideoURI(PermissionUtil.getVideoInstructionsUri(this.context));
        this.videoView.setOnPreparedListener($$Lambda$SpecialPermissionsDialog$WE2wt1DH0RY0Tio1e42eNhyFJ4Y.INSTANCE);
        this.videoView.start();
        this.btnClose.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SpecialPermissionsDialog.this.lambda$setupViews$1$SpecialPermissionsDialog(view);
            }
        });
        this.btnSettings.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                SpecialPermissionsDialog.this.lambda$setupViews$2$SpecialPermissionsDialog(view);
            }
        });
    }

    public /* synthetic */ void lambda$setupViews$1$SpecialPermissionsDialog(View view) {
        dismiss();
    }

    public /* synthetic */ void lambda$setupViews$2$SpecialPermissionsDialog(View view) {
        PermissionUtil.startVendorSpecificBatteryPrompt(this.context);
        dismiss();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        onCreateDialog.getWindow().requestFeature(1);
        return onCreateDialog;
    }

    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(-1, -1);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    public void onResume() {
        super.onResume();
        this.videoView.start();
    }

    public static void show(AppCompatActivity appCompatActivity) {
        if (PermissionUtil.hasVendorSpecificRestrictions(appCompatActivity)) {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    SpecialPermissionsDialog.lambda$show$3(AppCompatActivity.this);
                }
            }, 300);
        }
    }

    static /* synthetic */ void lambda$show$3(AppCompatActivity appCompatActivity) {
        try {
            new SpecialPermissionsDialog().show(Util.getAnimatedFragmentTransaction(appCompatActivity.getSupportFragmentManager().beginTransaction()), "");
        } catch (Exception unused) {
        }
    }
}
