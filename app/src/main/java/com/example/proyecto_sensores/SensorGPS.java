package com.example.proyecto_sensores;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SensorGPS {
    LocationManager locationManager;
    TextView txtLatitud, txtLongitud;

    public SensorGPS(Context context, Activity activity, TextView ltt, TextView lgt) {
        txtLatitud = ltt; txtLongitud = lgt;

        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[] {
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                10, 1, new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                txtLatitud.setText(String.valueOf(location.getLatitude()));
                txtLongitud.setText(String.valueOf(location.getLongitude()));
            }
        });
    }
}
