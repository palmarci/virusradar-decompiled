package p008hu.gov.virusradar.Services.Interface;

import java.util.ArrayList;
import okhttp3.ResponseBody;
import p008hu.gov.virusradar.Modules.ConfigurationModel;
import p008hu.gov.virusradar.Modules.Input.ConfirmUserInput;
import p008hu.gov.virusradar.Modules.Input.RegisterDeviceTokenInput;
import p008hu.gov.virusradar.Modules.Input.RegisterUserInput;
import p008hu.gov.virusradar.Modules.Input.UserEncounterInput;
import p008hu.gov.virusradar.Modules.NotificationsResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/* renamed from: hu.gov.virusradar.Services.Interface.IUserService */
public interface IUserService {
    @POST("api/user/confirm-code-adv")
    Call<ArrayList<String>> confirmCodeAdv(@Body ConfirmUserInput confirmUserInput);

    @POST("api/user/delete-profile")
    Call<Boolean> deleteProfile();

    @POST("api/user/configuration")
    Call<ConfigurationModel> getConfiguration();

    @GET("api/user/notifications")
    Call<NotificationsResponseModel> getNotifications(@Query("Page") Integer num, @Query("Size") Integer num2, @Query("Term") String str);

    @POST("api/user/register-device")
    Call<ResponseBody> registerDevice(@Body RegisterDeviceTokenInput registerDeviceTokenInput);

    @POST("api/user/register-number")
    Call<ResponseBody> registerNumber(@Body RegisterUserInput registerUserInput);

    @POST("api/user/renew-alias")
    Call<ArrayList<String>> renewAlias();

    @POST("api/user/upload-encounters-with-code")
    Call<ResponseBody> uploadEncounters(@Body UserEncounterInput userEncounterInput);

    @GET("api/user/verify-submit")
    Call<Boolean> verifySubmit();
}
