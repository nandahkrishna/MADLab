package com.app.rssreader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import static android.Manifest.permission.INTERNET;


public class MainActivity extends Activity {
    TextView t;
    EditText u;
    Button b1, b2;
    String finalUrl = "https://tutorialspoint.com/android/sampleXML.xml";
    HandleXML obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = (TextView) findViewById(R.id.textview);
        u = (EditText) findViewById(R.id.editText);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!u.getText().toString().equals("")) finalUrl = u.getText().toString();
                ActivityCompat.requestPermissions(MainActivity.this, new String[] {INTERNET}, 0);
                obj = new HandleXML(finalUrl);
                obj.fetchXML();
                while(obj.parsingComplete);
                t.setText(obj.getContent());
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this, SecondActivity.class);
                in.putExtra("url", u.getText().toString());
                startActivity(in);
            }
        });
    }

}

