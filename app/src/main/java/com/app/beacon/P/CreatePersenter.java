package com.app.beacon.P;

import android.os.Handler;

import com.app.beacon.M.CreateBeaconModel;
import com.app.beacon.M.ICreateBeaconListener;
import com.app.beacon.V.LoginView;

import java.util.List;


public class CreatePersenter {
    private LoginView view;
    private CreateBeaconModel model;
    private Handler handler = new Handler();
    public CreatePersenter(LoginView view){
        this.view = view;
        this.model = new CreateBeaconModel();
    }
    public void create(String UID){
        this.model.create(UID, new ICreateBeaconListener() {
            @Override
            public void createSuccess(String state, final List beaconList) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.BeaconListRes(beaconList);
                    }
                });

            }
            @Override
            public void createFailed(String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.FailedRes("Create failure");
                    }
                });
            }
        });
    }
}
