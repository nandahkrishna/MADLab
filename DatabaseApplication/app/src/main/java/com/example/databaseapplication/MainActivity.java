package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.execSQL("DROP TABLE IF EXISTS EMPLOYEES;");
                db.execSQL("CREATE TABLE EMPLOYEES(NAME VARCHAR(20), GENDER VARCHAR(20), ID VARCHAR(20), DEPT VARCHAR(20), SALARY VARCHAR(20));");
                Toast.makeText(getApplicationContext(), "Created!", Toast.LENGTH_SHORT).show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), InsertActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), UpdateActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), DeleteActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), RetrieveActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), RetrieveDeptActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
