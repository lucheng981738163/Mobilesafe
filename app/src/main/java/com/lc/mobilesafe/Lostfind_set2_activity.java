package com.lc.mobilesafe;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.lc.mobilesafe.com.lc.uis.Textview_checkbox_view;
import com.lc.mobilesafe.com.lc.uis.pre_next_star_view;

/**
 * Created by Administrator on 2017/06/01/0001.
 */

public class Lostfind_set2_activity extends Activity {
    private pre_next_star_view star2;
    private GestureDetector gestureDetector;
    private Textview_checkbox_view sim_view;
    private SharedPreferences sp;
    private TelephonyManager tm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind_set2);
//        int checkCallPhonePermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},123);
        tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        sp = getSharedPreferences("config", MODE_PRIVATE);
        sim_view = (Textview_checkbox_view) findViewById(R.id.sim_view);
        if (!sp.getBoolean("sim_binded", false)) {
            sim_view.setText("sim卡未绑定");
            sim_view.setChecked(false);
        } else {
            sim_view.setText("sim卡已绑定");
            sim_view.setChecked(true);
        }
        sim_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!sp.getBoolean("sim_binded", false)) {
                    String simserial = tm.getSimSerialNumber();

                    sp.edit().putBoolean("sim_binded", true).commit();
                    sp.edit().putString("simserial", simserial).commit();
                    sim_view.setText("sim卡已绑定");
                    sim_view.setChecked(true);
                } else {
                    sp.edit().putBoolean("sim_binded", false).commit();
                    sim_view.setText("sim卡未绑定");
                    sim_view.setChecked(false);
                }
            }
        });
        star2 = (pre_next_star_view) findViewById(R.id.star2);
        star2.setpage(2);
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e1.getRawX() > e2.getRawX()) {
                    bt_setup_next(null);
                    return true;
                }
                if (e1.getRawX() < e2.getRawX()) {
                    bt_setup_pre(null);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    public void bt_setup_pre(View view) {
        startActivity(new Intent(this, Lostfind_set1_activity.class));
        finish();
        overridePendingTransition(R.anim.pre_in, R.anim.pre_out);
    }

    public void bt_setup_next(View view) {
        if (!sp.getBoolean("sim_binded", false)){
            Toast.makeText(this,"绑定sim卡后,才能开启下一功能",Toast.LENGTH_SHORT).show();
            return;

        }
        startActivity(new Intent(this, Lostfind_set3_activity.class));
        finish();
        overridePendingTransition(R.anim.next_in, R.anim.next_out);
    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
