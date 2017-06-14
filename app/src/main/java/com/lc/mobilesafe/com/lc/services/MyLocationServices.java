package com.lc.mobilesafe.com.lc.services;


import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;


/**
 * Created by Administrator on 2017/06/14/0014.
 */

public class MyLocationServices extends Service {
    private LocationManager lm;
    private Mylocationlistenn mylocationlistenn;
    private Criteria criteria;
    private SharedPreferences sp;
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        sp=getSharedPreferences("config",MODE_PRIVATE);
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
    }
    class Mylocationlistenn implements LocationListener {

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
