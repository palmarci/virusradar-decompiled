package p008hu.gov.virusradar.Utilities;

import com.crashlytics.android.Crashlytics;
import java.io.IOException;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Services.CallBackInterface.IHttpCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* renamed from: hu.gov.virusradar.Utilities.HttpUtilities */
public class HttpUtilities {
    /* access modifiers changed from: private */
    public static Call<?> call;
    /* access modifiers changed from: private */
    public String error;
    /* access modifiers changed from: private */
    public int errorCode;

    public <T> void post(Call<T> call2, final IHttpCallback iHttpCallback) {
        if (!InternetConnectivityUtil.isNetworkAvailable(VirusTraceApp.getInstance())) {
            iHttpCallback.onError(new CustomError(VirusTraceApp.getInstance().getResources().getString(C1130R.string.no_internet_connection), 666));
            return;
        }
        call = call2;
        call2.enqueue(new Callback<T>() {
            public void onResponse(Call<T> call, Response<T> response) {
                HttpUtilities.call = null;
                if (response.isSuccessful()) {
                    iHttpCallback.onSuccess(response.body());
                    return;
                }
                try {
                    HttpUtilities.this.error = response.errorBody().string();
                    HttpUtilities.this.errorCode = response.code();
                    Crashlytics.logException(new Exception(HttpUtilities.this.error));
                    iHttpCallback.onError(new CustomError(HttpUtilities.this.error, HttpUtilities.this.errorCode));
                } catch (IOException unused) {
                    iHttpCallback.onError(new CustomError(HttpUtilities.this.error, HttpUtilities.this.errorCode));
                }
            }

            public void onFailure(Call<T> call, Throwable th) {
                HttpUtilities.call = null;
                if (!call.isCanceled()) {
                    Crashlytics.logException(new Exception(th.getMessage()));
                    iHttpCallback.onError(new CustomError(th.getMessage(), 500));
                }
            }
        });
    }

    public static void cancelActiveCall() {
        Call<?> call2 = call;
        if (call2 != null) {
            call2.cancel();
            call = null;
        }
    }
}
