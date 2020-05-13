package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import p008hu.gov.virusradar.Adapters.DevicesAdapter.C1123DevicesAdapter;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceModel;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.ResultActivity */
public class ResultActivity extends AppCompatActivity {
    private Context context;
    private ArrayList<DeviceModel> devices;
    private C1123DevicesAdapter devicesAdapter;
    private GridLayoutManager gridLayoutManager;
    @BindView(2131231001)
    RecyclerView rvDevices;
    @BindView(2131231101)
    TextView tvNoItems;
    @BindView(2131231116)
    TextView tvTitle;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_result);
        ButterKnife.bind((Activity) this);
        this.context = this;
        this.devices = DeviceListModel.getInstance();
        this.gridLayoutManager = new GridLayoutManager(this.context, 1);
        this.rvDevices.setLayoutManager(this.gridLayoutManager);
        this.devicesAdapter = new C1123DevicesAdapter(this.context);
        this.rvDevices.setAdapter(this.devicesAdapter);
        this.tvTitle.setText(AuthenticationUtil.getAlias());
        ArrayList<DeviceModel> arrayList = this.devices;
        if (arrayList == null || arrayList.size() == 0) {
            this.rvDevices.setVisibility(8);
            this.tvNoItems.setVisibility(0);
            return;
        }
        this.tvNoItems.setVisibility(8);
        this.rvDevices.setVisibility(0);
        new Thread(new Runnable() {
            public final void run() {
                ResultActivity.this.lambda$onCreate$1$ResultActivity();
            }
        }).start();
    }

    public /* synthetic */ void lambda$null$0$ResultActivity() {
        this.devicesAdapter.addDevices(this.devices);
    }

    public /* synthetic */ void lambda$onCreate$1$ResultActivity() {
        while (true) {
            runOnUiThread(new Runnable() {
                public final void run() {
                    ResultActivity.this.lambda$null$0$ResultActivity();
                }
            });
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick({2131230890})
    public void backClick() {
        onBackPressed();
    }
}
