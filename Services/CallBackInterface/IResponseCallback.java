package p008hu.gov.virusradar.Services.CallBackInterface;

import p008hu.gov.virusradar.ErrorHandling.CustomError;

/* renamed from: hu.gov.virusradar.Services.CallBackInterface.IResponseCallback */
public interface IResponseCallback<T> {
    void onError(CustomError customError);

    void onSuccess(T t);
}
