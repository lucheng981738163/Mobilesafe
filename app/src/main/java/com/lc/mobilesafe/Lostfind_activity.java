package com.lc.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/06/03/0003.
 */

public class Lostfind_activity extends Activity {
//WebView wb;
   private TextView tv_phonenumber;
    protected   SharedPreferences sp;
    private Handler handler;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind);
        tv_phonenumber= (TextView) findViewById(R.id.tv_phonenumber);
        sp=getSharedPreferences("config",MODE_PRIVATE);
        showphonenumber();
        SharedPreferences.Editor editor=sp.edit();
        editor .putBoolean("lostfind_isseted",true);
        editor.commit();
        Toast.makeText(Lostfind_activity.this,"设置成功!",Toast.LENGTH_SHORT).show();

//        wb= (WebView) findViewById(R.id.wb_view);
//        wb=new WebView(Lostfind_activity.this);
//        wb.setWebViewClient(new WebViewClient());
//        wb.loadUrl("http://m.bilibili.com/video/av2715877.html");
//        setContentView(wb);
    }

    private void showphonenumber() {
        handler=   new Handler(){
            @Override
            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
                tv_phonenumber.setText(msg.obj+"");
            }
        };
        Message msg=new Message();
        msg.obj= sp.getString("phonenumber","");
        handler.sendMessage(msg);
    }

    public void resetup(View view) {
        startActivity(new Intent(Lostfind_activity.this,Lostfind_set1_activity.class));
    }


}
