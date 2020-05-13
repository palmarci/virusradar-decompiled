package p008hu.gov.virusradar.Services.CallBackInterface;

import p008hu.gov.virusradar.ErrorHandling.CustomError;

/* renamed from: hu.gov.virusradar.Services.CallBackInterface.IHttpCallback */
public interface IHttpCallback {
    void onError(CustomError customError);

    void onSuccess(Object obj);
}
