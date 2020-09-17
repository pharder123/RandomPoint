package com.example.randopointtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.concurrent.Executor;

public class MainActivity extends AppCompatActivity {

    private GoogleMap mMap;
    static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 917;
    static Executor ex = ContextCompat.getMainExecutor();
    private boolean permissionsGranted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
    }

    public void getLatLong(View view) {

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        // I suppressed the missing-permission warning because this wouldn't be executed in my
        // case without location services being enabled
        @SuppressLint("MissingPermission") android.location.Location currentLocation =
                locationManager.getCurrentLocation(locationManager.getAllProviders().get(0), null, ContextCompat.getMainExecutor(this), );
        double userLat = currentLocation.getLatitude();
        double userLong = currentLocation.getLongitude();

        TextView textView = findViewById(R.id.txtMessage);
        textView.setText("current Latitude: " + userLat + "/n" + "current Longitude: " + userLong);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantresults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                if (grantresults.length > 0 && grantresults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionsGranted = true;
                } else {
                    permissionsGranted = false;
                }
                return;
            }
        }
    }

}