package com.unlistedi.aplikasicrudku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {
    EditText etNamaEdit, etAlamatEdit;
    Button btnEditData;
    final CharSequence[] dialogitem = {"Edit", "Hapus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        // Ikat Java dengan XML
        etNamaEdit = findViewById(R.id.etNamaEdit);
        etAlamatEdit = findViewById(R.id.etAlamatEdit);
        btnEditData = findViewById(R.id.btnEditData);
        // Ambil Data dari Intent Sebelah
        final String id = getIntent().getStringExtra("id");
        final String nama = getIntent().getStringExtra("nama");
        final String alamat = getIntent().getStringExtra("alamat");
        // Set Edit Text
        etNamaEdit.setText(nama);
        etAlamatEdit.setText(alamat);
        // Kejadian Pada saat tombol Update ditekan
        btnEditData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tangkap Datanya Input
                String nama2 = etNamaEdit.getText().toString();
                String alamat2 = etAlamatEdit.getText().toString();
                // Update data melalui database Helper
                DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
                databaseHelper.updateStudent(id, nama2, alamat2);
                // Tutup Aktivitas
                finish();
            }
        });
    }
}
