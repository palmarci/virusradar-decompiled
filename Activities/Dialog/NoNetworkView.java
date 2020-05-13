package p008hu.gov.virusradar.Activities.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;

/* renamed from: hu.gov.virusradar.Activities.Dialog.NoNetworkView */
public class NoNetworkView extends Dialog {
    private Context context;
    private INoNetworkListener listener;

    public NoNetworkView(Context context2, INoNetworkListener iNoNetworkListener) {
        super(context2);
        requestWindowFeature(1);
        this.context = context2;
        this.listener = iNoNetworkListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1130R.layout.view_no_network);
        ButterKnife.bind((Dialog) this);
        getWindow().setLayout(-1, -1);
        getWindow().setBackgroundDrawable(new ColorDrawable(-1));
    }

    @OnClick({2131231108})
    @Optional
    public void refreshClickListener() {
        INoNetworkListener iNoNetworkListener = this.listener;
        if (iNoNetworkListener != null) {
            iNoNetworkListener.onClickRefresh();
        }
        dismiss();
    }
}
