package com.app.emailapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    Button b, l;
    EditText recipient, subject, body;
    FusedLocationProviderClient c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        b = findViewById(R.id.b);
        l = findViewById(R.id.l);
        recipient = findViewById(R.id.recipient);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        ActivityCompat.requestPermissions(MainActivity.this, new String[] {ACCESS_FINE_LOCATION}, 1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                emailIntent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
                startActivity(Intent.createChooser(emailIntent, "Send Email"));
            }
        });
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {recipient.getText().toString()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Location");
                c.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            double lng = (double) location.getLongitude();
                            double lat = (double) location.getLatitude();
                            String loc = "Longitude: " + String.valueOf(lng) + " and Latitude: " + String.valueOf(lat);
                            emailIntent.putExtra(Intent.EXTRA_TEXT, loc);
                            startActivity(Intent.createChooser(emailIntent, "Send Location"));
                        }
                    }
                });
            }
        });
    }
}
