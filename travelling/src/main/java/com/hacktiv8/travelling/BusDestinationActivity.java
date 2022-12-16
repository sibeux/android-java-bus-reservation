package com.hacktiv8.travelling;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BusDestinationActivity extends AppCompatActivity {

    Button search_buses;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bus_destination);
        search_buses = findViewById(R.id.search_buses);
        search_buses.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),BusPriceActivity.class);
            startActivity(intent);
        });
    }
}
