package p008hu.gov.virusradar.Services.Interface;

import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* renamed from: hu.gov.virusradar.Services.Interface.ILanguagesService */
public interface ILanguagesService {
    @POST("api/languages/change-language")
    Call<Boolean> changeLanguage(@Body ChangeLanguageInput changeLanguageInput);
}
