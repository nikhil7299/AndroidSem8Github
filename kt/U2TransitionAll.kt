package com.example.sem8all

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class U2TransitionAll : AppCompatActivity() {
    lateinit var button: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u2_transition_all)
        button=findViewById(R.id.btnTransitionAll)
        button.setOnClickListener {
            startActivity(Intent(this@U2TransitionAll,MainActivity::class.java))
            // for right to left animation
            // overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left);

            // for bottom to top animation
            // overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_to_top);

            // for top to bottom animation
            // overridePendingTransition(R.anim.slide_from_top,R.anim.slide_to_bottom);

            // zoom-in animation
            // overridePendingTransition(R.anim.zoom_in,R.anim.static_animation);

            // zoom-out animation

            overridePendingTransition(R.anim.sequential_anim,R.anim.zoom_out_anim);
        }
    }
}