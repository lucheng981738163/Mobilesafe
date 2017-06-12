package com.lc.mobilesafe;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


public class Qidongguanggao_activity extends Activity{
    Long timestart= System.currentTimeMillis();
    private TextView tv_timer;
    public static long runtime;
    public static int chixumiaoshu;
     boolean flag=true;
    boolean flag2=true;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.qidongguanggao);
        tv_timer= (TextView) findViewById(R.id.tv_timer);
//        new Thread(new Runnable(){
//
//            @Override
//            public void run() {
//
//            }
//        }){}.start();
        new Thread(){


            public void run() {
                try {
                    for(int i=4,j=1;i>-1;i--,j++ ){
                        Message msg=new Message();
                        msg.what=i;
                        msg.arg1=j;
                        Thread.sleep(1000);
                        handler.sendMessage(msg);
                    }
             Long timeend= System.currentTimeMillis();
//                    有了定时这句变成鸡肋
//                    if(timeend-timestart<5000){
//                        Thread.sleep(5000-timeend+timestart);
//                    }
                    //记录运行时间
                   if (flag){
                    flag=false;
                    runtime=timeend-timestart-chixumiaoshu;
                    Intent intent =new Intent(Qidongguanggao_activity.this,Home_activity.class);
                    startActivity(intent);
                       Qidongguanggao_activity.this.finish();

                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    //执行handler.sendMessage(msg);后回调方法
    Handler handler=new Handler(){

        public void handleMessage(Message msg) {

        tv_timer.setText(msg.what+"");
            chixumiaoshu=msg.arg1*1000;
        }
    };
  public void   Homeactivity(View view){
      if(flag||flag2){

          flag=false;
      Intent intent =new Intent(Qidongguanggao_activity.this,Home_activity.class);
      startActivity(intent);
      Long timeend= System.currentTimeMillis();

        runtime=timeend-timestart-chixumiaoshu;;
          Qidongguanggao_activity.this.finish();

      }



  }
public void internet(View view){
    flag=false;
    Intent intent=new Intent();
    intent.setAction("android.intent.action.VIEW");
    intent.addCategory("android.intent.category.DEFAULT");
    intent.addCategory("android.intent.category.BROWSABLE");
    intent.setData(Uri.parse("https://www.baidu.com"));
    startActivity(intent);
    }

}

