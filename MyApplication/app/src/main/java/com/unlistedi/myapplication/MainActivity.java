package com.unlistedi.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button btnStore, btnGetAll;
    private EditText edtName;
    private DatabaseHelper databaseHelper;
    private ArrayList<String> arrayList;
    // Buat Variabel ListView
    private ListView lvStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper=new DatabaseHelper(this);
        btnStore=findViewById(R.id.btnStore);
        btnGetAll=findViewById(R.id.btnGet);
        edtName=findViewById(R.id.edtName);
        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.addSTudentDetail(edtName.getText().toString());
                edtName.setText("");
                Toast.makeText(MainActivity.this, "Stored Succesfully",
                Toast.LENGTH_SHORT).show();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList = databaseHelper.getAllStudentsList();
                // Masukkan Kedalam List View
                ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);
                lvStudent = findViewById(R.id.lvStudent);
                // Set ADapater List View
                lvStudent.setAdapter(adapter);
                // Listener Pada saat dia tekan lama
                lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        databaseHelper.deleteStudent(arrayList.get(position));
                        btnGetAll.callOnClick();
                        // True Supaya Tidak Ada Dieksekusi yang lain
                        return true;
                    }
                });
            }
        });
    }
}
