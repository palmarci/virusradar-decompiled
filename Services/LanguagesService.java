package p008hu.gov.virusradar.Services;

import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Modules.Input.ChangeLanguageInput;
import p008hu.gov.virusradar.Services.CallBackInterface.IHttpCallback;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.Client.ApiClient;
import p008hu.gov.virusradar.Services.Interface.ILanguagesService;
import p008hu.gov.virusradar.Utilities.HttpUtilities;

/* renamed from: hu.gov.virusradar.Services.LanguagesService */
public class LanguagesService {
    public static void changeLanguage(ChangeLanguageInput changeLanguageInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((ILanguagesService) ApiClient.getClient().create(ILanguagesService.class)).changeLanguage(changeLanguageInput), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }
}
