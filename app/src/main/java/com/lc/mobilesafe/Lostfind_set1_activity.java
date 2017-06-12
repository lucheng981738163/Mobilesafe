package com.lc.mobilesafe;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;

import android.view.MotionEvent;
import android.view.View;

import com.lc.mobilesafe.com.lc.uis.pre_next_star_view;

/**
 * Created by Administrator on 2017/06/01/0001.
 */

public class Lostfind_set1_activity extends Activity {
    private com.lc.mobilesafe.com.lc.uis.pre_next_star_view star1;
    private  GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind_set1);
        star1 = (pre_next_star_view) findViewById(R.id.star1);
        star1.setpage(1);
        gestureDetector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(e1.getRawX()>e2.getRawX()){
                    bt_setup_next(null);
                    return true;
                }
                if (e1.getRawX()<e2.getRawX()){
                    bt_setup_pre(null);
                    return true;
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

    }

    public void bt_setup_pre(View view) {
        startActivity(new Intent(this, Home_activity.class));
        finish();
        overridePendingTransition(R.anim.pre_in,R.anim.pre_out);
    }

    public void bt_setup_next(View view){
        startActivity(new Intent(this, Lostfind_set2_activity.class));
        finish();
       overridePendingTransition(R.anim.next_in,R.anim.next_out);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
