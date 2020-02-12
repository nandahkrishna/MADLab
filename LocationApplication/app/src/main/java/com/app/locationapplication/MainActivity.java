package com.app.locationapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import java.io.IOException;
import java.util.ArrayList;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Geocoder coder = new Geocoder(this);
        Button getLocation = findViewById(R.id.getLocation);
        Button getAddress = findViewById(R.id.getAddress);
        final EditText addressText = findViewById(R.id.addressText);
        final TextView latView1 = findViewById(R.id.locationLat1);
        final TextView longView1 = findViewById(R.id.locationLong1);
        final TextView latView = findViewById(R.id.locationLat);
        final TextView longView = findViewById(R.id.locationLong);
        ActivityCompat.requestPermissions(this, new String[] {ACCESS_FINE_LOCATION}, 1);
        client = LocationServices.getFusedLocationProviderClient(this);
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null) {
                            Double lng = (double) Math.round(location.getLongitude() * 100) / 100;
                            Double lat = (double) Math.round(location.getLatitude() * 100) / 100;
                            latView.setText(lat.toString());
                            longView.setText(lng.toString());
                        }
                    }
                });
            }
        });
        getAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Address> addresses = null;
                try {
                    String address = addressText.getText().toString();
                    addresses = (ArrayList<Address>) coder.getFromLocationName(address, 1);
                    for(Address add: addresses){
                        Double lng = (double) Math.round(add.getLongitude() * 100) / 100;
                        Double lat = (double) Math.round(add.getLatitude() * 100) / 100;
                        latView1.setText(lat.toString());
                        longView1.setText(lng.toString());
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
