package com.example.sem8all

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class U4TemperatureSensor : AppCompatActivity() , SensorEventListener{
    private lateinit var mgr : SensorManager
    private var temp : Sensor? = null
    private lateinit var tempTextView : TextView
    private  val msg = StringBuilder()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_u4_temperature_sensor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mgr = getSystemService(SENSOR_SERVICE) as SensorManager
        temp  = mgr.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        tempTextView = findViewById(R.id.tempTextView)

    }

    override fun onResume() {
        super.onResume()
        mgr.registerListener(this,temp,SensorManager.SENSOR_DELAY_NORMAL)

    }

    override fun onPause() {
        super.onPause()
        mgr.unregisterListener(this,temp)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event.let {
            val farenheit = event!!.values[0]*9/5 +32
            msg.insert(0,"Got a sensor event: [ ${event.values[0]} C : $farenheit F ]\n")
            tempTextView.text = msg
            tempTextView.invalidate()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//      "Not yet implemented"
    }
}