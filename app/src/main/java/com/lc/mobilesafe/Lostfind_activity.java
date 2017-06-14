package com.lc.mobilesafe;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/06/03/0003.
 */

public class Lostfind_activity extends Activity {
    //WebView wb;
    private LocationManager lm;
    private TextView tv_phonenumber;
    protected SharedPreferences sp;
    private Handler handler;
    private Criteria criteria;
    private Mylocationlistenn mylocationlistenn;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 123);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
//        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_MOCK_LOCATION},123);
        mylocationlistenn = new Mylocationlistenn();
        criteria = new Criteria();
        lm = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = lm.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.requestLocationUpdates(provider, 30, 0,mylocationlistenn);
        tv_phonenumber = (TextView) findViewById(R.id.tv_phonenumber);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        showphonenumber();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("lostfind_isseted", true);
        editor.commit();
        Toast.makeText(Lostfind_activity.this, "设置成功!", Toast.LENGTH_SHORT).show();

//        wb= (WebView) findViewById(R.id.wb_view);
//        wb=new WebView(Lostfind_activity.this);
//        wb.setWebViewClient(new WebViewClient());
//        wb.loadUrl("http://m.bilibili.com/video/av2715877.html");
//        setContentView(wb);
    }

    private void showphonenumber() {
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                tv_phonenumber.setText(msg.obj + "");
            }
        };
        Message msg = new Message();
        msg.obj = sp.getString("phonenumber", "");
        handler.sendMessage(msg);
    }

    public void resetup(View view) {
        startActivity(new Intent(Lostfind_activity.this, Lostfind_set1_activity.class));
    }

    public void gpsseesee(View view) {
      Log.v("z",sp.getString("longgitude",null)) ;
        Log.v("z",sp.getString("latitude",null)) ;
        Log.v("z", sp.getString("altitude",null)) ;
        Log.v("z",sp.getString("accuracy",null)) ;

    }

    class Mylocationlistenn implements LocationListener{

    @Override
    public void onLocationChanged(Location location) {
        String longgitude = "经度" + location.getLongitude();
        sp.edit().putString("longgitude", longgitude).commit();
        String latitude = "维度" + location.getLatitude();
        sp.edit().putString("latitude", latitude).commit();
        String altitude = "高度" + location.getAltitude();
        sp.edit().putString("altitude", altitude).commit();
        String accuracy = "精准度" + location.getAccuracy();
        sp.edit().putString("accuracy", accuracy).commit();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

}
