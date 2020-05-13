package p008hu.gov.virusradar.Modules.Input;

/* renamed from: hu.gov.virusradar.Modules.Input.NotificationsInput */
public class NotificationsInput {
    public Integer Page;
    public Integer Size;
    public String Term;

    public NotificationsInput(Integer num, Integer num2, String str) {
        this.Page = num;
        this.Size = num2;
        this.Term = str;
    }
}
