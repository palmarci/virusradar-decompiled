package p008hu.gov.virusradar.Services;

import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Modules.Input.ConfirmUserInput;
import p008hu.gov.virusradar.Modules.Input.NotificationsInput;
import p008hu.gov.virusradar.Modules.Input.RegisterDeviceTokenInput;
import p008hu.gov.virusradar.Modules.Input.RegisterUserInput;
import p008hu.gov.virusradar.Modules.Input.UserEncounterInput;
import p008hu.gov.virusradar.Services.CallBackInterface.IHttpCallback;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.Client.ApiClient;
import p008hu.gov.virusradar.Services.Interface.IUserService;
import p008hu.gov.virusradar.Utilities.HttpUtilities;

/* renamed from: hu.gov.virusradar.Services.UserService */
public class UserService {
    public static void register(RegisterUserInput registerUserInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).registerNumber(registerUserInput), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void confirm(ConfirmUserInput confirmUserInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).confirmCodeAdv(confirmUserInput), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void registerDevice(RegisterDeviceTokenInput registerDeviceTokenInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).registerDevice(registerDeviceTokenInput), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void uploadEncounters(UserEncounterInput userEncounterInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).uploadEncounters(userEncounterInput), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void getNotifications(NotificationsInput notificationsInput, final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).getNotifications(notificationsInput.Page, notificationsInput.Size, notificationsInput.Term), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void verifySubmit(final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).verifySubmit(), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void renewAlias(final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).renewAlias(), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void deleteProfile(final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).deleteProfile(), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }

    public static void getConfiguration(final IResponseCallback iResponseCallback) {
        new HttpUtilities().post(((IUserService) ApiClient.getClient().create(IUserService.class)).getConfiguration(), new IHttpCallback() {
            public void onSuccess(Object obj) {
                iResponseCallback.onSuccess(obj);
            }

            public void onError(CustomError customError) {
                iResponseCallback.onError(customError);
            }
        });
    }
}
