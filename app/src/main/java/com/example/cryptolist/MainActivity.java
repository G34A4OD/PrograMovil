package com.example.cryptolist;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cryptolist.adapter.ItemAdapter;
import com.example.cryptolist.api.ApiService;
import com.example.cryptolist.model.Item;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> items = new ArrayList<>();
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(items, this);
        recyclerView.setAdapter(adapter);

        // Configurar BottomNavigationView
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_list) {
                // Ya estamos en la lista
                return true;
            } else if (itemId == R.id.nav_add) {
                // Abrir formulario de creación
                openFormActivity(null);
                return true;
            }
            return false;
        });

        // Configurar FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> openFormActivity(null));

        // Cargar datos
        loadItems();
    }

    private void loadItems() {
        apiService.getItems().enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    items.clear();
                    items.addAll(response.body());
                    adapter.updateItems(items);
                }
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {
                // Manejar error
            }
        });
    }

    private void openFormActivity(Item item) {
        // TODO: Implementar navegación al formulario
    }

    @Override
    public void onItemClick(Item item) {
        // TODO: Implementar navegación al detalle
    }

    @Override
    public void onDeleteClick(Item item) {
        new AlertDialog.Builder(this)
                .setTitle("Eliminar")
                .setMessage("¿Estás seguro de que deseas eliminar este elemento?")
                .setPositiveButton("Sí", (dialog, which) -> {
                    adapter.removeItem(item);
                })
                .setNegativeButton("No", null)
                .show();
    }
} 