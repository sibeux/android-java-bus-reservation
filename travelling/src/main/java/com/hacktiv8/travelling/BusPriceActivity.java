package com.hacktiv8.travelling;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class BusPriceActivity extends AppCompatActivity {

    CardView cardView;
    TextView kotaAsal, kotaTujuan, tanggalBerakat, hargaTotalText;
    private String asal, tujuan, tanggal, dewasa, anak, email, id_book;
    private Integer hargaTotalDewasa, hargaTotalAnak, hargaTotal;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bus_price);

        cardView = findViewById(R.id.cardView);
        kotaAsal = findViewById(R.id.kotaAsal);
        kotaTujuan = findViewById(R.id.kotaTujuan);
        tanggalBerakat = findViewById(R.id.tanggal_berangkat);
        hargaTotalText = findViewById(R.id.hargaTotal);

        Bundle data = getIntent().getExtras();

        asal = data.getString("asal");
        tujuan = data.getString("tujuan");
        tanggal = data.getString("tanggal");
        dewasa = data.getString("dewasa");
        anak = data.getString("anak");
        email = data.getString("email");

        hargaTotal = data.getInt("hargaTotal");
        hargaTotalAnak = data.getInt("hargaTotalAnak");
        hargaTotalDewasa = data.getInt("hargaTotalDewasa");

        Integer jmlTotal = data.getInt("jmlTotal");

        // Create a new Locale
        Locale usa = new Locale("en", "US");
        // Create a Currency instance for the Locale
        Currency dollars = Currency.getInstance(usa);
        // Create a formatter given the Locale
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        String uang = dollarFormat.format(hargaTotal);
        String uangFinal = uang.replace("$","Rp.");

        kotaAsal.setText(asal);
        kotaTujuan.setText(tujuan);
        tanggalBerakat.setText(tanggal);
        hargaTotalText.setText(uangFinal);

        cardView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),SeatActivity.class);

            intent.putExtra("asal",asal);
            intent.putExtra("tujuan",tujuan);
            intent.putExtra("tanggal",tanggal);
            intent.putExtra("dewasa",dewasa);
            intent.putExtra("anak",anak);

            intent.putExtra("email",email);
            intent.putExtra("id_book",id_book);
            intent.putExtra("hargaTotalDewasa",hargaTotalDewasa);
            intent.putExtra("hargaTotalAnak",hargaTotalAnak);
            intent.putExtra("hargaTotal",hargaTotal);

            intent.putExtra("jmlTotal",jmlTotal);

            startActivity(intent);
        });
    }
}
