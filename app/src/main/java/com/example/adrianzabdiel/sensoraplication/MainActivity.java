package com.example.adrianzabdiel.sensoraplication;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView =(TextView) findViewById(R.id.text);
        mSensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor>deviceSensors =mSensorManager.getSensorList(Sensor.TYPE_ALL);

        String s ="";
        if (deviceSensors != null) {
            for(int i=0; i<deviceSensors.size(); i++){
                Log.wtf("Listando sensores","Nombre" +deviceSensors.get(i).getName());
                s += deviceSensors.get(i).getName()+" __ ";
                textView.setText(s);
            }
        }
        if(mSensor ==null){
            if(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!= null){
                mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            }
            else{
                textView.setText("No tengo acelerometro");
            }
        }

    }

    protected void onResume(){
        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause(){
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
    public void onSensorChanged(SensorEvent sensorEvent){
        Log.wtf("onSensorChange", ""+sensorEvent.values[0]);
        textView.setText("X: "+sensorEvent.values[0]+"  Y: "+sensorEvent.values[1]+ "  Z: "+sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
