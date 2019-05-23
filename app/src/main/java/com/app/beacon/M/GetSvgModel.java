package com.app.beacon.M;

import android.os.Environment;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.app.beacon.Util.HttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GetSvgModel implements IGetSVGModel{
    @Override
    public void getSvg(final String url,final IGetSVGListener listener) {
        HttpUtil.sendRequestWithOkhttp(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                listener.getFailed("Network error, load failure.");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String svgstr = response.body().string();
                listener.getSuccess(svgstr);
            }
        });
    }

}
