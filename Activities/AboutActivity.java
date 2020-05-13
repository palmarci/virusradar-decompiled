package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p008hu.gov.virusradar.C1130R;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.AboutActivity */
public class AboutActivity extends AppCompatActivity {
    Context context;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_about);
        this.context = this;
        ButterKnife.bind((Activity) this);
    }

    @OnClick({2131230916})
    public void onBack() {
        onBackPressed();
    }

    @OnClick({2131230894})
    public void onInnovacios() {
        String string = getString(C1130R.string.kormany_link);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(string));
        startActivity(intent);
    }

    @OnClick({2131230891})
    public void onBiztributor() {
        String string = getString(C1130R.string.biztributor_link);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(string));
        startActivity(intent);
    }

    @OnClick({2131230895})
    public void onKifu() {
        String string = getString(C1130R.string.kifu_link);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(string));
        startActivity(intent);
    }

    @OnClick({2131230898})
    public void onNextsense() {
        String string = getString(C1130R.string.nextsense_link);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(string));
        startActivity(intent);
    }
}
