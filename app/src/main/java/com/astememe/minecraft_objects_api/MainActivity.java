package com.astememe.minecraft_objects_api;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public ArrayList<McItemInfo> mcItemInfoArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    TextInputEditText busqueda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillItems();

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.lista);
        busqueda = findViewById(R.id.busqueda);
    }

    private void filtrarTexto(String texto, RecyclerViewAdapter adapter) {
        ArrayList<McItemInfo> filtrados = new ArrayList<>();

        for (McItemInfo item : mcItemInfoArrayList) {
            if (item.getTitulo().toLowerCase().contains(texto.toLowerCase())) {
                filtrados.add(item);
            }
        }

        adapter.filtrar(filtrados);
    }


    public void fillItems() {
        ApiInterface appInterface = APIClient.getRetrofitInstance().create(ApiInterface.class);
        Call<List<McItemAPIResponse>> call = appInterface.getAllItems();
        call.enqueue(new Callback<List<McItemAPIResponse>>() {
            @Override
            public void onResponse(Call<List<McItemAPIResponse>> call, Response<List<McItemAPIResponse>> response) {
                String nombre = "";
                String descripcion = "";
                String enlace = "";
                Log.d("Código de acierto: ", response.code()+"");
                List<McItemAPIResponse> items = response.body();
                for (McItemAPIResponse item : items) {
                    nombre = item.mcItemNombre;
                    descripcion = item.mcItemDescripcion;
                    enlace = item.mcItemImageURL;
                    mcItemInfoArrayList.add(new McItemInfo(nombre, descripcion, enlace));
                }

                RecyclerViewAdapter adapter = new RecyclerViewAdapter(mcItemInfoArrayList, getApplicationContext());
                GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);

                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);


                //https://www.simplifiedcoding.net/search-functionality-recyclerview/
                busqueda.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        filtrarTexto(s.toString(), adapter);
                    }
                });



            }

            @Override
            public void onFailure(Call<List<McItemAPIResponse>> call, Throwable t) {
                Log.d("Error!!", t.toString());
            }
        });
    }
}