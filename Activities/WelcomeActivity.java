package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import java.util.Iterator;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.C1127ErrorHandling;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import p008hu.gov.virusradar.Modules.LanguageModel;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.LanguagesService;
import p008hu.gov.virusradar.Utilities.AppLanguagesUtil;
import p008hu.gov.virusradar.Utilities.AuthenticationUtil;
import p008hu.gov.virusradar.Utilities.PermissionUtil;
import p008hu.gov.virusradar.Utilities.Util;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.WelcomeActivity */
public class WelcomeActivity extends AppCompatActivity {
    private static final int fabTextSizeDp = 14;
    @BindView(2131230803)
    Button btnNext;
    @BindView(2131230804)
    Button btnSkip;
    Context context;
    private TextView[] dots;
    @BindView(2131230907)
    LinearLayout dotsLayout;
    @BindView(2131230861)
    FloatingActionMenu famLocalization;
    /* access modifiers changed from: private */
    public int[] layouts;
    private MyViewPagerAdapter myViewPagerAdapter;
    @BindView(2131231127)
    ViewPager viewPager;
    OnPageChangeListener viewPagerPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            WelcomeActivity.this.addBottomDots(i);
            if (i == WelcomeActivity.this.layouts.length - 1) {
                WelcomeActivity.this.btnNext.setText(WelcomeActivity.this.getString(C1130R.string.start));
                WelcomeActivity.this.btnSkip.setVisibility(8);
                return;
            }
            WelcomeActivity.this.btnNext.setText(WelcomeActivity.this.getString(C1130R.string.next));
            WelcomeActivity.this.btnSkip.setVisibility(0);
        }
    };

    /* renamed from: hu.gov.virusradar.Activities.WelcomeActivity$MyViewPagerAdapter */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MyViewPagerAdapter() {
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            this.layoutInflater = (LayoutInflater) WelcomeActivity.this.getSystemService("layout_inflater");
            View inflate = this.layoutInflater.inflate(WelcomeActivity.this.layouts[i], viewGroup, false);
            viewGroup.addView(inflate);
            return inflate;
        }

        public int getCount() {
            return WelcomeActivity.this.layouts.length;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_welcome);
        ButterKnife.bind((Activity) this);
        this.layouts = new int[]{C1130R.layout.welcome_slide1, C1130R.layout.welcome_slide2, C1130R.layout.welcome_slide3, C1130R.layout.welcome_slide4};
        addBottomDots(0);
        this.context = this;
        changeStatusBarColor();
        this.myViewPagerAdapter = new MyViewPagerAdapter();
        this.viewPager.setAdapter(this.myViewPagerAdapter);
        this.viewPager.addOnPageChangeListener(this.viewPagerPageChangeListener);
        this.btnSkip.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WelcomeActivity.this.launchHomeScreen();
            }
        });
        this.btnNext.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                int access$100 = WelcomeActivity.this.getItem(1);
                if (access$100 < WelcomeActivity.this.layouts.length) {
                    WelcomeActivity.this.viewPager.setCurrentItem(access$100);
                } else {
                    WelcomeActivity.this.launchHomeScreen();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void addBottomDots(int i) {
        TextView[] textViewArr;
        this.dots = new TextView[this.layouts.length];
        int color = ContextCompat.getColor(this, C1130R.C1131color.active_line);
        int color2 = ContextCompat.getColor(this, C1130R.C1131color.inactive_line);
        this.dotsLayout.removeAllViews();
        int i2 = 0;
        while (true) {
            textViewArr = this.dots;
            if (i2 >= textViewArr.length) {
                break;
            }
            textViewArr[i2] = new TextView(this);
            this.dots[i2].setText(Html.fromHtml("&#8226;"));
            this.dots[i2].setTextSize(35.0f);
            this.dots[i2].setTextColor(color2);
            this.dotsLayout.addView(this.dots[i2]);
            i2++;
        }
        if (textViewArr.length > 0) {
            textViewArr[i].setTextColor(color);
        }
    }

    /* access modifiers changed from: private */
    public int getItem(int i) {
        return this.viewPager.getCurrentItem() + i;
    }

    /* access modifiers changed from: private */
    public void launchHomeScreen() {
        AuthenticationUtil.setFirstTimeLaunch(false);
        if (!AuthenticationUtil.isUserLogged()) {
            startConsentActivity();
        } else if (!PermissionUtil.areAllPermissionsGranted((WelcomeActivity) this.context) || !PermissionUtil.requestBatteryOptimisationDisable((WelcomeActivity) this.context, false)) {
            startPermissionActivity();
        } else {
            startMainActivity();
        }
    }

    private void startPermissionActivity() {
        Intent intent = new Intent(this, PermissionActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void startConsentActivity() {
        Intent intent = new Intent(this, ConsentActivity.class);
        intent.addFlags(335544320);
        startActivity(intent);
        finish();
    }

    private void changeStatusBarColor() {
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(ContextCompat.getColor(this, C1130R.C1131color.welcome_slider_background));
        }
    }

    private void setupFabLocalization() {
        this.famLocalization.setIconAnimated(false);
        this.famLocalization.setClosedOnTouchOutside(true);
        LanguageModel defaultLocaleFromSharedPreferences = AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this.context);
        this.famLocalization.getMenuIconView().setImageBitmap(Util.textAsBitmap(defaultLocaleFromSharedPreferences.shortTitle, 14, -1));
        Iterator it = AppLanguagesUtil.appLanguageModels.iterator();
        while (it.hasNext()) {
            LanguageModel languageModel = (LanguageModel) it.next();
            if (!defaultLocaleFromSharedPreferences.shortTitle.equalsIgnoreCase(languageModel.shortTitle)) {
                this.famLocalization.addMenuButton(getLocalizationFab(languageModel));
            }
        }
    }

    private FloatingActionButton getLocalizationFab(LanguageModel languageModel) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(this.context);
        floatingActionButton.setButtonSize(1);
        floatingActionButton.setColorNormal(-1);
        floatingActionButton.setColorPressed(C1130R.C1131color.fab_pressed_color);
        floatingActionButton.setImageBitmap(Util.textAsBitmap(languageModel.shortTitle, 14, ContextCompat.getColor(this.context, C1130R.C1131color.colorPrimary)));
        floatingActionButton.setOnClickListener(getOnFabOptionClickListener(languageModel));
        return floatingActionButton;
    }

    private OnClickListener getOnFabOptionClickListener(final LanguageModel languageModel) {
        return new OnClickListener() {
            public void onClick(View view) {
                WelcomeActivity.this.changeUserLanguage(languageModel);
            }
        };
    }

    /* access modifiers changed from: private */
    public void changeUserLanguage(final LanguageModel languageModel) {
        ChangeLanguageInput changeLanguageInput = new ChangeLanguageInput();
        changeLanguageInput.Language = languageModel.culture;
        this.famLocalization.close(true);
        LanguagesService.changeLanguage(changeLanguageInput, new IResponseCallback() {
            public void onSuccess(Object obj) {
                AppLanguagesUtil.setDefaultFromUser(languageModel);
                Util.setLocale(WelcomeActivity.this.context, languageModel);
                WelcomeActivity.this.recreate();
            }

            public void onError(CustomError customError) {
                C1127ErrorHandling.handlingError(WelcomeActivity.this.context, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        WelcomeActivity.this.changeUserLanguage(languageModel);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Util.setLocale(this, AppLanguagesUtil.getDefaultLocaleFromSharedPreferences(this));
    }
}
