package com.example.sem8all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class U2Canvas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_u2_canvas)

        val drawArc = U2CanvasArc(this)
        setContentView(drawArc)
    }
}