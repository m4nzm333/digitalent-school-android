package com.unlistedi.mystudent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SiswaAdapter extends RecyclerView.Adapter<SiswaViewHolder> {
    ArrayList<Siswa> arrayListSiswa;
    Context context;
    // Kita set datanya ke dalam adapter
    public SiswaAdapter(Context context, ArrayList<Siswa> arrayListSiswa){
        this.context = context;
        this.arrayListSiswa = arrayListSiswa;
    }

    @NonNull
    @Override
    public SiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View viewItem = layoutInflater.inflate(R.layout.student_row, parent, false);
        return new SiswaViewHolder(viewItem);
    }

    public void setData(ArrayList<Siswa> arrayListSiswa){
        this.arrayListSiswa = arrayListSiswa;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SiswaViewHolder holder, int position) {
        final Siswa siswa = arrayListSiswa.get(position);
        holder.tvNama.setText(siswa.nama);
        holder.tvAlamat.setText(siswa.alamat);
        holder.tvLongitude.setText(siswa.longitude.toString());
        holder.tvLatitude.setText(siswa.latitude.toString());
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", siswa.id);
                intent.putExtra("nama", siswa.nama);
                intent.putExtra("alamat", siswa.alamat);
                intent.putExtra("longitude", siswa.longitude);
                intent.putExtra("latitude", siswa.latitude);
                context.startActivity(intent);
            }
        });
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SiswaDatabaseHelper siswaDatabaseHelper = new SiswaDatabaseHelper(context);
                siswaDatabaseHelper.deleteSiswa(siswa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayListSiswa.size();
    }
}

class SiswaViewHolder extends RecyclerView.ViewHolder {
    TextView tvNama, tvAlamat, tvLongitude, tvLatitude;
    Button btnUpdate, btnDelete;
    public SiswaViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNama = itemView.findViewById(R.id.tvNama);
        tvAlamat = itemView.findViewById(R.id.tvAlamat);
        tvLongitude = itemView.findViewById(R.id.tvLongitude);
        tvLatitude = itemView.findViewById(R.id.tvLatitude);
        btnUpdate = itemView.findViewById(R.id.btnUpdate);
        btnDelete = itemView.findViewById(R.id.btnDelete);
    }
}
