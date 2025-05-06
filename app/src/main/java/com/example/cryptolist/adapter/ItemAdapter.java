package com.example.cryptolist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.cryptolist.R;
import com.example.cryptolist.model.Item;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> items;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
        void onDeleteClick(Item item);
    }

    public ItemAdapter(List<Item> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_crypto, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<Item> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    public void addItem(Item item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void removeItem(Item item) {
        int position = items.indexOf(item);
        if (position != -1) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvSimbolo;
        private TextView tvAnio;
        private ImageView ivImagen;
        private ImageView ivDelete;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvSimbolo = itemView.findViewById(R.id.tvSimbolo);
            tvAnio = itemView.findViewById(R.id.tvAnio);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }

        public void bind(final Item item, final OnItemClickListener listener) {
            tvNombre.setText(item.getNombre());
            tvSimbolo.setText(item.getSimbolo());
            tvAnio.setText(String.valueOf(item.getAnioCreacion()));

            Glide.with(itemView.getContext())
                    .load(item.getImagen())
                    .placeholder(R.drawable.placeholder_image)
                    .into(ivImagen);

            itemView.setOnClickListener(v -> listener.onItemClick(item));
            ivDelete.setOnClickListener(v -> listener.onDeleteClick(item));
        }
    }
} 