package com.example.progressbar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    Button b, b2;
    TextView t;
    EditText e;
    int[] colors = new int[] {0xffd50000, 0xff2196f3, 0xffff6f00, 0xff4caf50};
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = 0;
        bar = (ProgressBar) findViewById(R.id.progressBar);
        b = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        t = (TextView) findViewById(R.id.textView4);
        e = (EditText) findViewById(R.id.editText);
        bar.setProgress(0);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        i = 0;
                        while (i < 100) {
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            bar.setProgress(i);
                            bar.getProgressDrawable().setColorFilter(colors[i/25], android.graphics.PorterDuff.Mode.SRC_IN);
                            i++;
                            t.setText(String.valueOf(i) + "%");
                        }
                        bar.setProgress(100);
                    }
                }).start();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogTask d = new DialogTask(MainActivity.this, Integer.parseInt(e.getText().toString()));
                d.execute();
            }
        });
    }

    class DialogTask extends AsyncTask<Void, Void, Void> {
        ProgressDialog pd;
        int delay;
        public DialogTask(MainActivity c, int delay) {
            this.delay = delay;
            pd = new ProgressDialog(c);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Sleeping now for " + String.valueOf(delay) + " milliseconds...");
            pd.show();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pd.dismiss();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }
}