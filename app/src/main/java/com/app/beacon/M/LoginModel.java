package com.app.beacon.M;

import com.app.beacon.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class LoginModel implements ILoginModel{

    @Override
    public void Login(final String url, final ILoginListener loginListener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendRequestWithOkhttp(url, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        loginListener.OnFailed(".request was aborted.");
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonstr = response.body().string();
                        loginListener.OnSuccess(jsonstr);
                    }
                });
            }
        }).start();

    }
}
