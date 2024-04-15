package com.example.sem8all

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat

class U2TransitionActivity : AppCompatActivity() {

    lateinit var imageView : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u2_transition)

        val fade = Fade()
        val pPM = Slide()
        pPM.duration =3000
//        window.enterTransition = fade
//        window.exitTransition = fade
        window.enterTransition = pPM
        window.exitTransition = pPM

        imageView = findViewById(R.drawable.img)

        imageView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,imageView, ViewCompat.getTransitionName(imageView)!!
            )
            startActivity(intent,options.toBundle())
        }

    }
}