package com.unlistedi.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class TambahCatatanActivity extends AppCompatActivity {
    TextView lblTambah;
    EditText tvNamaFile, tvIsiFile;
    Button btnTambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_catatan);

        tvNamaFile = findViewById(R.id.tvNamaFile);
        tvIsiFile = findViewById(R.id.tvIsiFile);
        btnTambah = findViewById(R.id.btnTambah);
        lblTambah = findViewById(R.id.lblTambah);

        // Ambil Data dari Activity Sebelah
        String dataCatatan = getIntent().getStringExtra("DataCatatan");
        // Cek Jika data Catatan
        if (dataCatatan != null){
            Toast.makeText(this, dataCatatan, Toast.LENGTH_SHORT).show();
            // Simpan Data Ke Isian Nama File
            tvNamaFile.setText(dataCatatan);
            // Baca Data dari nama File dataCatatan
            File sdcard = getFilesDir();
            File file = new File(sdcard, dataCatatan);
            // Jika File Ada?
            if (file.exists()) {
                lblTambah.setText("Edit Catatan");
                btnTambah.setText("Simpan");
                StringBuilder text = new StringBuilder();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line = br.readLine();
                    while (line != null) {
                        text.append(line);
                        line = br.readLine();
                    }
                    br.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Set Isi dari File
                tvIsiFile.setText(text.toString());
            }
        }


        // Kejadian Saat btnTambah di tekan
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isiFile = tvIsiFile.getText().toString();
                File fileBaru = new File(getFilesDir(), tvNamaFile.getText().toString());
                FileOutputStream OutputStream;
                try {
                    fileBaru.createNewFile();
                    OutputStream = new FileOutputStream(fileBaru, false);
                    OutputStream.write(isiFile.getBytes());
                    OutputStream.flush();
                    OutputStream.close();
                    Toast.makeText(getApplicationContext(), "Buat file berhasil", Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Ada error buat file.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
