package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Details extends AppCompatActivity {

    private ImageView imgPhoto;
    private TextView tvNama,tvDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Detail Barang");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgPhoto = findViewById(R.id.img_item_photo);
        tvNama = findViewById(R.id.tv_item_nama);
        tvDetails = findViewById(R.id.tv_item_barang);

        Bundle bundle = getIntent().getExtras();

        String Nama = bundle.getString("Nama");
        String Detail = bundle.getString("Detail");

        Glide.with(this)
                .load(bundle.getString("Photo"))
                .apply(new RequestOptions().override(350,550))
                .into(imgPhoto);
        tvNama.setText(Nama);
        tvDetails.setText(Detail);
    }
}
