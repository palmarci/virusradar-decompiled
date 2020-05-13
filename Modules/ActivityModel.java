package p008hu.gov.virusradar.Modules;

import java.util.Date;

/* renamed from: hu.gov.virusradar.Modules.ActivityModel */
public class ActivityModel {
    public String Alias;
    public Date Date;
    public Double Distance;

    public ActivityModel(String str, Double d, Date date) {
        this.Alias = str;
        this.Distance = d;
        this.Date = date;
    }
}
