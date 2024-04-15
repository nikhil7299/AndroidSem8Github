package com.example.sem8all

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class U5Bluetooth : AppCompatActivity() {
    private lateinit var devicesListView : ListView

    private  var arrayAdapter  : ArrayAdapter<String>?=null
    private var bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    private lateinit var bluetoothBtn : Button
    private lateinit var bluetoothTV : TextView
    @SuppressLint( "MissingInflatedId", "MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_u5_bluetooth)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //
        bluetoothBtn = findViewById(R.id.bluetoothBtn)
        bluetoothTV = findViewById(R.id.bluetoothTV)
//        val bluetoothManager: BluetoothManager = getSystemService(BluetoothManager::class.java)
//        val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.getAdapter()

        bluetoothBtn.setOnClickListener {
            if(bluetoothAdapter.isEnabled){
                bluetoothAdapter.disable()
//                val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                startActivityForResult(enableBtIntent,1)
                bluetoothTV.text = "Bluetooth Status : OFF"
            }
            else{
                bluetoothAdapter.enable()
                bluetoothTV.text = "Bluetooth Status : ON"
            }
        }

        findViewById<Button>(R.id.getPairedDevicesBtn).setOnClickListener {
            if(bluetoothAdapter == null){
                Toast.makeText(this,"Bluetooth Not Supported",Toast.LENGTH_LONG).show()
            }
            else{
                val pairedDevices = bluetoothAdapter.bondedDevices
                val list = ArrayList<String>()
                if(pairedDevices.size >0){
                    for (device in pairedDevices){
                        val deviceName = device.name
                        val macAddress = device.address
                        list.add("Name: $deviceName | MAC Address : $macAddress")
                    }
                    devicesListView = findViewById(R.id.devicesListView)
                    arrayAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,list)
                    devicesListView.adapter = arrayAdapter
                }
            }
        }

    }
}