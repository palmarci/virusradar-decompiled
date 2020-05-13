package p008hu.gov.virusradar.ErrorHandling;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* renamed from: hu.gov.virusradar.ErrorHandling.ErrorModel */
public class ErrorModel implements Serializable {
    @SerializedName("errors")
    public ArrayList<ErrorCompactModel> Errors;
    @SerializedName("Message")
    public String Message;

    public HashMap<String, ArrayList<String>> ToHashMap() {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        Iterator it = this.Errors.iterator();
        while (it.hasNext()) {
            ErrorCompactModel errorCompactModel = (ErrorCompactModel) it.next();
            if (hashMap.containsKey(errorCompactModel.Key)) {
                ((ArrayList) hashMap.get(errorCompactModel.Key)).add(errorCompactModel.Errors.get(0));
            } else {
                ArrayList arrayList = new ArrayList();
                Iterator it2 = errorCompactModel.Errors.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("\n");
                    arrayList.add(sb.toString());
                }
                hashMap.put(errorCompactModel.Key != null ? errorCompactModel.Key.toLowerCase() : null, arrayList);
            }
        }
        return hashMap;
    }
}
