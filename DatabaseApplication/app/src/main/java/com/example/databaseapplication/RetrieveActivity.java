package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveActivity extends AppCompatActivity {
    Button b;
    EditText e;
    TextView t;
    String id;
    SQLiteDatabase db;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        b = findViewById(R.id.buttonr);
        e = findViewById(R.id.editTextr);
        t = findViewById(R.id.resultr);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = e.getText().toString();
                rs = db.rawQuery("SELECT * FROM EMPLOYEES WHERE ID = ?;", new String[]{id});
                rs.moveToFirst();
                try {
                    t.setText(rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4));
                } catch (Exception ex) {
                    t.setText("No such employee");
                    ex.printStackTrace();
                }
            }
        });
    }
}