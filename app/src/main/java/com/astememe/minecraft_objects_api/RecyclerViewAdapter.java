package com.astememe.minecraft_objects_api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    private ArrayList<McItemInfo> mcItemInfoArrayList;
    private Context context;

    public RecyclerViewAdapter(ArrayList<McItemInfo> itemInfoArrayList, Context context) {
        this.mcItemInfoArrayList = itemInfoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mc_item_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        McItemInfo mcItemInfo = mcItemInfoArrayList.get(position);
        holder.tituloTV.setText(mcItemInfo.getTitulo());
        holder.descripcionTV.setText(mcItemInfo.getDescripcion());
        Glide.with(context)
                .load(mcItemInfo.getImagen())
                .centerCrop()
                .into(holder.imagenIV);

    }

    @Override
    public int getItemCount() {
        return mcItemInfoArrayList.size();
    }


    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloTV;
        private TextView descripcionTV;
        private ImageView imagenIV;

        private RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTV = itemView.findViewById(R.id.titulo);
            descripcionTV = itemView.findViewById(R.id.descripcion);
            imagenIV = itemView.findViewById(R.id.imagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, tituloTV.getText().toString(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
