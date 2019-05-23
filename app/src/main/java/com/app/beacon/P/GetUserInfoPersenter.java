package com.app.beacon.P;

import android.os.Handler;

import com.app.beacon.M.GetUserInfoModel;
import com.app.beacon.M.IGetUserInfoListener;
import com.app.beacon.M.IGetUserInfoModel;
import com.app.beacon.V.IMainView;


public class GetUserInfoPersenter {
    private IMainView view;
    private GetUserInfoModel model;
    private Handler handler = new Handler();
    public GetUserInfoPersenter(IMainView view){
        this.view = view;
        model = new GetUserInfoModel();
    }
    public void getUserInfo(String url){
        view.startProgress("Getting User Personal Information...");
        this.model.getUser(url, new IGetUserInfoListener() {
            @Override
            public void getSuccess(final String jsonstr) {
                handler.post(new Runnable() {
                    @Override
                    public void run(){
                        view.getUserSuccessView(jsonstr);
                    }
                });
            }
            @Override
            public void getFailed(final String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.getUserFailedView(res);
                    }
                });
            }
        });
    }
}
