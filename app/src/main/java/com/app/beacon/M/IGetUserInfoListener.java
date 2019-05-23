package com.app.beacon.M;

public interface IGetUserInfoListener {
    void getSuccess(String jsonstr);
    void getFailed(String res);
}
