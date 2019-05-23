package com.app.beacon.M;

import android.bluetooth.le.AdvertiseSettings;
import com.uriio.beacons.Beacons;
import com.uriio.beacons.model.Beacon;
import com.uriio.beacons.model.EddystoneUID;
import java.util.List;
public class CreateBeaconModel implements ICreateBeaconModel{
    @Override
    public void create(final String UID, final ICreateBeaconListener listener) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                byte bytes[] = UID.getBytes();
                new EddystoneUID(bytes, AdvertiseSettings.ADVERTISE_MODE_BALANCED, AdvertiseSettings.ADVERTISE_TX_POWER_LOW)
                        .start();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Beacon.fromCursor().getActiveState();
                List<Beacon> beacons = Beacons.getActive();
                listener.createSuccess("1",beacons);
            }
        }.start();
    }
}
