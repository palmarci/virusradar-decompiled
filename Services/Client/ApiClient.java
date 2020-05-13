package p008hu.gov.virusradar.Services.Client;

import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import p008hu.gov.virusradar.Utilities.FirebaseTokenNotificationUtil;
import p008hu.gov.virusradar.Utilities.VirusTraceApp;
import p009io.fabric.sdk.android.services.network.HttpRequest;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

/* renamed from: hu.gov.virusradar.Services.Client.ApiClient */
public class ApiClient {
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        retrofit = new Builder().baseUrl(VirusTraceApp.globalEnvironment.globalURL).addConverterFactory(GsonConverterFactory.create(getCustomGson())).client(getUnsafeOkHttpClient()).build();
        return retrofit;
    }

    private static OkHttpClient getUnsafeOkHttpClient() {
        PersistentCookieJar persistentCookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(VirusTraceApp.getInstance()));
        try {
            TrustManager[] trustManagerArr = {new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory socketFactory = instance.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.cookieJar(persistentCookieJar);
            builder.addInterceptor(new Interceptor() {
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder newBuilder = chain.request().newBuilder();
                    newBuilder.addHeader("Accept", "application/json");
                    newBuilder.addHeader(HttpRequest.HEADER_CONTENT_TYPE, "application/json; charset=UTF-8");
                    newBuilder.addHeader("X-Requested-With", "XMLHttpRequest");
                    newBuilder.addHeader("Token", FirebaseTokenNotificationUtil.getFirebaseFromSharedPref());
                    return chain.proceed(newBuilder.build());
                }
            }).readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();
            builder.sslSocketFactory(socketFactory, (X509TrustManager) trustManagerArr[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static SSLSocketFactory getSSLSocketFactory() {
        TrustManager[] trustManagerArr = {new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        try {
            SSLContext instance = SSLContext.getInstance("SSL");
            instance.init(null, trustManagerArr, new SecureRandom());
            return instance.getSocketFactory();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (KeyManagementException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static Gson getCustomGson() {
        return new GsonBuilder().create();
    }
}
