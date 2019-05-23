package com.app.beacon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.beacon.M.Bean.OAuthBean;
import com.app.beacon.M.Bean.measurementBean;
import com.app.beacon.M.Bean.userBean;
import com.app.beacon.P.GetCurrentLocationPresenter;
import com.app.beacon.P.GetSVGPersenter;
import com.app.beacon.P.GetUserInfoPersenter;
import com.app.beacon.V.IMainView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.gson.Gson;
import com.itheima.library.PhotoView;
import com.mingle.widget.ShapeLoadingDialog;
import com.pixplicity.sharp.Sharp;

public class MainActivity extends AppCompatActivity implements IMainView, View.OnClickListener {
    private GetUserInfoPersenter getUserInfoPersenter;
    private GetSVGPersenter getSVGPersenter;
    private GetCurrentLocationPresenter getCurrentLocationPresenter;
    private ImageView open;
    private PhotoView view;
    private DrawerLayout drawerLayout;
    private ShapeLoadingDialog shapeLoadingDialog;
    private OAuthBean bean;
    private String email;
    private Button bt_user,bt_openothers,bt_closeothers,logout;
    private TextView tv_email,tv_Device,tv_Category,tv_Client,about;
    private userBean userbean;
    private measurementBean measurementbean;
    private StringBuffer SVGBUFFER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        bean = (OAuthBean) getIntent().getSerializableExtra("oauthbean");
        email = getIntent().getStringExtra("email");
        shapeLoadingDialog = new ShapeLoadingDialog(this);
        getUserInfoPersenter = new GetUserInfoPersenter(this);
        initView();
        String url = "https://app.senseagent.com/api/v1/tracking/user/beacon/details?access_token="+bean.getAccess_token();
        getUserInfoPersenter.getUserInfo(url);
    }
    private void initView() {
        open = findViewById(R.id.open);
        bt_user = findViewById(R.id.user);
        bt_openothers = findViewById(R.id.other_open);
        bt_closeothers = findViewById(R.id.other_close);
        tv_email = findViewById(R.id.email);
        tv_Device = findViewById(R.id.Device);
        tv_Category = findViewById(R.id.Category);
        tv_Client = findViewById(R.id.Client);
        drawerLayout = findViewById(R.id.drawer);
        view = findViewById(R.id.view);
        about = findViewById(R.id.about);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        open.setOnClickListener(this);
        bt_user.setOnClickListener(this);
        bt_openothers.setOnClickListener(this);
        bt_closeothers.setOnClickListener(this);
        about.setOnClickListener(this);
        view.enable();
    }

    @Override
    public void getUserSuccessView(String jsonstr) {
        Log.v("msgmsgdata",jsonstr);
        Gson gson = new Gson();
        userbean = gson.fromJson(jsonstr,userBean.class);
        Log.v("msgmsgbean",userbean.toString());
        tv_email.setText(email);
        tv_Device.setText(userbean.getDevice());
        tv_Category.setText(userbean.getCategory());
        tv_Client.setText(userbean.getClient());
        shapeLoadingDialog.dismiss();
        getSVGPersenter = new GetSVGPersenter(this);
        getSVGPersenter.getSvg("https://app.senseagent.com/api/v1/floorplan/download/image/7?access_token="+bean.getAccess_token());
    }

    @Override
    public void getUserFailedView(String res) {
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void getSVGSuccessView(String svgstr) {
        Log.v("msgmsgres",svgstr);
        SVGBUFFER = new StringBuffer(svgstr);
        Sharp.loadString(svgstr).into(view);
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void getSVGFailedView(String url) {
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void getCurrentLocationSuccessView(String jsonstr){
        Log.v("msgmsgjson",jsonstr);
        Gson gson = new Gson();
        measurementbean = gson.fromJson(jsonstr,measurementBean.class);
        //Work out ratio of pixels per centimetres
        double ratio = measurementbean.getScale().getPixels()/measurementbean.getScale().getCentimeters();
        // Computation Xcm Ycm
        double X = userbean.getLastSeen().getLocation().getFloorPlan().getPosition().getX()*100.0*ratio;
        double Y = userbean.getLastSeen().getLocation().getFloorPlan().getPosition().getY()*100.0*ratio;
        Log.v("msgmsgxy",X+" "+Y);
        StringBuffer buffer = SVGBUFFER;
        int index = buffer.indexOf("</svg>");
        buffer.insert(index,"<circle cx=\""+X+"\" cy=\""+Y+"\" r=\"17\" fill=\"blue\"></circle>");
        Sharp.loadString(buffer.toString()).into(view);
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void getCurrentLocationFailedView(String res){
        Toast.makeText(MainActivity.this,"Load error",Toast.LENGTH_LONG).show();
        shapeLoadingDialog.dismiss();
    }

    @Override
    public void startProgress(String str) {
        shapeLoadingDialog.setLoadingText(str);
        shapeLoadingDialog.show();
    }

    @Override
    public void HiddenProgress() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.open:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.user:
                getCurrentLocationPresenter = new GetCurrentLocationPresenter(this);
                getCurrentLocationPresenter.getCurrentLocation("https://app.senseagent.com/api/v1/floorplan/scale/measurement/get/21?access_token="+bean.getAccess_token());
                break;
            case R.id.other_open:
                bt_openothers.setVisibility(View.GONE);
                bt_closeothers.setVisibility(View.VISIBLE);
                break;
            case R.id.other_close:
                bt_closeothers.setVisibility(View.GONE);
                bt_openothers.setVisibility(View.VISIBLE);
                break;
            case R.id.about:
                Intent goaabout = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(goaabout);
                break;
            case R.id.logout:
                finish();
                break;
        }
    }
}
