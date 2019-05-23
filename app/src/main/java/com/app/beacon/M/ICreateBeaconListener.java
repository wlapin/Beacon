package com.app.beacon.M;

import com.uriio.beacons.model.Beacon;

import java.util.List;

public interface ICreateBeaconListener {
    void createSuccess(String state,List<Beacon> beaconList);
    void createFailed(String res);
}
