package com.lc.mobilesafe.com.lc.uis;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lc.mobilesafe.R;

/**
 * Created by Administrator on 2017/06/03/0003.
 */

public  class pre_next_star_view extends LinearLayout {
    private ImageView iv_star1;
    private ImageView iv_star2;
    private ImageView iv_star3;
    private ImageView iv_star4;
    private void importview(Context context) {
        View startview=  View.inflate(context, R.layout.pre_next_star, pre_next_star_view.this);
        iv_star1= (ImageView) startview.findViewById(R.id.iv_star1);
        iv_star2= (ImageView) startview.findViewById(R.id.iv_star2);
        iv_star3= (ImageView) startview.findViewById(R.id.iv_star3);
        iv_star4= (ImageView) startview.findViewById(R.id.iv_star4);
    }
    public pre_next_star_view(Context context) {
        super(context);
        importview(context);
    }
    public pre_next_star_view(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        importview(context);
    }

    public void setpage(int number) {
        switch (number){
            case 1: iv_star1.setImageResource(android.R.drawable.btn_star_big_on);
                break;
            case 2:iv_star2.setImageResource(android.R.drawable.btn_star_big_on);
                break;
            case 3:iv_star3.setImageResource(android.R.drawable.btn_star_big_on);
                break;
            case 4:iv_star4.setImageResource(android.R.drawable.btn_star_big_on);
                break;
        }
    }

}
