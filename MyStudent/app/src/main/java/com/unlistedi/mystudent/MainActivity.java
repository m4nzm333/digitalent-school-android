package com.unlistedi.mystudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnAdd;

    ArrayList<Siswa> arrayListSiswa;
    RecyclerView rvSiswa;
    SiswaAdapter siswaAdapter;
    RecyclerView.LayoutManager siswaLayoutManager;
    SiswaDatabaseHelper siswaDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        rvSiswa = findViewById(R.id.rvSiswa);

        siswaDatabaseHelper = new SiswaDatabaseHelper(getApplicationContext());
        arrayListSiswa = siswaDatabaseHelper.getAllSiswa();
        siswaAdapter = new SiswaAdapter(getApplicationContext(), arrayListSiswa);
        siswaLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvSiswa.setLayoutManager(siswaLayoutManager);
        rvSiswa.setAdapter(siswaAdapter);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        arrayListSiswa = siswaDatabaseHelper.getAllSiswa();
        siswaAdapter.setData(arrayListSiswa);
    }
}
