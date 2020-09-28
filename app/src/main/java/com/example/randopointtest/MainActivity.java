package com.example.randopointtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private TextView coordinates;
    private TextView randomNumber;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinates = findViewById(R.id.txtMessage);
        randomNumber = findViewById(R.id.txtMessage2);

        rand = new Random();

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getRandomNumber(View view) {
        int rand_int1 = rand.nextInt(1000);
        int rand_int2 = rand.nextInt(1000);
        String output = rand_int1 + ", " + rand_int2;
        randomNumber.setText(output);
    }

    public void getLocation(View view) {
        gpsTracker = new GpsTracker(MainActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            String LATLONG = latitude + ", " + longitude;

            coordinates.setText(LATLONG);
        } else {
            gpsTracker.showSettingsAlert();
        }
    }

}