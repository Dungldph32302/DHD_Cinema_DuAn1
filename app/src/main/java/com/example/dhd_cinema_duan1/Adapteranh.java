package com.example.dhd_cinema_duan1;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

public class Adapteranh extends RecyclerView.Adapter<Adapteranh.ViewHolder> {

    private final Context context;
    private final ArrayList<anhmodel> list;

    public Adapteranh(Context context, ArrayList<anhmodel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_anh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.id.setText("ID: " + String.valueOf(list.get(position).getId()));

        File file = new File(list.get(position).getAnh());
        if (file.exists()) {
            Glide.with(context)
                    .load(Uri.parse("file://" + list.get(position).getAnh()))
                    .into(holder.anh);
        } else {
            Toast.makeText(context, "Ảnh không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anh;
        TextView id;

        public ViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.id);
            anh = view.findViewById(R.id.imght);
        }
    }
}
