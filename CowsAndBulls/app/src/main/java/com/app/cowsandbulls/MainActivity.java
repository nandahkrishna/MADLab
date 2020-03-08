package com.app.cowsandbulls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button newgame, instructions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(getApplicationContext(), "words.sqlite");
        try {
            dbHelper.importDataBaseFromAssets();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        newgame = findViewById(R.id.newgame);
        instructions = findViewById(R.id.instructions);
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WordActivity.class);
                startActivity(i);
            }
        });
    }
}
