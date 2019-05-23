package com.app.beacon.M;

import com.app.beacon.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class InstanceModel implements IInstanceModel{

    @Override
    public void getInstanceId(final String url, final IInstanceListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtil.sendRequestWithOkhttp(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        listener.InstanceFailed(".Failed to request InstanceId.");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonstr = response.body().string();
                        listener.InstanceCallBack(jsonstr);
                    }
                });
            }
        }).start();
    }
}
