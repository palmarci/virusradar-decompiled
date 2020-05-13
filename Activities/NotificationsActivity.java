package p008hu.gov.virusradar.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import p008hu.gov.virusradar.Activities.Dialog.ProgressBarDialog;
import p008hu.gov.virusradar.Adapters.NotificationsAdapter.C1124NotificationsAdapter;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.C1127ErrorHandling;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Listeners.INoNetworkListener;
import p008hu.gov.virusradar.Modules.Input.NotificationsInput;
import p008hu.gov.virusradar.Modules.NotificationsResponseModel;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.UserService;
import p009io.github.inflationx.viewpump.ViewPumpContextWrapper;

/* renamed from: hu.gov.virusradar.Activities.NotificationsActivity */
public class NotificationsActivity extends AppCompatActivity {
    /* access modifiers changed from: private */
    public Context context;
    @BindView(2131230869)
    LinearLayout footerView;
    /* access modifiers changed from: private */
    public Boolean isLoading = Boolean.valueOf(false);
    /* access modifiers changed from: private */
    public GridLayoutManager manager;
    /* access modifiers changed from: private */
    public NotificationsResponseModel notifications;
    /* access modifiers changed from: private */
    public C1124NotificationsAdapter notificationsAdapter;
    /* access modifiers changed from: private */
    public Integer page = Integer.valueOf(1);
    @BindView(2131231002)
    RecyclerView rvNotifications;
    public OnScrollListener scrollListener = new OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (!NotificationsActivity.this.isLoading.booleanValue()) {
                int childCount = NotificationsActivity.this.manager.getChildCount();
                int itemCount = NotificationsActivity.this.manager.getItemCount();
                int findFirstVisibleItemPosition = NotificationsActivity.this.manager.findFirstVisibleItemPosition() + childCount;
                if (findFirstVisibleItemPosition == itemCount && itemCount != 0 && NotificationsActivity.this.totalItems.intValue() > NotificationsActivity.this.notifications.Notifications.size() && NotificationsActivity.this.notifications.Notifications.size() == findFirstVisibleItemPosition) {
                    NotificationsActivity.this.isLoading = Boolean.valueOf(true);
                    NotificationsActivity.this.footerView.setVisibility(0);
                    NotificationsActivity.this.page;
                    NotificationsActivity notificationsActivity = NotificationsActivity.this;
                    notificationsActivity.page = Integer.valueOf(notificationsActivity.page.intValue() + 1);
                    NotificationsActivity.this.getNotifications();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public Integer totalItems = Integer.valueOf(0);
    @BindView(2131231101)
    TextView tvNoItems;

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(context2));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) C1130R.layout.activity_notifications);
        ButterKnife.bind((Activity) this);
        this.context = this;
        this.manager = new GridLayoutManager(this.context, 1);
        this.rvNotifications.setLayoutManager(this.manager);
        this.rvNotifications.addOnScrollListener(this.scrollListener);
        getNotifications();
    }

    /* access modifiers changed from: private */
    public void getNotifications() {
        ProgressBarDialog.showDialog(getSupportFragmentManager(), "tag");
        UserService.getNotifications(new NotificationsInput(this.page, Integer.valueOf(10), ""), new IResponseCallback<NotificationsResponseModel>() {
            public void onSuccess(NotificationsResponseModel notificationsResponseModel) {
                ProgressBarDialog.dismissDialog();
                NotificationsActivity.this.isLoading = Boolean.valueOf(false);
                if (notificationsResponseModel == null || notificationsResponseModel.Total.intValue() == 0) {
                    NotificationsActivity.this.rvNotifications.setVisibility(8);
                    NotificationsActivity.this.tvNoItems.setVisibility(0);
                    return;
                }
                NotificationsActivity.this.tvNoItems.setVisibility(8);
                NotificationsActivity.this.rvNotifications.setVisibility(0);
                NotificationsActivity.this.totalItems = notificationsResponseModel.Total;
                if (NotificationsActivity.this.notifications == null) {
                    NotificationsActivity.this.notifications = notificationsResponseModel;
                    NotificationsActivity notificationsActivity = NotificationsActivity.this;
                    notificationsActivity.notificationsAdapter = new C1124NotificationsAdapter(notificationsActivity.context);
                    NotificationsActivity.this.rvNotifications.setAdapter(NotificationsActivity.this.notificationsAdapter);
                } else {
                    NotificationsActivity.this.notifications.Notifications.addAll(notificationsResponseModel.Notifications);
                }
                NotificationsActivity.this.notificationsAdapter.addNotifications(NotificationsActivity.this.notifications.Notifications);
            }

            public void onError(CustomError customError) {
                ProgressBarDialog.dismissDialog();
                C1127ErrorHandling.handlingError(NotificationsActivity.this.context, customError, new INoNetworkListener() {
                    public void onClickRefresh() {
                        NotificationsActivity.this.getNotifications();
                    }
                });
            }
        });
    }

    @OnClick({2131230916})
    public void onBack() {
        onBackPressed();
    }
}
