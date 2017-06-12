package com.lc.mobilesafe.com.lc.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;


public class listen_startos_receive extends BroadcastReceiver {
   private SharedPreferences sp;
    private TelephonyManager tm;

    public void onReceive(Context context, Intent intent) {
        tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        String lastsimserial = sp.getString("simserial", null) ;
        String nowsimserial = tm.getSimSerialNumber();
        if (lastsimserial.equals(nowsimserial)) {
            Log.v("log", "欢迎回来！");
            Toast.makeText(context, "欢迎回来！", Toast.LENGTH_LONG).show();

        } else {
            Log.v("log", "你的手机可能被盗");
            Toast.makeText(context, "你的手机可能被盗", Toast.LENGTH_LONG).show();
        }
    }
}
