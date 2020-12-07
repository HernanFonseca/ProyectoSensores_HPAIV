package com.example.proyecto_sensores;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Acelerometro extends AppCompatActivity implements SensorEventListener {
    Sensor accelerometer;
    SensorManager sm;
    TextView acceleration;
    int whip=0;
    float x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_acel);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this,accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        acceleration = (TextView) findViewById(R.id.acceleration);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        x = sensorEvent.values[0];
        acceleration.setText("\nX: " + sensorEvent.values[0] +
                "\nY: " + sensorEvent.values[1] +
                "\nZ: " + sensorEvent.values[2]);
        if (x<-5 && whip==0){
            whip++;
            getWindow().getDecorView().setBackgroundColor(Color.rgb(140, 200, 255));
        }
        if (x>5 && whip==1){
            whip++;
            getWindow().getDecorView().setBackgroundColor(Color.rgb(255,150,150));
        }
        if (whip==2){
            whip=0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) { }
}
