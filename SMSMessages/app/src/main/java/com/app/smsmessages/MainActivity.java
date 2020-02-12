package com.app.smsmessages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.RECEIVE_SMS;
import static android.Manifest.permission.SEND_SMS;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {READ_SMS, SEND_SMS, RECEIVE_SMS}, 1);
    }
    public void launchCompose(View view) {
        Intent intent = new Intent(MainActivity.this, ComposeActivity.class);
        startActivity(intent);
    }
    public void launchInbox(View view) {
        Intent intent = new Intent(MainActivity.this, InboxActivity.class);
        startActivity(intent);
    }
}