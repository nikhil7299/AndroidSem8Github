package com.example.sem8all

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class U3AddToLoc : AppCompatActivity() {
    private  lateinit var editAddress : EditText
    private lateinit var txtViewLat : TextView
    private lateinit var txtViewLon : TextView
    private lateinit var btnGetLocation : Button
    private  lateinit var  btnShowMap : Button
    private var latitude : Double = 0.0
    private  var longitude : Double = 0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u3_add_to_loc)

        editAddress = findViewById(R.id.editAddress)
        txtViewLat = findViewById(R.id.txtViewLat)
        txtViewLon = findViewById(R.id.txtViewLon)
        btnGetLocation = findViewById(R.id.btnGetLocation)
        btnShowMap = findViewById(R.id.btnShowMap)

        btnGetLocation.setOnClickListener{
            if( editAddress.text.isEmpty()){
                Toast.makeText(this,"Enter the required field", Toast.LENGTH_LONG).show()
            }
            else{
                getLocationFromAddress(editAddress.text.toString())
            }
        }
        btnShowMap.setOnClickListener {
            val geoMapsUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW,geoMapsUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
    private fun getLocationFromAddress(address: String){
        val geocoder : Geocoder = Geocoder(this)
        val list : List<Address> = geocoder.getFromLocationName(address,5)!!
        if(list.isEmpty()){
            return
        }
        txtViewLat.text = "Latitude : ${list[0].latitude}"
        txtViewLon.text = "Longitude: ${list[0].longitude}"
        latitude = list[0].latitude
        longitude = list[0].longitude
    }
}