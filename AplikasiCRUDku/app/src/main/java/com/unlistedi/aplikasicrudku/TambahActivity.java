package com.unlistedi.aplikasicrudku;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TambahActivity extends AppCompatActivity {

    EditText etNamaTambah, etAlamatTambah;
    Button btnTambahData;
    String nama, alamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);
        // Ikat Java dengan XML
        etNamaTambah = findViewById(R.id.etNamaTambah);
        etAlamatTambah = findViewById(R.id.etAlamatTambah);
        btnTambahData = findViewById(R.id.btnTambahData);

        // Jalan Pada Saat tombol ditekan
        btnTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tangkap Data
                nama = etNamaTambah.getText().toString();
                alamat = etAlamatTambah.getText().toString();
                // Bikin Intance DB Helper
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                // Jalankan Fungsi Insert dari DB Helper
                databaseHelper.insertStudent(nama, alamat);
                // Tutup Aktivitas
                databaseHelper.close();
                finish();
            }
        });

    }
}
