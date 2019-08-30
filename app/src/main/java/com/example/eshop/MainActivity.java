package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvBarang;
    
    private ArrayList<Barang> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvBarang = findViewById(R.id.rv_barang);
        rvBarang.setHasFixedSize(true);

        list.addAll(DataBarang.getListData());
        showRecyclerList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.findViewById(R.id.about).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent about = new Intent(MainActivity.this, Main3Activity.class);
                startActivity(about);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Aksesoris Market");
    }

    private void showSelectedBarang(Barang barang) {
        Toast.makeText(this, "Kamu memilih " + barang.getNama(), Toast.LENGTH_SHORT).show();
    }

    private void showRecyclerList() {
        rvBarang.setLayoutManager(new LinearLayoutManager(this));
        ListBarangAdapter listBarangAdapter = new ListBarangAdapter(list);
        rvBarang.setAdapter(listBarangAdapter);

        listBarangAdapter.setOnItemClickCallback(new ListBarangAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Barang data) {
                showSelectedBarang(data);
            }
        });
    }
}
