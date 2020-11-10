package com.reo.running.runningactivity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.location.Location;
import android.location.LocationListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

        private GoogleMap mMap;
        private Button runBtn;
        private LocationManager locationManager;
        private Location location;
        double latNum,lonNum;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            runBtn = findViewById(R.id.runBtn);
        }

        @Override
        protected void onResume() {
            super.onResume();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                return;
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,1,this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,1,this);
        }

        @Override
        protected void onStop() {
            super.onStop();

            if(locationManager != null) {
                if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.removeUpdates(this);
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
        @Override
        public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            latNum = location.getLatitude();
            lonNum = location.getLongitude();

            //Add a marker in Sydney and move the camera
            LatLng japan = new LatLng(latNum, lonNum);
            mMap.addMarker(new MarkerOptions().position(japan).title("走ろうよ！"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(japan));
        }

        @Override

        public void onLocationChanged(@NonNull Location location) {
            String text = "緯度：" + location.getLatitude() + "経度 :" + location.getLongitude();
            runBtn.setText(text);



        }

        @Override

        public void onStatusChanged(String provider, int status ,Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }