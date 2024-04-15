package com.example.sem8all

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class U2CanvasGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val obj = U2CanvasGameView(this)

        setContentView(obj)
//        setContentView(R.layout.activity_u2_canvas_game)
    }
}