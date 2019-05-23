package com.app.beacon.P;

import android.os.Handler;
import android.view.View;

import com.app.beacon.M.ILoginListener;
import com.app.beacon.M.LoginModel;
import com.app.beacon.V.LoginView;



public class LoginPersenter {
    private LoginView view;
    private LoginModel model;
    private Handler handler = new Handler();
    public LoginPersenter(LoginView view){
        this.view = view;
        this.model = new LoginModel();
    }
    public void Login(String url){
        view.startProgress("In the process of certification...");
        model.Login(url, new ILoginListener() {
            @Override
            public void OnSuccess(final String jsonstr) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.onDataRes(jsonstr);
                    }
                });
            }

            @Override
            public void OnFailed(final String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.FailedRes(res);
                    }
                });
            }
        });
    }
}
