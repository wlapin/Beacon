package com.app.beacon;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.app.beacon.M.Bean.OAuthBean;
import com.app.beacon.P.CreatePersenter;
import com.app.beacon.P.InstancePersenter;
import com.app.beacon.P.LoginPersenter;
import com.app.beacon.V.LoginView;
import com.google.gson.Gson;
import com.mingle.widget.ShapeLoadingDialog;
import com.uriio.beacons.Beacons;
import com.uriio.beacons.model.Beacon;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private ShapeLoadingDialog shapeLoadingDialog;
    private LoginPersenter loginPersenter;
    private InstancePersenter instancePersenter;
    private CreatePersenter createPersenter;
    private EditText username,password;
    private OAuthBean bean;
    private Button login;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Beacons.initialize(this);
        shapeLoadingDialog = new ShapeLoadingDialog(this);
        loginPersenter = new LoginPersenter(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        checkPermission();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://app.senseagent.com/oauth/v2/token?grant_type=password&client_id=5ca28f6c9a2f740ba7192df1_50c4d3p98eoscw08o8g8wgsgs4gswkw4kg8cow400484ssks8s&client_secret=51ri7gab0q4ogk4ccgg4w488840c444g8sg0owwok4k8cgg4gw&username="+username.getText().toString()+"&password="+password.getText().toString();
                //String url = "https://app.senseagent.com/oauth/v2/token?grant_type=password&client_id=5ca28f6c9a2f740ba7192df1_50c4d3p98eoscw08o8g8wgsgs4gswkw4kg8cow400484ssks8s&client_secret=51ri7gab0q4ogk4ccgg4w488840c444g8sg0owwok4k8cgg4gw&username=tracking.app@senseagent.com&password=Wayf!nd7890";
                Log.v("msgmsgurl",url);
                loginPersenter.Login(url);
            }
        });
    }
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private void checkPermission(){
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if((checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)) permissionsList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            if (permissionsList.size() != 0){
                    requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                            REQUEST_PHONE_PERMISSIONS);
            }else{
                checkBleDevice();
//                startAnimation();
//                com.example.nan.jobapp2.testDemo.rtcIM.serverAPI.InterfaceUrls.demoLogin(MLOC.userId);
//                loginPublicTest();
            }
        }else{
//            InterfaceUrls.demoLogin(MLOC.userId);
//            startAnimation();
//            com.example.nan.jobapp2.testDemo.rtcIM.serverAPI.InterfaceUrls.demoLogin(MLOC.userId);
//            loginPublicTest();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  final String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        checkBleDevice();
                    }
                });

                break;
        }
    }
    private void checkBleDevice() {
        //First get BluetoothManager
        BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        //get BluetoothAdapter
        if (bluetoothManager != null) {
            BluetoothAdapter mBluetoothAdapter = bluetoothManager.getAdapter();
            if (mBluetoothAdapter != null) {
                if (!mBluetoothAdapter.isEnabled()) {
                    //Call enable () method to open Bluetooth directly
                    if (!mBluetoothAdapter.enable()) {
                        Log.i("tag", "Bluetooth failed to open");
                    } else {
                        Log.i("tag", "Bluetooth is on");
                    }
                    //This method can also open Bluetooth, but there will be a very ugly bullet window, you can try it on your own.

//                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                    enableBtIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(enableBtIntent);
                }
            } else {
                Log.i("tag", "Agree to apply");
            }
        }
    }
    @Override
    public void onDataRes(String jsonstr) {
        Gson gson = new Gson();
        bean = gson.fromJson(jsonstr,OAuthBean.class);
        Log.v("msgmsg",jsonstr);
        shapeLoadingDialog.dismiss();
        if(bean.getAccess_token()==null||bean.getAccess_token().trim().length()==0){
            Toast.makeText(LoginActivity.this,"Authentication failed.",Toast.LENGTH_LONG).show();
        }else{
            Log.v("msgmsg", String.valueOf(bean));
            instancePersenter = new InstancePersenter(this);
            String url = "https://app.senseagent.com/api/v1/tracking/user/beacon/instanceid?access_token="+bean.getAccess_token();
            instancePersenter.getInstanceId(url);
            //gonext
        }

    }

    @Override
    public void FailedRes(String res){
        Toast.makeText(LoginActivity.this,"Load error.",Toast.LENGTH_LONG).show();
        shapeLoadingDialog.dismiss();

    }

    @Override
    public void InstanceidRes(String id) {
        Log.v("msgmsg",id);
        shapeLoadingDialog.dismiss();
        createPersenter = new CreatePersenter(this);
        String UID = "016E6F7264696373656D"+id;
        createPersenter.create(UID);
    }



    @Override
    public void BeaconListRes(List<Beacon> beaconList) {
        shapeLoadingDialog.dismiss();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        intent.putExtra("oauthbean",bean);
        intent.putExtra("email",username.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void startProgress(String str){
        shapeLoadingDialog.setLoadingText(str);
        shapeLoadingDialog.show();
    }

    @Override
    public void HiddenProgress(){

    }

}
