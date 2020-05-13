package p008hu.gov.virusradar.Modules.MonitorServiceModels;

import android.bluetooth.le.ScanResult;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import p008hu.gov.virusradar.Modules.Input.EncounterEntryModel;
import p008hu.gov.virusradar.Modules.Input.EncounterModel;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper;

/* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.DeviceModel */
public class DeviceModel implements Serializable {

    /* renamed from: id */
    public String f84id;
    public ArrayList<TimeFrame> timeFrames;

    DeviceModel(ScanResult scanResult) {
        this.f84id = AdvertisingHelper.extractMessageFromRecord(scanResult);
        updateTimeFrame(scanResult);
    }

    /* access modifiers changed from: 0000 */
    public void update(ScanResult scanResult) {
        if (scanResult != null) {
            updateTimeFrame(scanResult);
        }
    }

    private void updateTimeFrame(ScanResult scanResult) {
        ArrayList<TimeFrame> arrayList = this.timeFrames;
        if (arrayList == null || arrayList.size() <= 0) {
            this.timeFrames = new ArrayList<>();
            this.timeFrames.add(0, new TimeFrame(scanResult.getRssi()));
        } else if (((TimeFrame) this.timeFrames.get(0)).hasExpired()) {
            if (!((TimeFrame) this.timeFrames.get(0)).isValid()) {
                this.timeFrames.remove(0);
            }
            this.timeFrames.add(0, new TimeFrame(scanResult.getRssi()));
        } else {
            ((TimeFrame) this.timeFrames.get(0)).update(scanResult.getRssi());
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isValid() {
        if (this.timeFrames.size() <= 1) {
            return this.timeFrames.size() > 0 && (!((TimeFrame) this.timeFrames.get(0)).hasExpired() || ((TimeFrame) this.timeFrames.get(0)).isValid());
        }
        return true;
    }

    /* access modifiers changed from: 0000 */
    public EncounterModel mapToEncounterModel() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.timeFrames.iterator();
        while (it.hasNext()) {
            TimeFrame timeFrame = (TimeFrame) it.next();
            if (!timeFrame.validForUpload()) {
                break;
            }
            EncounterEntryModel encounterEntryModel = new EncounterEntryModel();
            encounterEntryModel.setDate(timeFrame.endTime);
            encounterEntryModel.Distance = timeFrame.distance;
            encounterEntryModel.Duration = Double.valueOf(timeFrame.getDurationSeconds());
            arrayList.add(encounterEntryModel);
        }
        return new EncounterModel(this.f84id, arrayList);
    }

    /* access modifiers changed from: 0000 */
    public boolean equals(String str) {
        return str.trim().equalsIgnoreCase(this.f84id.trim());
    }

    /* access modifiers changed from: 0000 */
    public long timeSinceLastDetection() {
        return System.currentTimeMillis() - ((TimeFrame) this.timeFrames.get(0)).endTime;
    }
}
