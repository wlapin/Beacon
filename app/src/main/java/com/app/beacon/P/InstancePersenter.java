package com.app.beacon.P;

import android.os.Handler;

import com.app.beacon.M.IInstanceListener;
import com.app.beacon.M.InstanceModel;
import com.app.beacon.V.LoginView;

public class InstancePersenter {
    private LoginView view;
    private InstanceModel model;
    private Handler handler = new Handler();
    public InstancePersenter(LoginView view){
        this.view = view;
        this.model = new InstanceModel();
    }
    public void getInstanceId(String url){
        view.startProgress("Being acquired InstanceId...");
        this.model.getInstanceId(url, new IInstanceListener() {
            @Override
            public void InstanceCallBack(final String id) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.InstanceidRes(id);
                        view.HiddenProgress();
                    }
                });
            }

            @Override
            public void InstanceFailed(String res) {
                view.FailedRes(res);
            }
        });
    }
}
