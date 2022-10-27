package com.unlistedi.aplikasikalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    EditText etAngka1, etAngka2;
    Button btnTambah, btnKurang, btnKali, btnBagi, btnBersih;
    TextView tvHasil;
    double angka1, angka2, hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAngka1 = findViewById(R.id.etAngka1);
        etAngka2 = findViewById(R.id.etAngka2);
        btnTambah = findViewById(R.id.btnTambah);
        btnKurang = findViewById(R.id.btnKurang);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        btnBersih = findViewById(R.id.btnBersih);
        tvHasil = findViewById(R.id.tvHasil);
    }

    public void tambah(View view) {
        if(etAngka1.getText().toString().equals("") || etAngka2.getText().toString().equals("")){
            Toast.makeText(this, "Tolong masukkan angka.", Toast.LENGTH_SHORT).show();
        } else {
            angka1 = Double.valueOf(etAngka1.getText().toString());
            angka2 = Double.valueOf(etAngka2.getText().toString());
            hasil = angka1 + angka2;
            tvHasil.setText(String.valueOf(hasil));
        }
    }

    public void kurang(View view) {
        if(etAngka1.getText().toString().equals("") || etAngka2.getText().toString().equals("")){
            Toast.makeText(this, "Tolong masukkan angka.", Toast.LENGTH_SHORT).show();
        } else {
            angka1 = Double.valueOf(etAngka1.getText().toString());
            angka2 = Double.valueOf(etAngka2.getText().toString());
            hasil = angka1 - angka2;
            tvHasil.setText(String.valueOf(hasil));
        }
    }

    public void kali(View view) {
        if(etAngka1.getText().toString().equals("") || etAngka2.getText().toString().equals("")){
            Toast.makeText(this, "Tolong masukkan angka.", Toast.LENGTH_SHORT).show();
        } else {
            angka1 = Double.valueOf(etAngka1.getText().toString());
            angka2 = Double.valueOf(etAngka2.getText().toString());
            hasil = angka1 * angka2;
            tvHasil.setText(String.valueOf(hasil));
        }
    }

    public void bagi(View view) {
        if(etAngka1.getText().toString().equals("") || etAngka2.getText().toString().equals("")){
            Toast.makeText(this, "Tolong masukkan angka.", Toast.LENGTH_SHORT).show();
        } else {
            angka1 = Double.valueOf(etAngka1.getText().toString());
            angka2 = Double.valueOf(etAngka2.getText().toString());
            hasil = angka1 / angka2;
            tvHasil.setText(String.valueOf(hasil));
        }
    }

    public void bersihkan(View view) {
        etAngka1.setText("0");
        etAngka2.setText("0");
        tvHasil.setText("0");
    }
}
