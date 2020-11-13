package com.reo.running.runningactivity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : FragmentActivity(), OnMapReadyCallback, LocationListener {
    private var mMap: GoogleMap? = null
    private var runBtn: Button? = null
    private var locationManager: LocationManager? = null
    private val location: Location? = null
    var latNum = 0.0
    var lonNum = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        runBtn = findViewById(R.id.runBtn)
    }

    override fun onResume() {
        super.onResume()
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }
        locationManager!!.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1f, this)
        locationManager!!.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 1f, this)
    }

    override fun onStop() {
        super.onStop()
        if (locationManager != null) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return
            }
            locationManager!!.removeUpdates(this)
        }
    }

    /*
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.*/
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (location != null) {
            latNum = location.latitude
            lonNum = location.longitude
        }

        //Add a marker in current location and move the camera
        val current = LatLng(latNum, lonNum)
        mMap!!.addMarker(MarkerOptions().position(current).title("走ろうよ！"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(current))
    }

    override fun onLocationChanged(location: Location) {}
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    override fun onProviderEnabled(provider: String) {}
    override fun onProviderDisabled(provider: String) {}
}