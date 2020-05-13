package p008hu.gov.virusradar.Modules.Input;

import java.util.Calendar;
import java.util.Date;

/* renamed from: hu.gov.virusradar.Modules.Input.EncounterEntryModel */
public class EncounterEntryModel {
    public Date Date;
    public Double Distance;
    public Double Duration;
    public Date End;
    public Date Start;

    public void setDate(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        instance.set(10, 0);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        this.Date = instance.getTime();
    }
}
