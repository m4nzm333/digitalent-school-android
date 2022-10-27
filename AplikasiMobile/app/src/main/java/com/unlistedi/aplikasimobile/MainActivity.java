package com.unlistedi.aplikasimobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txtNama;
    Button btnTampilNama;
    String namaTampil;
    TextView tvNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNama = findViewById(R.id.txtNama);
        btnTampilNama = findViewById(R.id.btnTampilNama);
        tvNama = findViewById(R.id.tvNama);

        btnTampilNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaTampil = txtNama.getText().toString();
                tvNama.setText(namaTampil);
                Toast.makeText(MainActivity.this, namaTampil, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
