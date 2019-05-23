package com.app.beacon.P;

import android.os.Handler;
import android.webkit.WebView;

import com.app.beacon.M.GetSvgModel;
import com.app.beacon.M.IGetSVGListener;
import com.app.beacon.M.IGetSVGModel;
import com.app.beacon.V.IMainView;
public class GetSVGPersenter {
    private IMainView view;
    private GetSvgModel model;
    private Handler handler = new Handler();
    public GetSVGPersenter(IMainView view){
        this.view = view;
        model = new GetSvgModel();
    }
    public void getSvg(String url){
        view.startProgress("Load the SVG diagram, please wait a moment....");
        this.model.getSvg(url,new IGetSVGListener() {
            @Override
            public void getSuccess(final String response){
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                              view.getSVGSuccessView(response);
                    }
                });
            }
            @Override
            public void getFailed(final String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.getSVGFailedView(res);
                    }
                });
            }
        });
    }
}
