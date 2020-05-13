package p008hu.gov.virusradar.Utilities.BluetoothUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.ParcelUuid;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.DeviceListModel;

/* renamed from: hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper */
public class AdvertisingHelper {
    static final ParcelUuid DATAUUID = ParcelUuid.fromString("0000ffaa-0000-1000-8000-00805F9B34FB");
    static final ParcelUuid GUUID = ParcelUuid.fromString("0000ff01-0000-1000-8000-00805F9B34FB");
    static final ParcelUuid MASKUUID = ParcelUuid.fromString("FFFFFFFF-FFFF-FFFF-FFFF-FFFFFFFFFFFF");
    static final byte[] MESSAGE_POSTFIX = {42};
    static final byte[] MESSAGE_PREFIX = {126};
    private BluetoothAdapter bleAdapter;
    private IAdvertisementListener iAdvertisementListener;

    /* renamed from: hu.gov.virusradar.Utilities.BluetoothUtils.AdvertisingHelper$IAdvertisementListener */
    public interface IAdvertisementListener {
        void onDeviceListUpdate(DeviceListModel deviceListModel);
    }

    public AdvertisingHelper(Context context, IAdvertisementListener iAdvertisementListener2) {
        BluetoothManager bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth");
        this.iAdvertisementListener = iAdvertisementListener2;
        if (bluetoothManager != null) {
            this.bleAdapter = bluetoothManager.getAdapter();
        }
        if (this.bleAdapter == null) {
            this.bleAdapter = BluetoothAdapter.getDefaultAdapter();
        }
    }

    public void activate() {
        if (isBluetoothOn()) {
            BluetoothAdapter bluetoothAdapter = this.bleAdapter;
            if (bluetoothAdapter != null) {
                BleAdvertisingReceiver.initiateScan(bluetoothAdapter, this.iAdvertisementListener);
                BleAdvertisingTransmitter.broadcastAdvert(this.bleAdapter);
            }
        }
    }

    public void deactivate() {
        if (isBluetoothOn()) {
            BleAdvertisingReceiver.disableScan();
            BleAdvertisingTransmitter.stopAdvert();
        }
    }

    public void checkAndRenewAlias() {
        if (AliasManager.getInstance().isCurrentAliasExpired()) {
            BleAdvertisingTransmitter.stopAdvert();
            BleAdvertisingTransmitter.broadcastAdvert(this.bleAdapter);
        }
    }

    public static boolean isBluetoothOn() {
        try {
            return BluetoothAdapter.getDefaultAdapter().isEnabled() && BluetoothAdapter.getDefaultAdapter().getState() == 12;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void clearModels() {
        DeviceListModel.getInstance().deleteAll();
    }

    public static double calculateDistance(int i) {
        return BleAdvertisingReceiver.calculateDistance(i);
    }

    public static String extractMessageFromRecord(ScanResult scanResult) {
        return BleAdvertisingReceiver.extractMessageFromRecord(scanResult);
    }

    public static boolean isResponseValid(ScanResult scanResult) {
        return BleAdvertisingReceiver.isResponseValid(scanResult);
    }
}
