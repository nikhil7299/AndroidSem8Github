package com.example.sem8all

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class U4ProximityDemo : AppCompatActivity(){
    lateinit var sensorStatusTV: TextView
    lateinit var proximitySensor: Sensor
    lateinit var sensorManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_u4_proximity_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sensorStatusTV = findViewById(R.id.sensorStatusTV)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as
                SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!
        if (proximitySensor == null) {

            Toast.makeText(this, "No proximity sensor found in device..", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            sensorManager.registerListener(proximitySensorEventListener, proximitySensor,
                SensorManager.SENSOR_DELAY_NORMAL)
        }
    }
    var proximitySensorEventListener: SensorEventListener? =
        object : SensorEventListener {
            override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            }
            override fun onSensorChanged(event: SensorEvent) {
                // check if the sensor type is proximity sensor.
                if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                    if (event.values[0] == 0f) {
                        sensorStatusTV.text = "Object is Near to sensor"
                    } else {
                        sensorStatusTV.text = "Object is Away from sensor"
                    }
                }
            }
        }
}