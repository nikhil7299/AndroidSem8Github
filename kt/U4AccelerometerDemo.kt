package com.example.sem8all

import android.annotation.SuppressLint
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

class U4AccelerometerDemo : AppCompatActivity() {
    private var accTextView : TextView? = null
    private var sm : SensorManager? = null
    private var list : List<Sensor>? = null
    private val sel : SensorEventListener = object  : SensorEventListener{
        override fun onSensorChanged(event: SensorEvent?) {
            val values = event?.values
            accTextView?.text = "x : ${values?.get(0)}\ny : ${values?.get(1)}\nz : ${values?.get(2)}"

        }
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            TODO("Not yet implemented")
        }
    }
    
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_u4_accelerometer_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accTextView = findViewById(R.id.accTextView)

        list = sm?.getSensorList(Sensor.TYPE_ACCELEROMETER)
        if(list?.isNotEmpty() == true){
            sm?.registerListener(sel,list?.get(0),SensorManager.SENSOR_DELAY_NORMAL)
        }
        else{
            Toast.makeText(baseContext,"Error : No Accelerometer",Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        if(list?.isNotEmpty() == true){
            sm?.unregisterListener(sel)
        }
        super.onStop()
    }
}