package p008hu.gov.virusradar.Modules.MonitorServiceModels;

import java.io.Serializable;
import p008hu.gov.virusradar.Modules.ConfigurationModel;

/* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.AliasModel */
public class AliasModel implements Serializable {
    public String alias;
    private Long issuedTime;

    AliasModel(String str) {
        this.alias = str;
    }

    /* access modifiers changed from: 0000 */
    public boolean isExpired() {
        return this.issuedTime != null && System.currentTimeMillis() - this.issuedTime.longValue() > ConfigurationModel.getConfiguration().getReissueTokenPeriodMs().longValue();
    }

    /* access modifiers changed from: 0000 */
    public void updateAliasLifetime() {
        if (this.issuedTime == null) {
            this.issuedTime = Long.valueOf(System.currentTimeMillis());
        }
    }
}
