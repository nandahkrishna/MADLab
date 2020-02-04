package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveDeptActivity extends AppCompatActivity {
    Button b;
    Spinner d;
    TextView t;
    String dept, msg;
    SQLiteDatabase db;
    Cursor rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrievedept);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        b = findViewById(R.id.buttonrd);
        d = findViewById(R.id.deptspinner);
        t = findViewById(R.id.resultrd);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dept = d.getSelectedItem().toString();
                rs = db.rawQuery("SELECT * FROM EMPLOYEES WHERE DEPT = ?;", new String[]{dept});
                msg = "";
                try {
                    while(rs.moveToNext()) {
                        msg += rs.getString(0) + " " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "\n";
                    }
                    t.setText(msg);
                    if(msg.equals("")) {
                        t.setText("No such employee");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}