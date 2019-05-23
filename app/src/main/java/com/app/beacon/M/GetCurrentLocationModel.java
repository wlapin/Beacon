package com.app.beacon.M;

import com.app.beacon.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetCurrentLocationModel implements IGetCurrentLocationModel{

    @Override
    public void getCurrentLocation(final String url, final IGetCurrentLocationListener listener) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                HttpUtil.sendRequestWithOkhttp(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.getFailed("Load error");
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
