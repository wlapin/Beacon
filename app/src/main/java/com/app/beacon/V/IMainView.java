package com.app.beacon.V;

public interface IMainView extends IBaseView{
    void getUserSuccessView(String jsonstr);
    void getUserFailedView(String res);
    void getSVGSuccessView(String svgstr);
    void getSVGFailedView(String url);
    void getCurrentLocationSuccessView(String jsonstr);
    void getCurrentLocationFailedView(String res);
}
