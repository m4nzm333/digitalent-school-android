package com.unlistedi.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView lvNegara;
    String[] dataNegara = new String[]{
            "Indonesia",
            "Malaysia",
            "Singapura",
            "Italia",
            "Thailand"
    };
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, dataNegara);

        lvNegara = findViewById(R.id.lvNegara);

        lvNegara.setAdapter(adapter);
        lvNegara.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.RED);
                Toast.makeText(MainActivity.this, "Anda mengklik "+ dataNegara[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
