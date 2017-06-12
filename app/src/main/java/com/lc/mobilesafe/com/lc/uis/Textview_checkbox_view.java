package com.lc.mobilesafe.com.lc.uis;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lc.mobilesafe.R;


/**
 * Created by Administrator on 2017/05/13/0013.
 */

public class Textview_checkbox_view extends RelativeLayout {
    private TextView tv_textivew_checkbox;
    private CheckBox cb_textivew_checkbox;
    private TextView tv_title;
    private String title;
private  String update_on;
    private String update_off;

    //初始化操作都在这个方法里，因为都会执行一次
    private void importview(Context context) {
        View.inflate(context, R.layout.textview_checkbox, Textview_checkbox_view.this);
        tv_textivew_checkbox= (TextView) this.findViewById(R.id.tv_textivew_checkbox);
         cb_textivew_checkbox= (CheckBox) this.findViewById(R.id.cb_textivew_checkbox);
        tv_title= (TextView) findViewById(R.id.tv_title);
    }
    public boolean isChecked() {
     return cb_textivew_checkbox.isChecked();
    }
    public void setChecked(boolean checked){
        cb_textivew_checkbox.setChecked(checked);


    }
    public void setText(String text){
        tv_textivew_checkbox.setText(text);
    }
    public Textview_checkbox_view(Context context, AttributeSet attrs) {
        super(context, attrs);
        importview(context);
        //和后面这个键值有关系,布局文件中，和Class中直接改的是键值
        title = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "text");
        update_on = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "update_on");
        update_off = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "update_off");
        tv_title.setText(title);

    }



    public Textview_checkbox_view(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        importview(context);
    }

    public Textview_checkbox_view(Context context) {
        super(context);
        importview(context);
    }


}
