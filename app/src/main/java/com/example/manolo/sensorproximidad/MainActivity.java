package com.example.manolo.sensorproximidad;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensorProximidad;
    private SensorEventListener escuchadorSensorProximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorProximidad = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if(sensorProximidad == null) {
            Toast.makeText(this, "Sensor de proximidad no disponible", Toast.LENGTH_LONG).show();
            finish();
        }

        escuchadorSensorProximidad = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0] < sensorProximidad.getMaximumRange()) {
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(escuchadorSensorProximidad, sensorProximidad, 20000000);

    }


}
