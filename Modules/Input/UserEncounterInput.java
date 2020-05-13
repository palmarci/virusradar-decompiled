package p008hu.gov.virusradar.Modules.Input;

import java.util.ArrayList;

/* renamed from: hu.gov.virusradar.Modules.Input.UserEncounterInput */
public class UserEncounterInput {
    public String Code;
    public ArrayList<EncounterModel> Encounters;

    public UserEncounterInput(String str, ArrayList<EncounterModel> arrayList) {
        this.Code = str;
        this.Encounters = arrayList;
    }
}
