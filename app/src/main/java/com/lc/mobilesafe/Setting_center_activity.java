package com.lc.mobilesafe;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.lc.mobilesafe.com.lc.uis.Textview_checkbox_view;


public class Setting_center_activity extends Activity {
    private Textview_checkbox_view textview_checkbox_view1;
    private Textview_checkbox_view textview_checkbox_view2;
    public static SharedPreferences sp;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_center);


        sp=getSharedPreferences("config",MODE_PRIVATE);

       Boolean update= sp.getBoolean("update",true);
        Boolean update2= sp.getBoolean("update2",true);

        textview_checkbox_view1= (Textview_checkbox_view) findViewById(R.id.tcv_autoupdate);
        textview_checkbox_view2= (Textview_checkbox_view) findViewById(R.id.tcv_autoupdate2);
        if (update){
            textview_checkbox_view1.setChecked(true);
           textview_checkbox_view1.setText("自动更新已打开");
        }else {
            textview_checkbox_view1.setChecked(false);
           textview_checkbox_view1.setText("自动更新已关闭");
        }

        textview_checkbox_view1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                if(textview_checkbox_view1.isChecked()){
                    textview_checkbox_view1.setChecked(false);
                   textview_checkbox_view1.setText("自动更新已关闭");
                    editor.putBoolean("update",false);

                }else{
                    textview_checkbox_view1.setChecked(true);
                    textview_checkbox_view1.setText("自动更新已打开");
                    editor.putBoolean("update",true);
                }
                editor.commit();
            }
        });
        if (update2){
            textview_checkbox_view2.setChecked(true);
            textview_checkbox_view2.setText("自动更新已打开");
        }else {
            textview_checkbox_view2.setChecked(false);
           textview_checkbox_view2.setText("自动更新已关闭");
        }
        textview_checkbox_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                if(textview_checkbox_view2.isChecked()){
                    textview_checkbox_view2.setChecked(false);
                    textview_checkbox_view2.setText("自动更新已关闭");
                    editor.putBoolean("update2",false);
                }else{
                    textview_checkbox_view2.setChecked(true);
                   textview_checkbox_view2.setText("自动更新已打开");
                    editor.putBoolean("update2",true);
                }editor.commit();
            }
        });
    }
}
