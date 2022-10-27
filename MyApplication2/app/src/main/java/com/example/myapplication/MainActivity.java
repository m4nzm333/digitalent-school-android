package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.location.LocationManager;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager ;
    boolean GpsStatus ;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        CheckGpsStatus();
        if (GpsStatus == true) {
            Toast.makeText(this, "gps is enabled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "akses lokasi tidak aktif, nyalakan gps anda", Toast.LENGTH_SHORT).show();
        }
    }

    public void adakah(View view) {
        FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    // Do it all with location
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
                    Toast.makeText(MainActivity.this,
                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
                            Toast.LENGTH_LONG).show();
                    textView=findViewById(R.id.textView2);
                    textView.setText(String.valueOf(location.getLatitude()));

                }else{
                    textView=findViewById(R.id.textView2);
                    textView.setText("location undefined");
                }
            }
        });
    }
    public void CheckGpsStatus(){

        locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        GpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public void kenipah(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.google.com/maps/@-5.1153527,119.5137473,17z"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
