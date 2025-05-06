package com.example.cryptolist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cryptolist.model.Item;

public class FormActivity extends AppCompatActivity {
    private EditText etNombre;
    private EditText etSimbolo;
    private EditText etAnio;
    private EditText etTipo;
    private EditText etDescripcion;
    private EditText etImagen;
    private Button btnGuardar;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        // Inicializar vistas
        etNombre = findViewById(R.id.etNombre);
        etSimbolo = findViewById(R.id.etSimbolo);
        etAnio = findViewById(R.id.etAnio);
        etTipo = findViewById(R.id.etTipo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etImagen = findViewById(R.id.etImagen);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Obtener item si es edición
        item = (Item) getIntent().getSerializableExtra("item");
        if (item != null) {
            // Cargar datos para edición
            etNombre.setText(item.getNombre());
            etSimbolo.setText(item.getSimbolo());
            etAnio.setText(String.valueOf(item.getAnioCreacion()));
            etTipo.setText(item.getTipo());
            etDescripcion.setText(item.getDescripcion());
            etImagen.setText(item.getImagen());
        }

        btnGuardar.setOnClickListener(v -> guardarItem());
    }

    private void guardarItem() {
        String nombre = etNombre.getText().toString();
        String simbolo = etSimbolo.getText().toString();
        int anio = Integer.parseInt(etAnio.getText().toString());
        String tipo = etTipo.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String imagen = etImagen.getText().toString();

        if (item == null) {
            // Crear nuevo item
            item = new Item();
        }

        item.setNombre(nombre);
        item.setSimbolo(simbolo);
        item.setAnioCreacion(anio);
        item.setTipo(tipo);
        item.setDescripcion(descripcion);
        item.setImagen(imagen);

        Intent resultIntent = new Intent();
        resultIntent.putExtra("item", item);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
} 