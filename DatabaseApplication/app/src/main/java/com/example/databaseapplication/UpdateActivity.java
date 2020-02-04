package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    Button insert;
    EditText id, name, salary;
    RadioGroup gender;
    RadioButton sel;
    Spinner dept;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        insert = findViewById(R.id.uinsert);
        id = findViewById(R.id.uid);
        name = findViewById(R.id.uname);
        salary = findViewById(R.id.usalary);
        gender = findViewById(R.id.urg);
        dept = findViewById(R.id.uspinner);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "";
                if(!name.getText().toString().matches("[A-Za-z]+")) msg += "Name error! ";
                if(!salary.getText().toString().matches("[0-9]+")) msg += "Salary error! ";
                if(gender.getCheckedRadioButtonId() == -1) msg += "Select gender! ";
                else sel = findViewById(gender.getCheckedRadioButtonId());
                Cursor c = db.rawQuery("SELECT * FROM EMPLOYEES WHERE ID = ?;", new String[]{id.getText().toString()});
                if(!id.getText().toString().matches("[A-Za-z0-9]+") || !c.moveToNext()) {
                    msg = "ID error or doesn't exist!";
                }
                else if(msg.equals("")) {
                    msg = "Updated!";
                    db.execSQL("UPDATE EMPLOYEES SET NAME = ?, GENDER = ?, DEPT = ?, SALARY = ? WHERE ID = ?;", new String[]{name.getText().toString(), sel.getText().toString(), dept.getSelectedItem().toString(), salary.getText().toString(), id.getText().toString()});
                }
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}