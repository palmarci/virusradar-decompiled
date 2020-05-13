package p008hu.gov.virusradar.Modules;

import p008hu.gov.virusradar.Utilities.SharedPrefsUtil;

/* renamed from: hu.gov.virusradar.Modules.ConfigurationModel */
public class ConfigurationModel {
    private static final int DEFAULT_DAYS_INCLUDED = 14;
    private static final String DEFAULT_PHONE_PREFIX = "+36";
    private static final int DEFAULT_REISSUE_PERIOD = 720;
    private static final String KEY_DEFAULT = "keyDefaultConfiguration";
    public Integer daysIncluded;
    public String phonePrefix;
    public Integer reissueTokenPeriod;
    public Boolean verifySubmit;

    public ConfigurationModel(Boolean bool, Integer num, Integer num2, String str) {
        this.verifySubmit = bool;
        this.daysIncluded = num;
        this.reissueTokenPeriod = num2;
        this.phonePrefix = str;
    }

    public static ConfigurationModel getConfiguration() {
        ConfigurationModel configurationModel = (ConfigurationModel) SharedPrefsUtil.getObject(KEY_DEFAULT, ConfigurationModel.class);
        return configurationModel == null ? new ConfigurationModel(Boolean.valueOf(false), Integer.valueOf(14), Integer.valueOf(DEFAULT_REISSUE_PERIOD), DEFAULT_PHONE_PREFIX) : configurationModel;
    }

    public void save() {
        SharedPrefsUtil.saveObject(KEY_DEFAULT, this);
    }

    public Integer getDaysIncludedMs() {
        return Integer.valueOf(this.daysIncluded.intValue() * 24 * 60 * 60 * 1000);
    }

    public Long getReissueTokenPeriodMs() {
        return Long.valueOf(((long) (this.reissueTokenPeriod.intValue() * 60)) * 1000);
    }
}
