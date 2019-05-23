package com.app.beacon.M;

public interface IGetCurrentLocationListener {
    void getSuccess(String jsonstr);
    void getFailed(String res);
}
