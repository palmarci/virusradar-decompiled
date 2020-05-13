package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p008hu.gov.virusradar.C1130R;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.UpdateActivity */
public class UpdateActivity extends AppCompatActivity {
    private Context context;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_update);
        ButterKnife.bind((Activity) this);
        this.context = this;
    }

    @OnClick({2131231118})
    public void openGooglePlay(View view) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268468224);
        intent.setData(Uri.parse(getString(C1130R.string.google_play_link)));
        startActivity(intent);
        finish();
    }
}
