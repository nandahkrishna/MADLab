package com.app.smsmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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