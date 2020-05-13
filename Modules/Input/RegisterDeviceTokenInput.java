package p008hu.gov.virusradar.Modules.Input;

/* renamed from: hu.gov.virusradar.Modules.Input.RegisterDeviceTokenInput */
public class RegisterDeviceTokenInput {
    public Integer Platform;
    public String Token;

    public RegisterDeviceTokenInput(String str, Integer num) {
        this.Token = str;
        this.Platform = num;
    }
}
