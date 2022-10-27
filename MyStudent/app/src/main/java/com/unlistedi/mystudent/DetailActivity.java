package com.unlistedi.mystudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {
    Button btnUpdate;
    EditText etNama, etAlamat, etLongitude, etLatitude;
    String nama, alamat;
    Double longitude, latitude;
    Siswa siswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        // Ambil Database
        final SiswaDatabaseHelper siswaDatabaseHelper = new SiswaDatabaseHelper(getApplicationContext());
        // Ikat XML dengan Java
        etNama = findViewById(R.id.etNama);
        etAlamat = findViewById(R.id.etAlamat);
        etLongitude = findViewById(R.id.etLongitude);
        etLatitude = findViewById(R.id.etLatitude);
        btnUpdate = findViewById(R.id.btnUpdate);

        // Apakah Data dari Sebelah Ada?
        if (getIntent().getStringExtra("id") != null){
            Intent thisIntent = getIntent();
            // Tarik Dari Data Intent Sebelah
            siswa = new Siswa(
                    thisIntent.getStringExtra("id"),
                    thisIntent.getStringExtra("nama"),
                    thisIntent.getStringExtra("alamat"),
                    thisIntent.getDoubleExtra("longitude", 0d),
                    thisIntent.getDoubleExtra("latitude", 0d)
                );
            // Set Data dari Intent Sebelah
            etNama.setText(siswa.nama);
            etAlamat.setText(siswa.alamat);
            etLongitude.setText(String.valueOf(siswa.longitude));
            etLatitude.setText(String.valueOf(siswa.latitude));
            nama = etNama.getText().toString();
            alamat = etAlamat.getText().toString();
            longitude = Double.valueOf(etLongitude.getText().toString());
            latitude = Double.valueOf(etLatitude.getText().toString());
            // Pada Saat Update ditekan maka SQLite Melakukan Update
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nama = etNama.getText().toString();
                    alamat = etAlamat.getText().toString();
                    longitude = Double.valueOf(etLongitude.getText().toString());
                    latitude = Double.valueOf(etLatitude.getText().toString());
                    // Panggil Fungsi Update dari Database Helper
                    siswaDatabaseHelper.updateSiswa(new Siswa(siswa.id, nama, alamat, longitude, latitude));
                    finish();
                }
            });
        } else {
            // Jika Tidak Ada...
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nama = etNama.getText().toString();
                    alamat = etAlamat.getText().toString();
                    longitude = Double.valueOf(etLongitude.getText().toString());
                    latitude = Double.valueOf(etLatitude.getText().toString());
                    siswaDatabaseHelper.addSiswa(new Siswa(null, nama, alamat, longitude, latitude));
                    finish();
                }
            });
        }


    }
}
