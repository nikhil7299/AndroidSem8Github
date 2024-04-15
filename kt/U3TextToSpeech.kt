package com.example.sem8all

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Locale

class U3TextToSpeech : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var iv_mic: ImageView
    private var tv_Speech_to_text: TextView? = null
    private val REQUEST_CODE_SPEECH_INPUT = 1
    private lateinit var etSpeak : EditText
    private lateinit var btnSpeak : Button
    private lateinit var tts : TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u3_text_to_speech)
        iv_mic = findViewById(R.id.vectorMic)
        tv_Speech_to_text = findViewById(R.id.textMic)
        etSpeak = findViewById(R.id.etSpeak)
        btnSpeak = findViewById(R.id.speakBtn)

        tts = TextToSpeech(this,this)
        btnSpeak!!.setOnClickListener { speakOut() }



        iv_mic.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).
            apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
                putExtra(
                    RecognizerIntent.EXTRA_PROMPT,
                    "Speak to text"
                ) }
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                Toast.makeText(
                    this, " ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.US)
            if(result  == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS","Language not supported")
            }
            else{
                btnSpeak!!.isEnabled = true
            }

        }
    }
    private fun speakOut(){
        val text = etSpeak!!.text.toString()
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    override fun onDestroy() {
        if(tts !=null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                val result: ArrayList<String>? =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                tv_Speech_to_text!!.text = result?.get(0)
            }
        }
    }
}