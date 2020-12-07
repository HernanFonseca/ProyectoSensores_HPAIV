package com.example.proyecto_sensores;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SensorGPS extends AppCompatActivity {
    double latitud, longitud;
    LocationManager locationManager;
    Context context;

    public SensorGPS(final Context context, Activity activity, LocationManager locationManager) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
        this.locationManager = locationManager;
        this.context = context;
    }

    @SuppressLint("MissingPermission")
    public void getLocation() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 1, new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        latitud = location.getLatitude();
                        longitud = location.getLongitude();
                        String loc = "Latitud: " + String.format("%.6f", latitud) +
                                "\nLongitud: " + String.format("%.6f", longitud);
                        Toast.makeText(context, loc, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
