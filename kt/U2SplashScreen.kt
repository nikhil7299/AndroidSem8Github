package com.example.sem8all

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class U2SplashScreen : AppCompatActivity() {
    lateinit var imgSplash: ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u2_splash_screen)

        imgSplash = findViewById(R.id.imgSplash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        imgSplash.setImageResource(R.drawable.img)
        val anim = AnimationUtils.loadAnimation(this,R.anim.together_anim)
        imgSplash.startAnimation(anim)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
    }
