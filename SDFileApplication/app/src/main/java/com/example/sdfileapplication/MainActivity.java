package com.example.sdfileapplication;

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
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText writefile, content;
    Button readb, writeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writefile = findViewById(R.id.writefile);
        content = findViewById(R.id.editText2);
        writeb = findViewById(R.id.writeb);
        readb = findViewById(R.id.readb);
        final File sdcard = Environment.getExternalStorageDirectory();
        writeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                    }
                }
                String c = content.getText().toString();
                try {
                    FileWriter writer = new FileWriter(sdcard.getAbsolutePath() + "/" + writefile.getText().toString(), false);
                    writer.write(c);
                    writer.close();
                    Toast.makeText(getApplicationContext(), "Write complete!", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Write error!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        readb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReadActivity.class);
                startActivity(intent);
            }
        });
    }
}
