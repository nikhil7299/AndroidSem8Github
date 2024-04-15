package com.example.sem8all

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class U4AccleroGyroDemo : AppCompatActivity() {
    private  lateinit var accelerometer : U4Accelerometer
    private  lateinit var  gyroscope: U4Gyroscope
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_u4_acclero_gyro_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainAcceleroGyroDemo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        accelerometer = U4Accelerometer(this)
        gyroscope = U4Gyroscope(this)
        accelerometer.setListener(object : U4Accelerometer.Listener{
            override fun onTranslation(tx: Float, ty: Float, ts: Float) {
                if (tx>1.0f){
                    window.decorView.setBackgroundColor(Color.RED)
                }
                else if(tx < -1.0f){
                    window.decorView.setBackgroundColor(Color.BLUE)
                }
            }
        })

        gyroscope.setListener(object  : U4Gyroscope.Listener{
            override fun onRotation(tx: Float, ty: Float, tz: Float) {
                if(tz >1.0f){
                    window.decorView.setBackgroundColor(Color.GREEN)

                }
                else if (tz < -1.0f){
                    window.decorView.setBackgroundColor(Color.YELLOW)
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()
        accelerometer.register()
        gyroscope.register()
    }

    override fun onPause() {
        super.onPause()
        accelerometer.unregister()
        gyroscope.unregister()
    }
}