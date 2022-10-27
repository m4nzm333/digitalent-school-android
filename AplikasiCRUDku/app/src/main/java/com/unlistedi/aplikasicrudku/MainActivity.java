package com.unlistedi.aplikasicrudku;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    ListView lvStudent;
    Button btnTambah;
    DatabaseHelper databaseHelper;
    ArrayList dataId, dataNama, dataAlamat;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvStudent = findViewById(R.id.lvStudent);
        btnTambah = findViewById(R.id.btnTambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TambahActivity.class);
                startActivityForResult(intent, 200);
            }
        });
        // Buat Tangkap Data Student
        databaseHelper = new DatabaseHelper(getApplicationContext());
        dataId = databaseHelper.getStudentId();
        dataNama = databaseHelper.getStudentNama();
        dataAlamat = databaseHelper.getStudentAlamat();
        // Set Konfigurasi ListView
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1);
        adapter.addAll(dataNama);
        adapter.notifyDataSetChanged();
        lvStudent.setAdapter(adapter);
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                // Bikin Tampilan menu Pop Up
                final CharSequence[] dialogitem = {"Update", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Menu");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0){
                            // Jalankan Intent Update dan kirim data yang diperlukan
                            Intent intent = new Intent(MainActivity.this, EditActivity.class);
                            intent.putExtra("id", dataId.get(position).toString());
                            intent.putExtra("nama", dataNama.get(position).toString());
                            intent.putExtra("alamat", dataAlamat.get(position).toString());
                            startActivity(intent);
                        }
                        if (item == 1){
                            // Hapus melalui DB Helper
                            databaseHelper.deleteStudent(dataId.get(position).toString());
                            // Aktivitas Dimuat Ulang
                            recreate();
                        }
                    }
                });
                builder.create().show();
            }
        });

        // Request
        ApiHelper apiHelper = new ApiHelper(getApplicationContext());
        try {
            Toast.makeText(this, apiHelper.getSiswa(), Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            Toast.makeText(this, "Gagal1", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (ExecutionException e) {
            Toast.makeText(this, "Gagal2", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        } catch (TimeoutException e) {
            Toast.makeText(this, "Gagal3", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    // Fungsi ini agar data terupdate saat kembali
    @Override
    protected void onResume() {
        super.onResume();
        // Ambil Data dari DB Helper
        dataId = databaseHelper.getStudentId();
        dataNama = databaseHelper.getStudentNama();
        dataAlamat= databaseHelper.getStudentAlamat();
        // Set ulang adapter
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, dataNama);
        lvStudent.setAdapter(adapter);
    }
}
