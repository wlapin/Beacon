package com.app.beacon.M;

import android.util.Log;

import com.app.beacon.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetUserInfoModel implements IGetUserInfoModel{
    @Override
    public void getUser(final String url, final IGetUserInfoListener listener) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpUtil.sendRequestWithOkhttp(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.getFailed("Request error, check network");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonstr = response.body().string();
                        listener.getSuccess(jsonstr);
                    }
                });
            }
        }.start();
    }
}
