package p008hu.gov.virusradar.Modules.Input;

import java.util.ArrayList;

/* renamed from: hu.gov.virusradar.Modules.Input.EncounterModel */
public class EncounterModel {
    public String Alias;
    public ArrayList<EncounterEntryModel> Entry;

    public EncounterModel(String str, ArrayList<EncounterEntryModel> arrayList) {
        this.Alias = str;
        this.Entry = arrayList;
    }
}
