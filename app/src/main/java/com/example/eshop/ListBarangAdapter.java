package com.example.eshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListBarangAdapter extends RecyclerView.Adapter<ListBarangAdapter.ListViewHolder> {

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    private ArrayList<Barang> listBarang;

    public ListBarangAdapter(ArrayList<Barang> list) {
        this.listBarang = list;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_cc, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Barang barang = listBarang.get(position);

        Glide.with(holder.itemView.getContext())
                .load(barang.getPhoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);

        holder.tvNama.setText(barang.getNama());
        holder.tvDetail.setText(barang.getDetail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context =  v.getContext();
                onItemClickCallback.onItemClicked(listBarang.get(holder.getAdapterPosition()));
                Intent detail = new Intent(holder.itemView.getContext(),Details.class);
                detail.putExtra("Nama", listBarang.get(holder.getAdapterPosition()).getNama());
                detail.putExtra("Detail", listBarang.get(holder.getAdapterPosition()).getDetail());
                detail.putExtra("Photo", listBarang.get(holder.getAdapterPosition()).getPhoto());
                context.startActivity(detail);

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBarang.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvNama, tvDetail;

        ListViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvNama = itemView.findViewById(R.id.tv_item_nama);
            tvDetail = itemView.findViewById(R.id.tv_item_barang);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(Barang data);
    }
}
