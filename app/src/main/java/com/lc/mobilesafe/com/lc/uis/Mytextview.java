package com.lc.mobilesafe.com.lc.uis;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Administrator on 2017/05/15/0015.
 */

public class Mytextview extends TextView {
    public Mytextview(Context context) {
        super(context);
    }

    public Mytextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Mytextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused()
    {
        return true;
    }
}
