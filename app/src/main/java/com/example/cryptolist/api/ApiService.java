package com.example.cryptolist.api;

import com.example.cryptolist.model.Item;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("adancondori/TareaAPI/refs/heads/main/api/criptomonedas.json")
    Call<List<Item>> getItems();
} 