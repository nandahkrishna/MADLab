package com.example.graphicsapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.EventListener;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {
    ImageView im;
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9;
    int clicks;

    public void reset_im() {
        im.setScaleX(1);
        im.setScaleY(1);
        im.setRotation(0);
        im.setBackgroundResource(0);
        im.setImageResource(0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        im = (ImageView)findViewById(R.id.imageView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        b9 = (Button)findViewById(R.id.button9);
        clicks = 0;
        final Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                Bitmap tempBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawRect(25, 25, 75, 75, paint);
                im.setImageBitmap(tempBitmap);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                Bitmap tempBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawCircle(50, 50, 20, paint);
                im.setImageBitmap(tempBitmap);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                Bitmap tempBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawLine(0, 0, 100, 100, paint);
                im.setImageBitmap(tempBitmap);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                Bitmap tempBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                RectF a = new RectF(10, 10, 90, 90);
                tempCanvas.drawArc(a, 225, 90, true, paint);
                im.setImageBitmap(tempBitmap);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                im.setBackgroundResource(R.drawable.movie);
                AnimationDrawable anim = (AnimationDrawable) im.getBackground();
                anim.start();
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                Bitmap tempBitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
                Canvas tempCanvas = new Canvas(tempBitmap);
                tempCanvas.drawRect(10, 10, 90, 60, paint);
                paint.setColor(Color.YELLOW);
                tempCanvas.drawRect(20, 20, 80, 40, paint);
                paint.setColor(Color.BLACK);
                tempCanvas.drawCircle(30, 60, 10, paint);
                tempCanvas.drawCircle(70, 60, 10, paint);
                paint.setColor(Color.BLUE);
                im.setImageBitmap(tempBitmap);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                im.setImageResource(R.drawable.apple);
                if(clicks == 0) {
                    im.setScaleX(0.5f);
                    im.setScaleY(0.5f);
                }
                else if(clicks == 1) {
                    im.setRotation(20f);
                }
                else {
                    im.setScaleX(1.05f);
                    im.setScaleY(1.05f);
                }
                clicks++;
                clicks %= 3;
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                float x = im.getX();
                ObjectAnimator animCar = ObjectAnimator.ofFloat(im, "x", x + 200);
                animCar.setDuration(1000);
                AnimatorSet a = new AnimatorSet();
                a.play(animCar).before(ObjectAnimator.ofFloat(im, "x", x).setDuration(1000));
                a.start();
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset_im();
                im.setScaleX(0.5f);
                im.setScaleY(0.5f);
                im.setY(0f);
                im.setImageResource(R.drawable.apple);
                ObjectAnimator appleDrop = ObjectAnimator.ofFloat(im, "y", 400).setDuration(1500);
                ObjectAnimator appleTurn = ObjectAnimator.ofFloat(im, "rotation", 20).setDuration(1500);
                AnimatorSet a = new AnimatorSet();
                a.playTogether(appleDrop, appleTurn);
                a.start();
            }
        });
    }
}
