package com.unlistedi.recyclerviewnegara;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class NegaraAdapter extends RecyclerView.Adapter<NegaraViewHolder> {
    Context context;
    String[] namaNegara;
    int[] fotoNegara;

    public NegaraAdapter(Context context, String[] namaNegara, int[] fotoNegara){
        this.context = context;
        this.namaNegara = namaNegara;
        this.fotoNegara = fotoNegara;
    }

    @NonNull
    @Override
    public NegaraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        View viewItem = layoutInflater.inflate(R.layout.item_negara, parent, false);
        return new NegaraViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull NegaraViewHolder holder, final int position) {
        holder.tvNegara.setText(this.namaNegara[position]);
        holder.ivNegara.setImageResource(this.fotoNegara[position]);
        holder.ivNegara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NegaraDetail.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.namaNegara.length;
    }
}

class NegaraViewHolder extends RecyclerView.ViewHolder {
    TextView tvNegara;
    ImageView ivNegara;
    public NegaraViewHolder(@NonNull View itemView) {
        super(itemView);
        tvNegara = itemView.findViewById(R.id.tvNamaNegara);
        ivNegara = itemView.findViewById(R.id.ivFotoNegara);
    }
}
