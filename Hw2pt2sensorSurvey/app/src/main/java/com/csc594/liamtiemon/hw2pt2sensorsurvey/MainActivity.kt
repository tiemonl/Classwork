package com.csc594.liamtiemon.hw2pt2sensorsurvey

import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.content.Context
import android.widget.TextView




class MainActivity : AppCompatActivity() {

    private lateinit var mSensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL)

        // Iterate through the list of sensors, get the sensor name,
        // append it to the string.
        val sensorText = StringBuilder()
        //String sensorText = "";
        for (currentSensor in sensorList) {
            sensorText.append(currentSensor.name).append(
                System.getProperty("line.separator")
            )
        }

        // Update the sensor list text view to display the list of sensors.
        val sensorTextView = findViewById<TextView>(R.id.sensor_list)
        sensorTextView.text = sensorText

    }
}
