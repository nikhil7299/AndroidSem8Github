package com.example.sem8all

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.view.MotionEvent
import android.view.View

class U2CanvasArc(context: Context?): View(context) {
    var paint1 = Paint()
    var paint2 = Paint()
    var x = 100
    var l=100f
    var b =80f
    var r1l = 200
    var r1r = 600
    var r2l = 800
    var r2r = 1200
    //    private var motion
    override fun onDraw(canvas: Canvas) {
        paint2.color = Color.CYAN
        paint2.style = Paint.Style.FILL
        var r: Rect? = Rect(r1l, 400, r1r, 80)
        var r2: Rect? = Rect(r2l, 400, r2r, 80)
        canvas?.drawArc(400f,600f,1000f,1200f,x.toFloat(),30f,true,paint2)
        canvas?.drawArc(400f,600f,1000f,1200f,(x+120).toFloat(),30f,true,paint2)
        canvas?.drawArc(400f,600f,1000f,1200f,(x+240).toFloat(),30f,true,paint2)
        canvas?.drawArc(400f,600f,1000f,1200f,x.toFloat(),30f,true,paint2)
        canvas?.drawArc(400f,600f,1000f,1200f,(x+120).toFloat(),30f,true,paint2)
        canvas?.drawArc(400f,600f,1000f,1200f,(x+240).toFloat(),30f,true,paint2)
        val rf = RectF(l, b,l,b)
        canvas?.drawRect(rf,paint1)
        canvas?.drawRect(l,b,l,b,paint1)
        paint1.setStyle(Paint.Style.FILL)
        paint1.setColor(Color.MAGENTA)
        r?.let { canvas?.drawRect(it, paint1) }
//
        paint1.setStyle(Paint.Style.FILL)
        paint1.setColor(Color.GREEN)
        r2?.let { canvas?.drawRect(it, paint1) }

//   border
        paint1.setStyle(Paint.Style.FILL)
        paint1.setColor(Color.RED)
        r?.let { canvas?.drawRect(it, paint1) }

        super.onDraw(canvas)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_UP->startFan()
//            MotionEvent.ACTION_UP->stopFan()
            MotionEvent.ACTION_MOVE ->move()
            MotionEvent.ACTION_DOWN -> changeAgain()
        }
        return true
    }
    private fun move(){
        r1l=r1l+20
        r1r = r1r +20
        r2l = r2l - 20
        r2r  = r2r -20
        invalidate()
    }
    private fun changeAgain(){
        r1l=r1l-20
        r1r = r1r -20
        r2l = r2l + 20
        r2r  = r2r +20
        invalidate()
    }
    fun startFan(){
        x= x+20
        invalidate()
    }
    fun stopFan(){
//        x=100
//        invalidate()
    }
}