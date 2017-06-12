package com.lc.mobilesafe;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.lc.mobilesafe.com.lc.uis.pre_next_star_view;

/**
 * Created by Administrator on 2017/06/01/0001.
 */

public class Lostfind_set4_activity extends Activity {
    private pre_next_star_view star4;
private GestureDetector gestureDetector;
private SharedPreferences sp;
    private TextView tv_protect_on_off;
    private Drawable drawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind_set4);
        tv_protect_on_off= (TextView) findViewById(R.id.tv_protect_on_off);
        tv_protect_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sp.getBoolean("protected",false)){
                    sp.edit().putBoolean("protected",true).commit();
                    drawable=getResources().getDrawable(android.R.drawable.checkbox_on_background);
                    drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_protect_on_off.setCompoundDrawables(drawable,null,null,null);
                }else{
                    sp.edit().putBoolean("protected",false).commit();
                    drawable=getResources().getDrawable(android.R.drawable.checkbox_off_background);
                    drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv_protect_on_off.setCompoundDrawables(drawable,null,null,null);
                }
            }
        });
        sp=getSharedPreferences("config",MODE_PRIVATE);
        if(!sp.getBoolean("protected",false)){
            drawable=getResources().getDrawable(android.R.drawable.checkbox_off_background);
            drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_protect_on_off.setCompoundDrawables(drawable,null,null,null);
        }else{
            drawable=getResources().getDrawable(android.R.drawable.checkbox_on_background);
            drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_protect_on_off.setCompoundDrawables(drawable,null,null,null);
        }
        star4 = (pre_next_star_view) findViewById(R.id.star4);
        star4.setpage(4);
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
        startActivity(new Intent(this, Lostfind_set3_activity.class));
        finish();
        overridePendingTransition(R.anim.pre_in,R.anim.pre_out);
    }
    public void bt_setup_next(View view){
        startActivity(new Intent(this, Lostfind_activity.class));
        finish();
        overridePendingTransition(R.anim.next_in,R.anim.next_out);
    }
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


}
