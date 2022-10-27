package com.unlistedi.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnBuatFile, btnBacaFile, btnUbahFile, btnHapusFile;
    TextView tvData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBuatFile = findViewById(R.id.btnBuatFile);
        btnBacaFile = findViewById(R.id.btnBacaFile);
        btnUbahFile = findViewById(R.id.btnUbahFile);
        btnHapusFile = findViewById(R.id.btnHapusFile);
        tvData = findViewById(R.id.tvData);
        btnBuatFile.setOnClickListener(this);
        btnBacaFile.setOnClickListener(this);
        btnUbahFile.setOnClickListener(this);
        btnHapusFile.setOnClickListener(this);

        requestAppPermissions();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBuatFile :
                buatFile();
                break;
            case R.id.btnBacaFile :
                bacaFile();
                break;
            case R.id.btnUbahFile :
                Toast.makeText(this, "Ubah File di tekan", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnHapusFile :
                Toast.makeText(this, "Hapus file ditekan", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void buatFile(){
        String isiFile = "Temanku.";
        File fileBaru = new File(getFilesDir(), "FileBaruku.txt");
        FileOutputStream OutputStream = null;
        try {
            fileBaru.createNewFile();
            OutputStream = new FileOutputStream(fileBaru, true);
            OutputStream.write(isiFile.getBytes());
            OutputStream.flush();
            OutputStream.close();
            Toast.makeText(this, "Buat file berhasil", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "Ada error buat file.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
    void bacaFile(){
        File sdcard = getFilesDir();
        File file = new File(sdcard, "FileBaruku.txt");
        if (file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                Toast.makeText(this, "Bacafile di Tekan", Toast.LENGTH_SHORT).show();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tvData.setText(text.toString());
        }
    }

    private void requestAppPermissions() {
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }

        if (hasReadPermissions() && hasWritePermissions()) {
            return;
        }

        ActivityCompat.requestPermissions(this,
                new String[] {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 112); // your request code
    }

    private boolean hasReadPermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private boolean hasWritePermissions() {
        return (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
}
