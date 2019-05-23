package com.app.beacon.M;

public interface ILoginListener {
    void OnSuccess(String jsonstr);
    void OnFailed(String res);
}
