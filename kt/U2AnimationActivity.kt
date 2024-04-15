package com.example.sem8all

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class U2AnimationActivity : AppCompatActivity() {

    lateinit var imgViewAnim: ImageView
    lateinit var btnFadeIn: Button
    lateinit var btnFadeOut: Button
    lateinit var btnRotate: Button
    lateinit var btnBounce: Button
    lateinit var btnSlideIn: Button
    lateinit var btnSlideDown: Button
    lateinit var btnFlip: Button
    lateinit var btnZoomIn: Button
    lateinit var btnZoomOut: Button
    lateinit var btnSequential: Button
    lateinit var btnSlideRight: Button
    lateinit var btnTogether: Button
    lateinit var animation: Animation

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u2_animation)
        imgViewAnim = findViewById(R.id.imgViewAnim)
        btnFadeIn = findViewById(R.id.btnFadeIn)
        btnFadeOut = findViewById(R.id.btnFadeOut)
        btnBounce = findViewById(R.id.btnBounce)
        btnRotate = findViewById(R.id.btnRotate)
        btnFlip = findViewById(R.id.btnFlip)
        btnSlideIn = findViewById(R.id.btnSlideUp)
        btnSlideDown = findViewById(R.id.btnSlideDown)
        btnZoomIn = findViewById(R.id.btnZoomIn)
        btnZoomOut = findViewById(R.id.btnZoomOut)
        btnSequential = findViewById(R.id.btnSequential)
        btnSlideRight = findViewById(R.id.btnSlideRight)
        btnTogether = findViewById(R.id.btnTogether)

        imgViewAnim.setImageResource(R.drawable.profile)
        /*
        val blink = AlphaAnimation(0.0f,1.0f)//transparent = 0,0 & opaque = 1,1
        blink.duration = 50
        blink.repeatMode- Animation.REVERSE
        blink.repeatCount = Animation.INFINITE
        blink.startOffset = 20
        imgViewAnim.startAnimation(blink)

         */

        btnFadeIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_in_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnFadeOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade_out_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnRotate.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.roatate_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnBounce.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.bounce_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnFlip.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.flip_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnSlideIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_up_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnSlideDown.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_down_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnZoomIn.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.zoom_in_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnZoomOut.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.zoom_out_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnSequential.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.sequential_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnSlideRight.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.slide_right_anim)
            imgViewAnim.startAnimation(animation)
        }
        btnTogether.setOnClickListener {
            animation = AnimationUtils.loadAnimation(applicationContext,R.anim.together_anim)
            imgViewAnim.startAnimation(animation)
        }
    }
}