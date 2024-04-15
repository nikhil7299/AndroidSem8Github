package com.example.sem8all

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class U3LocToAdd : AppCompatActivity() {
    lateinit var tvLatitude: TextView
    lateinit var tvLongitude: TextView
    lateinit var tvCountryName: TextView
    lateinit var tvLocality: TextView
    lateinit var tvAddress: TextView
    lateinit var btnLocation: Button
    lateinit var btnLocMap: Button
    private  var latitude =0.0
    private  var longitude = 0.0
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_u3_loc_to_add)
        tvLatitude = findViewById(R.id.lat)
        tvLongitude = findViewById(R.id.lon)
        tvCountryName = findViewById(R.id.country)
        tvAddress = findViewById(R.id.address)
        tvLocality = findViewById(R.id.locality)
        btnLocation = findViewById(R.id.btnLocation)
        btnLocMap = findViewById(R.id.btnLocMap)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        btnLocation.setOnClickListener {
            getLocation()
        }

        btnLocMap.setOnClickListener{
            val geoMapsUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW,geoMapsUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                    location?.let {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val list: List<Address> =
                            geocoder.getFromLocation(location.latitude, location.longitude, 1)!!
                        latitude = list[0].longitude
                        longitude = list[0].longitude
                        tvLatitude.text = "Latitude : \t ${list[0].latitude}"
                        tvLongitude.text = "Longitude : \t ${list[0].longitude}"
                        tvCountryName.text = "Country Name : \t ${list[0].countryName}"
                        tvLocality.text = "Locality : \t ${list[0].locality}"
                        tvAddress.text = "Address : \t ${list[0].getAddressLine(0)}"
                    }
                }
            } else {
                Toast.makeText(this, "Please turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }

        } else {
            requestPermissions()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == permissionId){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                getLocation()

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        ),
            permissionId
        )
    }

    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    private fun checkPermissions(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

}
