package com.app.beacon.V;

import com.uriio.beacons.model.Beacon;

import java.util.List;

public interface LoginView extends IBaseView {
    void onDataRes(String jsonstr); //return OAuthjson
    void FailedRes(String res);
    void InstanceidRes(String id); // return InstanceidRes
    void BeaconListRes(List<Beacon> beaconList);
}
