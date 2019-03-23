package com.example.hw2pt1surveylisteners

import android.hardware.Sensor
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.hardware.SensorManager
import android.content.Context
import android.hardware.SensorEvent
import android.hardware.SensorEventListener


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var mSensorManager: SensorManager

    // Proximity and light sensors, as retrieved from the sensor manager.
    private lateinit var mSensorProximity: Sensor
    private lateinit var mSensorLight: Sensor

    // TextViews to display current sensor values.
    private lateinit var mTextSensorLight: TextView
    private lateinit var mTextSensorProximity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTextSensorLight = findViewById<TextView>(R.id.label_light)
        mTextSensorProximity = findViewById<TextView>(R.id.label_proximity)

        // Get an instance of the sensor manager.
        mSensorManager = getSystemService(
            Context.SENSOR_SERVICE
        ) as SensorManager

        mSensorProximity = mSensorManager.getDefaultSensor(
            Sensor.TYPE_PROXIMITY
        )
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)

        val sensor_error = resources.getString(R.string.error_no_sensor)

        if (mSensorLight == null) {
            mTextSensorLight.text = sensor_error
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.text = sensor_error
        }
    }

    override fun onStart() {
        super.onStart()

        if (mSensorProximity != null) {
            mSensorManager.registerListener(
                this, mSensorProximity,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(
                this, mSensorLight,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onStop() {
        super.onStop()

        mSensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {

        val sensorType = sensorEvent.sensor.type

        val currentValue = sensorEvent.values[0]

        when (sensorType) {
            Sensor.TYPE_LIGHT ->
                mTextSensorLight.text = resources.getString(
                    R.string.label_light, currentValue
                )
            Sensor.TYPE_PROXIMITY ->
                mTextSensorProximity.text = resources.getString(
                    R.string.label_proximity, currentValue
                )
        }// do nothing
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}

}
