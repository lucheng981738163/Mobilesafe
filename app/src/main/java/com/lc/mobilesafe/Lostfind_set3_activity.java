package com.lc.mobilesafe;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.lc.mobilesafe.com.lc.uis.pre_next_star_view;

/**
 * Created by Administrator on 2017/06/01/0001.
 */

public class Lostfind_set3_activity extends Activity {
    private pre_next_star_view star3;
    private GestureDetector gestureDetector;
    private EditText et_phonenumber;
    private SharedPreferences sp;
private ContentResolver resolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lostfind_set3);
        resolver=getContentResolver();
        sp = getSharedPreferences("config", MODE_PRIVATE);
        et_phonenumber = (EditText) findViewById(R.id.et_phonenumber);
        star3 = (pre_next_star_view) findViewById(R.id.star3);
        star3.setpage(3);
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
        startActivity(new Intent(this, Lostfind_set2_activity.class));
        finish();
        overridePendingTransition(R.anim.pre_in, R.anim.pre_out);
    }

    public void bt_setup_next(View view) {
        sp.edit().putString("phonenumber", et_phonenumber.getText().toString()).commit();
        startActivity(new Intent(this, Lostfind_set4_activity.class));
        finish();
        overridePendingTransition(R.anim.next_in, R.anim.next_out);
    }

    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public void choose_phonenumber(View view) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == Activity.RESULT_OK) {
                    Cursor cursor = resolver.query(Uri.parse("content://com.android.contacts/raw_contacts"), new String[]{"contact_id"}, null, null, null);
                    while (cursor.moveToNext()) {
                        String contact_id = cursor.getString(0);
                        if (contact_id != null) {
                            Cursor datacursor = resolver.query(Uri.parse("content://com.android.contacts/data"), new String[]{"data1", "mimetype"}, "contact_id=?", new String[]{"contact_id"}, null);
                           while (datacursor.moveToNext()){
                               if ("vnd.adnroid.cursor.item/name".equals(datacursor.getString(1))){

                               }else if("vnd.adnroid.cursor.item/phone_v2".equals(datacursor.getString(1))){

                               }
                           }
                        }
                    }

                }
                break;
        }
    }

    public void clicktext(View view) {
        et_phonenumber.setText("");
    }
}
