package p008hu.gov.virusradar.Utilities.BluetoothUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanFilter.Builder;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.ParcelUuid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;
import p008hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper.IAdvertisementListener;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Utilities.BluetoothUtils.BleAdvertisingReceiver */
class BleAdvertisingReceiver {
    private static final double BLUETOOTH_RSSI_POWER_COEF = -69.0d;
    private static final int MAX_POSSIBLE_DISTANCE = 20;
    private static BleAdvertisingReceiver instance;
    private BluetoothAdapter bleAdapter;
    private IAdvertisementListener iAdvertisementListener;
    private ScanCallback scanCallback = new ScanCallback() {
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            BleAdvertisingReceiver.this.handleScanResult(scanResult);
        }
    };

    BleAdvertisingReceiver() {
    }

    static void initiateScan(BluetoothAdapter bluetoothAdapter, IAdvertisementListener iAdvertisementListener2) {
        try {
            if (instance == null) {
                instance = new BleAdvertisingReceiver();
            }
            instance.bleAdapter = bluetoothAdapter;
            instance.iAdvertisementListener = iAdvertisementListener2;
            instance.stopScan();
            instance.startScan();
        } catch (Exception unused) {
        }
    }

    static void disableScan() {
        try {
            instance.stopScan();
        } catch (Exception unused) {
        }
    }

    private void startScan() {
        this.bleAdapter.getBluetoothLeScanner().startScan(getFilter(), getScanSettings(), this.scanCallback);
    }

    private void stopScan() {
        this.bleAdapter.getBluetoothLeScanner().stopScan(this.scanCallback);
    }

    private List<ScanFilter> getFilter() {
        final Builder builder = new Builder();
        builder.setServiceUuid(AdvertisingHelper.GUUID, AdvertisingHelper.MASKUUID);
        return new ArrayList<ScanFilter>() {
            {
                add(builder.build());
            }
        };
    }

    private ScanSettings getScanSettings() {
        ScanSettings.Builder builder = new ScanSettings.Builder();
        builder.setScanMode(1);
        builder.setReportDelay(0);
        return builder.build();
    }

    /* access modifiers changed from: private */
    public void handleScanResult(ScanResult scanResult) {
        DeviceListModel.getInstance().updateList(scanResult);
        notifyMessage();
    }

    private void notifyMessage() {
        IAdvertisementListener iAdvertisementListener2 = this.iAdvertisementListener;
        if (iAdvertisementListener2 != null) {
            iAdvertisementListener2.onDeviceListUpdate(DeviceListModel.getInstance());
        }
    }

    static String extractMessageFromRecord(ScanResult scanResult) {
        try {
            byte[] bytes = ((ScanRecord) Objects.requireNonNull(scanResult.getScanRecord())).getBytes();
            int substream = Util.substream(bytes, AdvertisingHelper.MESSAGE_PREFIX);
            int substream2 = Util.substream(bytes, AdvertisingHelper.MESSAGE_POSTFIX) - 1;
            if (substream2 < 0) {
                substream2 = bytes.length;
            }
            if (substream >= 0) {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    if (substream >= substream2) {
                        break;
                    } else if (bytes[substream] == 0) {
                        break;
                    } else {
                        sb.append((char) bytes[substream]);
                        substream++;
                    }
                }
                return sb.toString().toLowerCase();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    static boolean isResponseValid(ScanResult scanResult) {
        try {
            for (ParcelUuid equals : ((ScanRecord) Objects.requireNonNull(scanResult.getScanRecord())).getServiceUuids()) {
                if (AdvertisingHelper.GUUID.equals(equals)) {
                    if (AdvertisingHelper.extractMessageFromRecord(scanResult) != null) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    static double calculateDistance(int i) {
        if (i == 0) {
            return 0.0d;
        }
        double d = (((double) i) * 1.0d) / BLUETOOTH_RSSI_POWER_COEF;
        if (d < 1.0d) {
            return Math.pow(d, 10.0d);
        }
        return Math.min((Math.pow(d, 7.7095d) * 0.89976d) + 0.111d, 20.0d);
    }
}
