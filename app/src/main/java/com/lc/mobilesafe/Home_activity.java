package com.lc.mobilesafe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class Home_activity extends Activity {

    private long time;
    private GridView listview;
    private String[] names = {"手机防盗", "通讯卫士", "软件管理", "进程管理", "流量统计", "手机杀毒", "缓存清理", "高级工具", "设置中心"};
    private int[] ids = {R.drawable.safe, R.drawable.callmsgsafe, R.drawable.app, R.drawable.taskmanager, R.drawable.netmanager, R.drawable.trojan,
            R.drawable.sysoptimize, R.drawable.atools, R.drawable.settings, R.drawable.trojan
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
       // backgroundmusic();

        Myadapter adapter = new Myadapter();
        listview = (GridView) findViewById(R.id.gv_item);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        mobilefind();

                        break;
                    case 2:

                        break;
                    case 8:
                        Toast.makeText(Home_activity.this, "点击了设置中心", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Home_activity.this, Setting_center_activity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
        Toast.makeText(Home_activity.this, "开启APP时间为" + Qidongguanggao_activity.runtime + "毫秒" + ",封面广告持续" + Qidongguanggao_activity.chixumiaoshu + "毫秒", Toast.LENGTH_SHORT).show();

    }

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText et_setpw_first;
    EditText et_setpw_second;
    EditText et_pw_login;
    Button bt_setpw_enter;
    Button bt_setpw_cancel;
    Button bt_pw_enter;
    Button bt_pw_cancel;
    AlertDialog dialog;
    int retry = 1;

    private void mobilefind() {
        sp = Home_activity.this.getSharedPreferences("config", MODE_PRIVATE);
        editor = sp.edit();
        if (!sp.getBoolean("passworded", false)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Home_activity.this);
            View dialog_view = View.inflate(Home_activity.this, R.layout.setpw_dialog, null);
            et_setpw_first = (EditText) dialog_view.findViewById(R.id.et_setpw_first);
            et_setpw_second = (EditText) dialog_view.findViewById(R.id.et_setpw_second);
            bt_setpw_cancel = (Button) dialog_view.findViewById(R.id.bt_setpw_cancel);
            bt_setpw_enter = (Button) dialog_view.findViewById(R.id.bt_setpw_enter);
            dialog = builder.create();
            dialog.setView(dialog_view);
            dialog.show();

//                        builder.setView(R.layout.setpw_dialog);

            bt_setpw_enter.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (et_setpw_first.getText().toString().equals(et_setpw_second.getText().toString())) {
                        editor.putString("password", et_setpw_first.getText().toString());
                        editor.putBoolean("passworded", true);
                        editor.commit();
                        if (!sp.getBoolean("lostfind_isseted", false)) {
                            startActivity(new Intent(Home_activity.this, Lostfind_set1_activity.class));
                        } else {
                            startActivity(new Intent(Home_activity.this, Lostfind_activity.class));
                        }
                        dialog.dismiss();
                    } else if (et_setpw_first.getText().toString().equals("") || et_setpw_second.getText().toString().equals("")) {
                        et_setpw_first.setText("");
                        et_setpw_second.setText("");
                        editor.commit();
                        Toast.makeText(Home_activity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        et_setpw_first.setText("");
                        et_setpw_second.setText("");
                        editor.commit();
                        Toast.makeText(Home_activity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();

                    }
                }
            });
            bt_setpw_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
//            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    if(et_setpw_first.getText().toString().equals(et_setpw_second.getText().toString())){
//                    editor.putString("password",et_setpw_first.getText().toString())  ;
//               editor.putBoolean("passworded",true);
//            editor.commit();
//                    }
//                    else if (et_setpw_first.getText().toString().equals("")||et_setpw_second.getText().toString().equals("")){
//                        et_setpw_first.setText("");
//                        et_setpw_second.setText("");
//            editor.commit();
//                        Toast.makeText(Home_activity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        et_setpw_first.setText("");
//                        et_setpw_second.setText("");
//            editor.commit();
//                        Toast.makeText(Home_activity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
//
//                    }


//                }
//            });

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(Home_activity.this);
            View dialog_view = View.inflate(Home_activity.this, R.layout.loginpw_dialog, null);
            et_pw_login = (EditText) dialog_view.findViewById(R.id.et_pw_login);
            bt_pw_enter = (Button) dialog_view.findViewById(R.id.bt_pw_enter);
            bt_pw_cancel = (Button) dialog_view.findViewById(R.id.bt_pw_cancel);
            dialog = builder.create();
            dialog.setView(dialog_view);
            dialog.show();
            bt_pw_enter.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (et_pw_login.getText().toString().equals(sp.getString("password", null))) {
                        dialog.dismiss();
                        if (!sp.getBoolean("lostfind_isseted", false)) {
                            startActivity(new Intent(Home_activity.this, Lostfind_set1_activity.class));
                        } else {
                            startActivity(new Intent(Home_activity.this, Lostfind_activity.class));
                        }
                        Toast.makeText(Home_activity.this, "密码正确", Toast.LENGTH_SHORT).show();
                    } else {
                        et_pw_login.setText("");
                        Toast.makeText(Home_activity.this, "密码错误" + retry + "次", Toast.LENGTH_SHORT).show();
                        retry++;
                    }
                }
            });
            bt_pw_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog.dismiss();
                }
            });
        }
    }

    public class Myadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(Home_activity.this, R.layout.view_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_item);
            TextView textView = (TextView) view.findViewById(R.id.tv_item);
            imageView.setImageResource(ids[position]);
            textView.setText(names[position]);
            return view;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void Exit() {
        if (System.currentTimeMillis() - time > 2000) {
            Toast.makeText(Home_activity.this, "T_T确定关掉我么，请再按一次", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        } else {
            Home_activity.this.finish();

        }
    }

    MediaPlayer mp;

    private void backgroundmusic() {

        mp = new MediaPlayer();
        new Thread(
                new Runnable() {

                    public void run() {
                        Log.v("AD", "B1");
                        try {
  //                          mp.setDataSource("http://nm2189cloud.oos-nm2.ctyunapi.cn/1c1a591e-f9a4-449f-8fc5-d03412ca2ec0?response-content-type=audio/mpeg&Expires=1496578716&response-content-disposition=attachment%3Bfilename%3D%22BilibiliJJ.COM-%C3%A3%C2%80%C2%90%C3%A6%C2%95%C2%B0%C3%A7%C2%A0%C2%81%C3%A5%C2%AE%C2%9D%C3%A8%C2%B4%C2%9Dtri%C3%A5%C2%89%C2%A7%C3%A5%C2%9C%C2%BA%C3%A7%C2%89%C2%88%C3%A3%C2%80%C2%91%C3%A6%C2%96%C2%B0%C3%A7%C2%89%C2%88butterfly%C3%AF%C2%BC%C2%88%C3%A5%C2%AE%C2%8C%C3%A6%C2%95%C2%B4%C3%A7%C2%89%C2%88%C3%AF%C2%BC%C2%89_%28Av3119563%2CP1%29.mp3%22&AWSAccessKeyId=879ff552c2446bad41c3&Signature=jzC/pTOF/N5yoqoJTVq/axGuJuc%3D");
//                            mp.setDataSource("http://xa189cloud.oos-website-cn.oos-snxa.ctyunapi.cn/15dd4626-7a7b-4efe-917a-231b861d3a55?response-content-type=audio/mpeg&Expires=1496593417&response-content-disposition=attachment%3Bfilename%3D%22BilibiliJJ.COM-%C3%A3%C2%80%C2%90%C3%A6%C2%95%C2%B0%C3%A7%C2%A0%C2%81%C3%A5%C2%AE%C2%9D%C3%A8%C2%B4%C2%9D%C3%A3%C2%80%C2%91Butterfly%C3%A4%C2%B9%C2%90%C3%A9%C2%98%C2%9FCover8%C3%A4%C2%B8%C2%AA%C3%A8%C2%A2%C2%AB%C3%A9%C2%80%C2%89%C3%A4%C2%B8%C2%AD%C3%A7%C2%9A%C2%84%C3%A5%C2%A5%C2%B3%C3%A5%C2%AD%C2%A9%C3%A5%C2%AD%C2%90%C3%AF%C2%BC%C2%88%C3%A5%C2%8E%C2%9F%C3%A5%C2%88%C2%9BPV%C3%A4%C2%BB%C2%98%C3%AF%C2%BC%C2%89_%28Av2386547%C3%AF%C2%BC%C2%8CP1%29.mp3%22&AWSAccessKeyId=c947570364056574cccc&Signature=lgPRWHh2MCqwO0Y6t9W8lpOxfM4%3D");
                            mp.prepare();
                            mp.start();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ) {
        }.start();

        new Thread() {
            @Override
            public void run() {
                Log.v("AD", "A");
            }
        }.start();
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.v("AD", "B2");
            }
        }).start();

    }

}