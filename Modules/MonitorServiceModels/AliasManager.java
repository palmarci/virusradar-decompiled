package p008hu.gov.virusradar.Modules.MonitorServiceModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import p008hu.gov.virusradar.ErrorHandling.CustomError;
import p008hu.gov.virusradar.Services.CallBackInterface.IResponseCallback;
import p008hu.gov.virusradar.Services.UserService;
import p008hu.gov.virusradar.Utilities.Logger;
import p008hu.gov.virusradar.Utilities.SharedPrefsUtil;

/* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager */
public class AliasManager implements Serializable {
    private static final String KEY_ALIAS = "Alias";
    private static final String KEY_ALIAS_MODELS = "AliasModels";
    private static AliasManager instance;
    private ArrayList<AliasModel> availableAliases;
    private AliasModel currentAlias;

    public static AliasManager getInstance() {
        if (instance == null) {
            instance = (AliasManager) SharedPrefsUtil.getObject(KEY_ALIAS_MODELS, AliasManager.class);
            if (instance == null) {
                instance = new AliasManager();
            }
            AliasManager aliasManager = instance;
            if (aliasManager.availableAliases == null) {
                aliasManager.availableAliases = new ArrayList<>();
            }
        }
        return instance;
    }

    private AliasManager() {
    }

    public AliasModel getAliasModel() {
        AliasModel aliasModel = this.currentAlias;
        String str = KEY_ALIAS;
        if (aliasModel == null || aliasModel.isExpired()) {
            removeExpired();
            if (this.availableAliases.size() > 0) {
                this.currentAlias = (AliasModel) this.availableAliases.get(0);
            } else {
                this.currentAlias = new AliasModel(SharedPrefsUtil.getString(str));
            }
            renewAlias();
        }
        this.currentAlias.updateAliasLifetime();
        Logger.log(String.format("ALIAS_USED: %s >>> ALIASES_LEFT: %d", new Object[]{this.currentAlias.alias, Integer.valueOf(this.availableAliases.size())}));
        SharedPrefsUtil.saveObject(KEY_ALIAS_MODELS, this);
        SharedPrefsUtil.saveString(str, this.currentAlias.alias);
        return this.currentAlias;
    }

    private void removeExpired() {
        int size = this.availableAliases.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            if (((AliasModel) this.availableAliases.get(size)).isExpired()) {
                this.availableAliases.remove(size);
            }
        }
    }

    public void addAll(ArrayList<String> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.availableAliases.add(new AliasModel((String) it.next()));
        }
        SharedPrefsUtil.saveObject(KEY_ALIAS_MODELS, this);
    }

    public boolean isCurrentAliasExpired() {
        AliasModel aliasModel = this.currentAlias;
        if (aliasModel == null) {
            return true;
        }
        aliasModel.updateAliasLifetime();
        return this.currentAlias.isExpired();
    }

    public void deleteAll() {
        this.availableAliases.clear();
        this.currentAlias = null;
        SharedPrefsUtil.delete(KEY_ALIAS, KEY_ALIAS_MODELS);
    }

    private void renewAlias() {
        if (this.availableAliases.size() <= 2) {
            UserService.renewAlias(new IResponseCallback<ArrayList<String>>() {
                public void onError(CustomError customError) {
                }

                public void onSuccess(ArrayList<String> arrayList) {
                    AliasManager.this.addAll(arrayList);
                }
            });
        }
    }

    public boolean hasAlias() {
        ArrayList<AliasModel> arrayList = this.availableAliases;
        return (arrayList != null && arrayList.size() > 0) || SharedPrefsUtil.getString(KEY_ALIAS) != null;
    }
}
