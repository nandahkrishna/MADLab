package com.app.cowsandbulls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PlayActivity extends AppCompatActivity {

    static DBHelper dbHelper;
    static SQLiteDatabase db;
    static String target;
    static int chances = 0;
    static Button chance, end;
    static EditText guess;
    static  Boolean win;
    static TextView guesses, bcs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        PlayActivity.chances = 0;
        Intent i = getIntent();
        PlayActivity.target = i.getStringExtra("target");
        guess = findViewById(R.id.guess);
        chance = findViewById(R.id.chance);
        end = findViewById(R.id.end);
        guesses = findViewById(R.id.guesses);
        bcs = findViewById(R.id.bcs);
        dbHelper = new DBHelper(getApplicationContext(), "words.sqlite");
        db = SQLiteDatabase.openDatabase(dbHelper.DB_PATH + dbHelper.DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                String g = PlayActivity.guess.getText().toString();
                Cursor cursor = db.rawQuery("SELECT * FROM words WHERE word='" + g.toLowerCase() + "';", new String[] {});
                if(g.length() != 4 || !g.matches("[A-Z]+")) {
                    Toast.makeText(getApplicationContext(), "Enter a 4-letter word!", Toast.LENGTH_SHORT).show();
                }
                else if(!cursor.moveToNext()) {
                    Toast.makeText(getApplicationContext(), "Does that word exist?", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    PlayActivity.win = false;
                    PlayActivity.guess.setText("");
                    Integer[] mt = new Integer[] {0, 0, 0, 0};
                    Integer[] mg = new Integer[] {0, 0, 0, 0};
                    int b = 0, c = 0;
                    for(int i = 0; i < 4; i++) {
                        if(g.charAt(i) == PlayActivity.target.charAt(i)) {
                            b++;
                            mt[i] = 1;
                            mg[i] = 1;
                        }
                    }
                    for(int i = 0; i < 4; i++) {
                        if(mg[i] == 1) continue;
                        for(int j = 0; j < 4; j++) {
                            if(g.charAt(i) == PlayActivity.target.charAt(j) && mt[j] == 0) {
                                mt[j] = 1;
                                mg[i] = 1;
                                c++;
                                break;
                            }
                        }
                    }
                    PlayActivity.guesses.setText(PlayActivity.guesses.getText().toString() + Integer.toString(PlayActivity.chances + 1) + ". " + g + "\n");
                    PlayActivity.bcs.setText(PlayActivity.bcs.getText().toString() + Integer.toString(b) + " & " + Integer.toString(c) + "\n");
                    PlayActivity.chances += 1;
                    if(b == 4) PlayActivity.win = true;
                    if(PlayActivity.chances == 10 || PlayActivity.win) {
                        guess.setEnabled(false);
                        guess.setFocusable(false);
                        chance.setEnabled(false);
                        chance.setFocusable(false);
                        end.setEnabled(false);
                        end.setFocusable(false);
                        if(win) {
                            Toast.makeText(getApplicationContext(), "You win!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "You lose! The word is " + PlayActivity.target, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}
