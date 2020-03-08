package com.app.cowsandbulls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WordActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DBHelper dbHelper;
    Button enter;
    EditText target;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        dbHelper = new DBHelper(getApplicationContext(), "words.sqlite");
        db = SQLiteDatabase.openDatabase(dbHelper.DB_PATH + dbHelper.DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        target = findViewById(R.id.target);
        enter = findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t = target.getText().toString();
                if(t.length() != 4 || !t.matches("[A-Z]+")) {
                    Toast.makeText(getApplicationContext(), "Enter a 4-letter word!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor c = db.rawQuery("SELECT * FROM words WHERE word='" + t.toLowerCase() + "';", new String[] {});
                    if(!c.moveToNext()) {
                        Toast.makeText(getApplicationContext(), "Does that word exist?", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent i = new Intent(getApplicationContext(), PlayActivity.class);
                        i.putExtra("target", t);
                        startActivity(i);
                    }
                }
            }
        });
    }
}
