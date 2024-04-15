package com.example.sem8all

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class U5WifiEnableDisable : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_u5_wifi_enable_disable)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val wifi = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        var wifiStateTV =findViewById<TextView>(R.id.wifiStateTV)
        findViewById<Button>(R.id.enableWifiBtn).setOnClickListener{
            wifi.isWifiEnabled = true
            Toast.makeText(this,"Wifi is ON", Toast.LENGTH_LONG).show()
        }
        findViewById<Button>(R.id.disableWifiBtn).setOnClickListener {

            wifi.isWifiEnabled = false
            Toast.makeText(this,"Wifi is OFF", Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.wifiSwitchBtn).setOnClickListener{
            wifi.isWifiEnabled = !wifi.isWifiEnabled
            if(wifi.isWifiEnabled){
                wifiStateTV.text = "WIFI is ON"
            }
            else{
                wifiStateTV.text = "WIFI is OFF"

            }
        }
    }
}