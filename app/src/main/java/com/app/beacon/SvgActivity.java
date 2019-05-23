package com.app.beacon;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.app.beacon.P.GetSVGPersenter;
import com.app.beacon.V.IMainView;
import com.itheima.library.PhotoView;
import com.mingle.widget.ShapeLoadingDialog;
import com.pixplicity.sharp.Sharp;

public class SvgActivity extends AppCompatActivity implements IMainView{
    private PhotoView view;
    private GetSVGPersenter getSVGPersenter;
    private ShapeLoadingDialog shapeLoadingDialog;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        view = findViewById(R.id.view);
        view.enable();
        Sharp.loadAsset(getAssets(),"21.svg").into(view);
        //getSVGPersenter = new GetSVGPersenter(this);
        //shapeLoadingDialog = new ShapeLoadingDialog(this);
        //getSVGPersenter.getSvg("https://app.senseagent.com/api/v1/floorplan/download/image/21?access_token=NmJkNzM3ZjY5MWIzY2U3NzFmMGEwMmEwZWY5ODBmMGM4NWYzM2Q5ZGFiNGNmNTI2YTJmNWNlOTk0YWQ3ODY2YQ");
    }
    @Override
    public void getUserSuccessView(String jsonstr) {

    }
    @Override
    public void getUserFailedView(String res) {

    }
    @Override
    public void getSVGSuccessView(String svgstr) {
        Log.v("msgmsgdata",svgstr);
        //final StringBuffer stringBuffer = new StringBuffer(svgstr);
        //int index = stringBuffer.indexOf("</svg>");
        //stringBuffer.insert(index,"<circle cx=\"425\" cy=\"60\" r=\"10\" fill=\"red\"></circle>");
        //Log.v("msgmsgbuff",stringBuffer.toString());
        //Sharp.loadString(svgstr).into(view);

        shapeLoadingDialog.dismiss();
    }
    @Override
    public void getSVGFailedView(String url) {
    }

    @Override
    public void getCurrentLocationSuccessView(String jsonstr) {

    }

    @Override
    public void getCurrentLocationFailedView(String res) {

    }

    @Override
    public void startProgress(String str) {
        shapeLoadingDialog.setLoadingText(str);
        shapeLoadingDialog.show();
    }
    @Override
    public void HiddenProgress() {
    }
}