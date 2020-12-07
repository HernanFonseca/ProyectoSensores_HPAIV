package com.example.proyecto_sensores;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    SensorGPS gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FragmentActivity fragment = MainActivity.this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Executor executor = ContextCompat.getMainExecutor(this);
        Button btnHuella = findViewById(R.id.btnHuella);
        btnHuella.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                SensorHuella huella = new SensorHuella();
                huella.usar(executor, MainActivity.this, fragment);
            }
        });

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        try {
            gps = new SensorGPS(getApplicationContext(),this, locationManager);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        Button btnGPS = findViewById(R.id.btnGPS);
        btnGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String location = "Latitud: " + String.format("%.6f", gps.getLatitud()) +
                            "\nLongitud: " + String.format("%.6f", gps.getLongitud());
                    Toast.makeText(getApplicationContext(), location, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}