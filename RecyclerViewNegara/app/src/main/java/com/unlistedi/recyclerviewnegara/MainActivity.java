package com.unlistedi.recyclerviewnegara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvNegara;
    RecyclerView.Adapter rvNegaraAdapter;
    RecyclerView.LayoutManager rvNegaraLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] namaNegara = new String[]{
                "Indonesia",
                "Filipina",
                "Thailand",
                "Malaysia"
        };
        int[] fotoNegara = new int[]{
            R.drawable.bendera_indo,
            R.drawable.bendera_filipina,
            R.drawable.bendera_thailand,
            R.drawable.bendera_malaysia
        };

        rvNegaraAdapter = new NegaraAdapter(getApplicationContext(), namaNegara, fotoNegara);
        rvNegaraLayoutManager = new LinearLayoutManager(getApplicationContext());

        rvNegara = findViewById(R.id.rvNegara);
        rvNegara.setLayoutManager(rvNegaraLayoutManager);
        rvNegara.setAdapter(rvNegaraAdapter);
}
}
