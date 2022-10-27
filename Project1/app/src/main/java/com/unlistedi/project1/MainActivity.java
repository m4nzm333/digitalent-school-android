package com.unlistedi.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvCatatan;
    Button btnTambahCatatan;
    // Variabel Buat Ambil Daftar File
    String[] daftarFile;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAppPermissions();

        lvCatatan = findViewById(R.id.lvCatatan);
        btnTambahCatatan = findViewById(R.id.btnTambahCatatan);

        // Ambil Data Direktori
        File myDir = getApplicationContext().getFilesDir();
        File lister = myDir.getAbsoluteFile();
        daftarFile = lister.list();
        // Adapter Buat List View
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1, daftarFile);
        lvCatatan.setAdapter(adapter);
        lvCatatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), TambahCatatanActivity.class);
                intent.putExtra("DataCatatan", daftarFile[position]);
                startActivity(intent);
            }
        });
        // Pada saat daftar di klik panjang
        lvCatatan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Konfigurasi PopUp
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this, R.style.Theme_AppCompat_Dialog);
                alertDialogBuilder.setMessage("Ingin Menghapus File "+ daftarFile[position] + " ?")
                        .setCancelable(true)
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                File file = new File(getFilesDir(), daftarFile[position]);
                                if (file.exists()){
                                    file.delete();
                                    Toast.makeText(MainActivity.this, daftarFile[position] + "Dihapus", Toast.LENGTH_SHORT).show();
                                    recreate();
                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                return true;
            }
        });

        btnTambahCatatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TambahCatatanActivity.class);
                startActivity(intent);
            }
        });
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
