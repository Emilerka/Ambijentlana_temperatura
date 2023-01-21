package com.example.mmos_drugi_seminarski;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textview;

    private SensorManager sensorManager;
    private Sensor temperaturnisenzor;
    private Boolean DostupnostSenzora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    textview = findViewById(R.id.textView);
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)!=null) {
            temperaturnisenzor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            DostupnostSenzora = true;
        }else {
            textview.setText("Senzor nije podržan");
            DostupnostSenzora = false;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        textview.setText("Temperatura je: "+sensorEvent.values[0]+ " °C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(DostupnostSenzora)
        {
            sensorManager.registerListener( this,  temperaturnisenzor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(DostupnostSenzora)
        {
            sensorManager.unregisterListener(this);
        }
    }
}