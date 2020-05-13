package p008hu.gov.virusradar.Modules.MonitorServiceModels;

import android.bluetooth.le.ScanResult;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import p008hu.gov.virusradar.Modules.Input.UserEncounterInput;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper;
import p008hu.gov.virusradar.Utilities.Logger;
import p008hu.gov.virusradar.Utilities.Util;
import p008hu.gov.virusradar.Utilities.VirusTraceApp;

/* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel */
public class DeviceListModel extends ArrayList<DeviceModel> implements Serializable {
    private static final int CLEANER_RECURRENCE_TIME = 86400000;
    private static final File DEVICE_MODEL_FILE;
    private static final int UI_UPDATE_INTERVAL = 20000;
    private static DeviceListModel instance;
    private static Long lastCleaned;
    private static Thread storageUpdateThread;
    private Thread deviceChangeMonitor;
    private IOnDeviceChangeListener iOnDeviceChangeListener;

    /* renamed from: hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel$IOnDeviceChangeListener */
    public interface IOnDeviceChangeListener {
        void onActiveDevicesChange(ArrayList<DeviceModel> arrayList);
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(VirusTraceApp.getInstance().getFilesDir());
        sb.append("/mainData");
        DEVICE_MODEL_FILE = new File(sb.toString());
    }

    public static DeviceListModel getInstance() {
        if (instance == null) {
            instance = (DeviceListModel) Util.getObjectFromFile(DeviceListModel.class, DEVICE_MODEL_FILE);
            DeviceListModel deviceListModel = instance;
            if (deviceListModel == null) {
                deviceListModel = new DeviceListModel();
            }
            instance = deviceListModel;
            Logger.log("LOADED");
        }
        return instance;
    }

    private DeviceListModel() {
    }

    public void updateList(ScanResult scanResult) {
        if (AdvertisingHelper.isResponseValid(scanResult)) {
            DeviceModel fetchDeviceModel = fetchDeviceModel(scanResult);
            if (fetchDeviceModel == null) {
                add(new DeviceModel(scanResult));
            } else {
                fetchDeviceModel.update(scanResult);
            }
        }
    }

    private DeviceModel fetchDeviceModel(ScanResult scanResult) {
        return fetchDeviceModel(AdvertisingHelper.extractMessageFromRecord(scanResult));
    }

    private DeviceModel fetchDeviceModel(String str) {
        int size = size();
        DeviceModel deviceModel = null;
        if (str != null) {
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (!((DeviceModel) get(size)).isValid()) {
                    remove(size);
                } else if (((DeviceModel) get(size)).equals(str)) {
                    deviceModel = (DeviceModel) get(size);
                }
            }
        }
        return deviceModel;
    }

    public UserEncounterInput mapToUserEncounter(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator it = iterator();
        while (it.hasNext()) {
            DeviceModel deviceModel = (DeviceModel) it.next();
            if (deviceModel.mapToEncounterModel().Entry.size() > 0) {
                arrayList.add(deviceModel.mapToEncounterModel());
            }
        }
        return new UserEncounterInput(str, arrayList);
    }

    private ArrayList<DeviceModel> getCurrentActiveUsers() {
        ArrayList<DeviceModel> arrayList = new ArrayList<>();
        Iterator it = iterator();
        while (it.hasNext()) {
            DeviceModel deviceModel = (DeviceModel) it.next();
            if (deviceModel.timeSinceLastDetection() < 20000) {
                arrayList.add(deviceModel);
            }
        }
        return arrayList;
    }

    public void setOnDeviceChangeListener(IOnDeviceChangeListener iOnDeviceChangeListener2) {
        this.iOnDeviceChangeListener = iOnDeviceChangeListener2;
        asyncDeviceChangeNotifier();
    }

    private void asyncDeviceChangeNotifier() {
        Thread thread = this.deviceChangeMonitor;
        if (thread != null) {
            thread.interrupt();
        }
        this.deviceChangeMonitor = new Thread(new Runnable() {
            public final void run() {
                DeviceListModel.this.lambda$asyncDeviceChangeNotifier$0$DeviceListModel();
            }
        });
        this.deviceChangeMonitor.start();
    }

    public /* synthetic */ void lambda$asyncDeviceChangeNotifier$0$DeviceListModel() {
        while (this.iOnDeviceChangeListener != null && Util.sleep(UI_UPDATE_INTERVAL)) {
            this.iOnDeviceChangeListener.onActiveDevicesChange(getCurrentActiveUsers());
        }
    }

    public static void updateStorage() {
        if (instance != null) {
            Thread thread = storageUpdateThread;
            if (thread != null && thread.isAlive()) {
                return;
            }
            if (cleaningTime()) {
                runAsyncListCleaner();
            } else {
                saveAsyncModels();
            }
        }
    }

    private static void saveAsyncModels() {
        DeviceListModel deviceListModel = instance;
        deviceListModel.getClass();
        storageUpdateThread = new Thread(new Runnable() {
            public final void run() {
                DeviceListModel.this.saveModel();
            }
        });
        storageUpdateThread.start();
    }

    private static void runAsyncListCleaner() {
        DeviceListModel deviceListModel = instance;
        deviceListModel.getClass();
        storageUpdateThread = new Thread(new Runnable() {
            public final void run() {
                DeviceListModel.this.cleanList();
            }
        });
        storageUpdateThread.start();
    }

    /* access modifiers changed from: private */
    public void saveModel() {
        Util.saveObjectToFile(instance, DEVICE_MODEL_FILE);
        Logger.log("SAVED");
    }

    /* access modifiers changed from: private */
    public void cleanList() {
        int size = size();
        while (true) {
            size--;
            if (size >= 0) {
                int size2 = ((DeviceModel) get(size)).timeFrames.size();
                while (true) {
                    size2--;
                    if (size2 >= 0 && ((TimeFrame) ((DeviceModel) get(size)).timeFrames.get(size2)).removeFromStorage()) {
                        ((DeviceModel) get(size)).timeFrames.remove(size2);
                    }
                }
                if (((DeviceModel) get(size)).timeFrames.size() == 0) {
                    remove(size);
                }
            } else {
                lastCleaned = Long.valueOf(System.currentTimeMillis());
                saveModel();
                Logger.log("LIST_CLEANED");
                return;
            }
        }
    }

    private static boolean cleaningTime() {
        return lastCleaned == null || System.currentTimeMillis() - lastCleaned.longValue() > 86400000;
    }

    public void deleteAll() {
        clear();
        saveModel();
    }
}
