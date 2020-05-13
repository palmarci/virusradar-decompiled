package p008hu.gov.virusradar.Activities.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import p008hu.gov.virusradar.Activities.SplashActivity;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;

/* renamed from: hu.gov.virusradar.Activities.Dialog.NoServiceView */
public class NoServiceView extends Dialog {
    private Context context;
    private INoNetworkListener listener;

    public NoServiceView(Context context2, INoNetworkListener iNoNetworkListener) {
        super(context2);
        this.listener = iNoNetworkListener;
        requestWindowFeature(1);
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1130R.layout.view_no_service);
        ButterKnife.bind((Dialog) this);
        getWindow().setLayout(-1, -1);
        getWindow().setBackgroundDrawable(new ColorDrawable(-1));
    }

    @OnClick({2131231108})
    @Optional
    public void refreshClickListener() {
        ActivityCompat.finishAffinity((Activity) this.context);
        Intent intent = new Intent(getContext(), SplashActivity.class);
        intent.addFlags(67108864);
        this.context.startActivity(intent);
    }
}
