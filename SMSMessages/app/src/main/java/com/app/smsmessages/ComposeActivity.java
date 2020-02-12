package com.app.smsmessages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ComposeActivity extends AppCompatActivity {

    Button b;
    EditText ph, msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        b = findViewById(R.id.send);
        ph = findViewById(R.id.ph);
        msg = findViewById(R.id.msg);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(ph.getText().toString(), null, msg.getText().toString(), null, null);
                Toast.makeText(getApplicationContext(), "Sent!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}