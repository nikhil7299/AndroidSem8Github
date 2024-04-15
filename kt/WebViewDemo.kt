package com.example.sem8all

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebViewDemo : AppCompatActivity() {
    lateinit var webView : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view_demo)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()

        webView.loadUrl("https://google.com/")

        webView.settings.javaScriptEnabled = true
        webView.settings.setSupportZoom(true)

    }

    override fun onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack()
        }else {
            super.onBackPressed()
        }
    }
}