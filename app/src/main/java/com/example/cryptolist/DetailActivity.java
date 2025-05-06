package com.example.cryptolist;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.cryptolist.model.Item;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Obtener el item del intent
        Item item = (Item) getIntent().getSerializableExtra("item");
        if (item != null) {
            // Configurar vistas
            TextView tvNombre = findViewById(R.id.tvNombre);
            TextView tvSimbolo = findViewById(R.id.tvSimbolo);
            TextView tvAnio = findViewById(R.id.tvAnio);
            TextView tvTipo = findViewById(R.id.tvTipo);
            TextView tvDescripcion = findViewById(R.id.tvDescripcion);
            ImageView ivImagen = findViewById(R.id.ivImagen);

            // Mostrar datos
            tvNombre.setText(item.getNombre());
            tvSimbolo.setText(item.getSimbolo());
            tvAnio.setText(String.valueOf(item.getAnioCreacion()));
            tvTipo.setText(item.getTipo());
            tvDescripcion.setText(item.getDescripcion());

            // Cargar imagen
            Glide.with(this)
                    .load(item.getImagen())
                    .placeholder(R.drawable.placeholder_image)
                    .into(ivImagen);
        }
    }
} 