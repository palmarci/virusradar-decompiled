package p008hu.gov.virusradar.Modules.MonitorServiceModels;

import p008hu.gov.virusradar.Modules.ConfigurationModel;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.TimeFrame */
public class TimeFrame {
    private static final String TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    private static final int TIME_FRAME_EXPIRY_MEMORY = 1814400000;
    private static final int TIME_FRAME_TIME_LIMIT = 1800000;
    private static final int TIME_FRAME_TIME_OUT = 30000;
    private static final int TIME_FRAME_VALIDITY = 0;
    public Double distance;
    private Double distanceSum;
    public long endTime = this.startTime;
    public long startTime = System.currentTimeMillis();
    private int updateCount;

    TimeFrame(int i) {
        this.distance = Double.valueOf(AdvertisingHelper.calculateDistance(i));
        this.distanceSum = this.distance;
        this.updateCount = 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean isValid() {
        return this.endTime - this.startTime >= 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean hasExpired() {
        long currentTimeMillis = System.currentTimeMillis();
        long j = this.endTime;
        return currentTimeMillis - j >= 30000 || j - this.startTime >= 1800000;
    }

    /* access modifiers changed from: 0000 */
    public boolean validForUpload() {
        return System.currentTimeMillis() - this.endTime < ((long) ConfigurationModel.getConfiguration().getDaysIncludedMs().intValue());
    }

    /* access modifiers changed from: 0000 */
    public boolean removeFromStorage() {
        return System.currentTimeMillis() - this.endTime > 1814400000;
    }

    /* access modifiers changed from: 0000 */
    public void update(int i) {
        updateMinDistance(i);
        this.endTime = System.currentTimeMillis();
    }

    private void updateMinDistance(int i) {
        this.distance = Double.valueOf(Math.min(AdvertisingHelper.calculateDistance(i), this.distance.doubleValue()));
    }

    public String getDurationMinutes() {
        return Util.convertDuration(Long.valueOf(this.endTime - this.startTime));
    }

    public double getDurationSeconds() {
        return (double) (((float) (this.endTime - this.startTime)) / 1000.0f);
    }

    public String toString() {
        return String.format("Time: %s, Duration: %s, Distance: %.1f", new Object[]{Util.formatDate(Long.valueOf(this.startTime), TIME_FORMAT), getDurationMinutes(), this.distance});
    }
}
