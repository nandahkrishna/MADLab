package com.example.sdfileapplication;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ReadActivity extends AppCompatActivity {

    EditText f;
    TextView t;
    Button rreadb, rwriteb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        f = findViewById(R.id.readfile);
        t = findViewById(R.id.textView3);
        rreadb = findViewById(R.id.rreadb);
        rwriteb = findViewById(R.id.rwriteb);
        final File sdcard = Environment.getExternalStorageDirectory();
        rreadb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(ReadActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                    }
                }
                File file = new File(sdcard, f.getText().toString());
                try {
                    Scanner r = new Scanner(file);
                    r.useDelimiter("\\Z");
                    t.setText(r.next());
                    Toast.makeText(getApplicationContext(), "Read complete!", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    t.setText("");
                    Toast.makeText(getApplicationContext(), "File does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        rwriteb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
