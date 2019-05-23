package com.app.beacon.P;
import android.os.Handler;
import com.app.beacon.M.GetCurrentLocationModel;
import com.app.beacon.M.IGetCurrentLocationListener;
import com.app.beacon.V.IMainView;

public class GetCurrentLocationPresenter {
    private IMainView view;
    private GetCurrentLocationModel model;
    private Handler handler = new Handler();
    public GetCurrentLocationPresenter(IMainView view){
        this.view = view;
        this.model = new GetCurrentLocationModel();
    }
    public void getCurrentLocation(final String url){
        view.startProgress("Load your own current location...");
        this.model.getCurrentLocation(url, new IGetCurrentLocationListener() {
            @Override
            public void getSuccess(final String jsonstr) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.getCurrentLocationSuccessView(jsonstr);
                    }
                });
            }
            @Override
            public void getFailed(final String res) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.getCurrentLocationFailedView(res);
                    }
                });
            }
        });
    }
}
