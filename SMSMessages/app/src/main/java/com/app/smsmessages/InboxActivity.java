package com.app.smsmessages;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class InboxActivity extends AppCompatActivity {

    public static boolean active = false;
    public static InboxActivity inst;
    TextView msgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inst = this;
        refreshInbox();
    }

    void refreshInbox() {
        InboxActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.activity_inbox);
                msgs = findViewById(R.id.msgs);
                String m = "";
                ContentResolver c = getContentResolver();
                Cursor i = c.query(Uri.parse("content://sms/inbox"), null, null, null, null);
                if(!i.moveToFirst()) {
                    return;
                }
                else {
                    do {
                        m = m + "From: " + i.getString(i.getColumnIndex("address")) + "\nMessage: " + i.getString(i.getColumnIndex("body")) + "\n\n";
                    } while(i.moveToNext());
                    msgs.setText(m);
                }
            }
        });
    }

    public static InboxActivity instance() {
        return inst;
    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
        inst = this;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
        inst = null;
    }
}