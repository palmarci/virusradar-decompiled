package p008hu.gov.virusradar.Utilities.BluetoothUtils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.AdvertiseSettings.Builder;
import android.bluetooth.le.BluetoothLeAdvertiser;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import p008hu.gov.virusradar.Modules.MonitorServiceModels.AliasManager;
import p008hu.gov.virusradar.Utilities.Logger;
import p008hu.gov.virusradar.Utilities.Util;

/* renamed from: hu.gov.virusradar.Utilities.BluetoothUtils.BleAdvertisingTransmitter */
class BleAdvertisingTransmitter {
    private static AdvertiseCallback advertisingCallback;
    private static BleAdvertisingTransmitter instance;
    private BluetoothAdapter bleAdapter;
    private BluetoothLeAdvertiser bleAdvertiser;

    BleAdvertisingTransmitter() {
    }

    private static AdvertiseSettings createAdvSettings() {
        Builder builder = new Builder();
        builder.setAdvertiseMode(1);
        builder.setConnectable(false);
        builder.setTxPowerLevel(3);
        return builder.build();
    }

    private static AdvertiseData makeAdvertiseData() {
        byte[] mergeArrays = Util.mergeArrays(AdvertisingHelper.MESSAGE_PREFIX, AliasManager.getInstance().getAliasModel().alias.getBytes());
        AdvertiseData.Builder builder = new AdvertiseData.Builder();
        builder.addServiceUuid(AdvertisingHelper.GUUID);
        builder.addServiceData(AdvertisingHelper.DATAUUID, mergeArrays);
        return builder.build();
    }

    static void broadcastAdvert(BluetoothAdapter bluetoothAdapter) {
        if (instance == null) {
            instance = new BleAdvertisingTransmitter();
        }
        BleAdvertisingTransmitter bleAdvertisingTransmitter = instance;
        bleAdvertisingTransmitter.bleAdapter = bluetoothAdapter;
        bleAdvertisingTransmitter.broadcastAdvert();
    }

    static void stopAdvert() {
        try {
            instance.bleAdvertiser.stopAdvertising(advertisingCallback);
        } catch (Exception unused) {
        }
    }

    private void broadcastAdvert() {
        setupAdvertiserCallback();
        new Thread(new Runnable() {
            public final void run() {
                BleAdvertisingTransmitter.this.lambda$broadcastAdvert$0$BleAdvertisingTransmitter();
            }
        }).start();
    }

    public /* synthetic */ void lambda$broadcastAdvert$0$BleAdvertisingTransmitter() {
        BluetoothAdapter bluetoothAdapter = this.bleAdapter;
        if (bluetoothAdapter != null) {
            if (this.bleAdvertiser == null) {
                this.bleAdvertiser = getAdvertiser(bluetoothAdapter);
            }
            try {
                this.bleAdvertiser.startAdvertising(createAdvSettings(), makeAdvertiseData(), advertisingCallback);
            } catch (Exception unused) {
            }
        }
    }

    private void setupAdvertiserCallback() {
        if (advertisingCallback == null) {
            advertisingCallback = new AdvertiseCallback() {
                public void onStartSuccess(AdvertiseSettings advertiseSettings) {
                    Logger.log("ADVERTISER_STARTED");
                }

                public void onStartFailure(int i) {
                    Logger.log("ADVERTISER_FAILED");
                }
            };
        }
    }

    private BluetoothLeAdvertiser getAdvertiser(BluetoothAdapter bluetoothAdapter) {
        try {
            if (bluetoothAdapter.getBluetoothLeAdvertiser() != null) {
                return bluetoothAdapter.getBluetoothLeAdvertiser();
            }
            Class cls = bluetoothAdapter.getClass();
            Field declaredField = cls.getDeclaredField("sBluetoothLeAdvertiser");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(bluetoothAdapter);
            if (obj == null) {
                Field declaredField2 = cls.getDeclaredField("mManagerService");
                declaredField2.setAccessible(true);
                Object obj2 = declaredField2.get(bluetoothAdapter);
                Constructor declaredConstructor = BluetoothLeAdvertiser.class.getDeclaredConstructor(new Class[]{declaredField2.getType()});
                declaredConstructor.setAccessible(true);
                obj = declaredConstructor.newInstance(new Object[]{obj2});
                declaredField.set(bluetoothAdapter, obj);
            }
            return (BluetoothLeAdvertiser) obj;
        } catch (Exception unused) {
            return null;
        }
    }
}
