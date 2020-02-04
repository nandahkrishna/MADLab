package com.example.databaseapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {
    Button d;
    EditText e;
    SQLiteDatabase db;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        db = openOrCreateDatabase("db", MODE_PRIVATE, null);
        d = findViewById(R.id.buttond);
        e = findViewById(R.id.editTextd);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = e.getText().toString();
                c = db.rawQuery("SELECT * FROM EMPLOYEES WHERE ID = ?;", new String[]{id});
                if (!c.moveToNext())
                    Toast.makeText(getApplicationContext(), "Doesn't exist!", Toast.LENGTH_SHORT).show();
                else {
                    db.execSQL("DELETE FROM EMPLOYEES WHERE ID = ?;", new String[]{id});
                    Toast.makeText(getApplicationContext(), "Deleted " + id, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}