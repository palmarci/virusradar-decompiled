package p008hu.gov.virusradar.Utilities;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import p008hu.gov.virusradar.C1130R;
import p008hu.gov.virusradar.Modules.LanguageModel;

/* renamed from: hu.gov.virusradar.Utilities.AppLanguagesUtil */
public class AppLanguagesUtil {
    public static ArrayList<LanguageModel> appLanguageModels = new ArrayList<>();
    public static boolean isFromRecreate = false;

    public static void setAppLanguageModels(Context context) {
        LanguageModel languageModel = new LanguageModel();
        languageModel.f83id = 1;
        languageModel.shortTitle = context.getString(C1130R.string.short_title_Hu);
        languageModel.title = context.getString(C1130R.string.title_Hu);
        languageModel.isDefault = true;
        languageModel.culture = context.getString(C1130R.string.culture_Hu);
        appLanguageModels.add(languageModel);
        LanguageModel languageModel2 = new LanguageModel();
        languageModel2.f83id = 2;
        languageModel2.shortTitle = context.getString(C1130R.string.short_title_En);
        languageModel2.title = context.getString(C1130R.string.title_En);
        languageModel2.isDefault = false;
        languageModel2.culture = context.getString(C1130R.string.culture_En);
        appLanguageModels.add(languageModel2);
    }

    public static LanguageModel getDefaultLocaleFromSharedPreferences(Context context) {
        String string = context.getSharedPreferences(context.getResources().getString(C1130R.string.VirusTracePrefFile), 0).getString(context.getResources().getString(C1130R.string.languages_prefs), "");
        Gson gson = new Gson();
        Type type = new TypeToken<LanguageModel>() {
        }.getType();
        if (gson.fromJson(string, type) != null) {
            return (LanguageModel) gson.fromJson(string, type);
        }
        return (LanguageModel) appLanguageModels.get(0);
    }

    public static void saveDefaultLocaleInSharedPreferences(Context context, LanguageModel languageModel) {
        Editor edit = context.getSharedPreferences(context.getResources().getString(C1130R.string.VirusTracePrefFile), 0).edit();
        edit.putString(context.getResources().getString(C1130R.string.languages_prefs), new Gson().toJson((Object) languageModel));
        edit.commit();
    }

    public static LanguageModel getSelectedLanguage(ArrayList<LanguageModel> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            LanguageModel languageModel = (LanguageModel) it.next();
            if (languageModel.isDefault) {
                return languageModel;
            }
        }
        return (LanguageModel) arrayList.get(0);
    }

    public static void setDefaultFromUser(LanguageModel languageModel) {
        Iterator it = appLanguageModels.iterator();
        while (it.hasNext()) {
            LanguageModel languageModel2 = (LanguageModel) it.next();
            if (languageModel2.f83id == languageModel.f83id) {
                languageModel2.isDefault = true;
            } else {
                languageModel2.isDefault = false;
            }
        }
    }
}
