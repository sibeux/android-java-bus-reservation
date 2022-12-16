package com.hacktiv8.travelling;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.hacktiv8.travelling.database.DatabaseHelper;

public class SeatActivity extends AppCompatActivity implements View.OnClickListener {

    private String asal, tujuan, tanggal, dewasa, anak, email;
    private Integer hargaTotalDewasa, hargaTotalAnak, hargaTotal, jumlahKursiDitekan;
    TextView kotaAsal, kotaTujuan;
    ImageView a1,a2,a3,a4,a5,a6,a7;
    ImageView b1,b2,b3,b4,b5,b6,b7;
    ImageView c1,c2,c3,c4,c5,c6,c7;
    ImageView d1,d2,d3,d4,d5,d6,d7;

    DatabaseHelper dbHelper;
    Integer id_book;
    SQLiteDatabase db;
    String spinPayment;
    protected Cursor cursor;

    Spinner sPayment;
    Button bayarTiket;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_seat);

        final String[] payment = {"Gopay","Dana","LinkAja","ShopeePay","Ovo"};

        jumlahKursiDitekan = 0;
        bayarTiket = findViewById(R.id.bayarTiketButton);
        sPayment = findViewById(R.id.payment);

        ArrayAdapter<CharSequence> adapterPayment = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item, payment);
        adapterPayment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sPayment.setAdapter(adapterPayment);

        sPayment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinPayment = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        a1 = findViewById(R.id.seat_a1);
        a2 = findViewById(R.id.seat_a2);
        a3 = findViewById(R.id.seat_a3);
        a4 = findViewById(R.id.seat_a4);
        a5 = findViewById(R.id.seat_a5);
        a6 = findViewById(R.id.seat_a6);
        a7 = findViewById(R.id.seat_a7);

        b1 = findViewById(R.id.seat_b1);
        b2 = findViewById(R.id.seat_b2);
        b3 = findViewById(R.id.seat_b3);
        b4 = findViewById(R.id.seat_b4);
        b5 = findViewById(R.id.seat_b5);
        b6 = findViewById(R.id.seat_b6);
        b7 = findViewById(R.id.seat_b7);

        c1 = findViewById(R.id.seat_c1);
        c2 = findViewById(R.id.seat_c2);
        c3 = findViewById(R.id.seat_c3);
        c4 = findViewById(R.id.seat_c4);
        c5 = findViewById(R.id.seat_c5);
        c6 = findViewById(R.id.seat_c6);
        c7 = findViewById(R.id.seat_c7);

        d1 = findViewById(R.id.seat_d1);
        d2 = findViewById(R.id.seat_d2);
        d3 = findViewById(R.id.seat_d3);
        d4 = findViewById(R.id.seat_d4);
        d5 = findViewById(R.id.seat_d5);
        d6 = findViewById(R.id.seat_d6);
        d7 = findViewById(R.id.seat_d7);

        kotaAsal = findViewById(R.id.kotaAsal);
        kotaTujuan = findViewById(R.id.kotaTujuan);

        dbHelper = new DatabaseHelper(SeatActivity.this);
        db = dbHelper.getReadableDatabase();

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

        a1.setOnClickListener(this);
        a2.setOnClickListener(this);
        a3.setOnClickListener(this);
        a4.setOnClickListener(this);
        a5.setOnClickListener(this);
        a6.setOnClickListener(this);
        a7.setOnClickListener(this);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);

        c1.setOnClickListener(this);
        c2.setOnClickListener(this);
        c3.setOnClickListener(this);
        c4.setOnClickListener(this);
        c5.setOnClickListener(this);
        c6.setOnClickListener(this);
        c7.setOnClickListener(this);

        d1.setOnClickListener(this);
        d2.setOnClickListener(this);
        d3.setOnClickListener(this);
        d4.setOnClickListener(this);
        d5.setOnClickListener(this);
        d6.setOnClickListener(this);
        d7.setOnClickListener(this);

        kotaAsal.setText(asal);
        kotaTujuan.setText(tujuan);

        bayarTiket.setOnClickListener(v -> {
            if (jumlahKursiDitekan == jmlTotal){
                AlertDialog dialog = new AlertDialog.Builder(SeatActivity.this)
                        .setTitle("Yakin melakukan pemesanan?")
                        .setPositiveButton("Yes",(dialog1,which) -> {
                            try{
                                db.execSQL("INSERT INTO TB_BOOK (asal, tujuan, tanggal, dewasa, anak) VALUES ('" +
                                        asal + "','" +
                                        tujuan + "','" +
                                        tanggal + "','" +
                                        dewasa + "','" +
                                        anak + "');");
                                cursor = db.rawQuery("SELECT id_book FROM TB_BOOK ORDER BY id_book DESC", null);
                                cursor.moveToLast();
                                if (cursor.getCount() > 0) {
                                    cursor.moveToPosition(0);
                                    id_book = cursor.getInt(0);
                                }
                                db.execSQL("INSERT INTO TB_HARGA (username, id_book, harga_dewasa, harga_anak, harga_total) VALUES ('" +
                                        email + "','" +
                                        id_book + "','" +
                                        hargaTotalDewasa + "','" +
                                        hargaTotalAnak + "','" +
                                        hargaTotal + "');");
//                                    finish(); jika ini aktif, langsung kembali ke halaman sebelumnya
                                Toast.makeText(this, "cetak tiket", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(this, MainActivity.class);
                                startActivity(i);
                            } catch (Exception e) {
                                Toast.makeText(SeatActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Tidak",null)
                        .create();
                dialog.show();
            } else if (jumlahKursiDitekan > jmlTotal){
                Toast.makeText(SeatActivity.this, "Max kursi : "+jmlTotal, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Pilih kursi : -"+(jmlTotal-jumlahKursiDitekan), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (true){
            if (v.getId() == R.id.seat_a1){
                if (!a1.isClickable()){
                    if (!a1.isFocusable()){
                        a1.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a1.setFocusable(true);
                    } else{
                        a1.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a1.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a2){
                if (a2.isClickable()){
                    if (!a2.isFocusable()){
                        a2.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a2.setFocusable(true);
                    } else{
                        a2.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a2.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a3){
                if (a3.isClickable()){
                    if (!a3.isFocusable()){
                        a3.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a3.setFocusable(true);
                    } else{
                        a3.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a3.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a4){
                if (!a4.isClickable()){
                    if (!a4.isFocusable()){
                        a4.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a4.setFocusable(true);
                    } else{
                        a4.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a4.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a5){
                if (a5.isClickable()){
                    if (!a5.isFocusable()){
                        a5.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a5.setFocusable(true);
                    } else{
                        a5.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a5.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a6){
                if (!a6.isClickable()){
                    if (!a6.isFocusable()){
                        a6.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a6.setFocusable(true);
                    } else{
                        a6.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a6.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_a7){
                if (a7.isClickable()){
                    if (!a7.isFocusable()){
                        a7.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        a7.setFocusable(true);
                    } else{
                        a7.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        a7.setFocusable(false);
                    }
                }
            }

            if (v.getId() == R.id.seat_b1){
                if (b1.isClickable()){
                    if (!b1.isFocusable()){
                        b1.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b1.setFocusable(true);
                    } else{
                        b1.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b1.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b2){
                if (!b2.isClickable()){
                    if (!b2.isFocusable()){
                        b2.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b2.setFocusable(true);
                    } else{
                        b2.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b2.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b3){
                if (b3.isClickable()){
                    if (!b3.isFocusable()){
                        b3.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b3.setFocusable(true);
                    } else{
                        b3.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b3.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b4){
                if (!b4.isClickable()){
                    if (!b4.isFocusable()){
                        b4.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b4.setFocusable(true);
                    } else{
                        b4.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b4.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b5){
                if (b5.isClickable()){
                    if (!b5.isFocusable()){
                        b5.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b5.setFocusable(true);
                    } else{
                        b5.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b5.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b6){
                if (b6.isClickable()){
                    if (!b6.isFocusable()){
                        b6.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b6.setFocusable(true);
                    } else{
                        b6.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b6.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_b7){
                if (b7.isClickable()){
                    if (!b7.isFocusable()){
                        b7.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        b7.setFocusable(true);
                    } else{
                        b7.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        b7.setFocusable(false);
                    }
                }
            }

            if (v.getId() == R.id.seat_c1){
                if (!c1.isClickable()){
                    if (!c1.isFocusable()){
                        c1.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c1.setFocusable(true);
                    } else{
                        c1.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c1.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c2){
                if (!c2.isClickable()){
                    if (!c2.isFocusable()){
                        c2.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c2.setFocusable(true);
                    } else{
                        c2.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c2.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c3){
                if (c3.isClickable()){
                    if (!c3.isFocusable()){
                        c3.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c3.setFocusable(true);
                    } else{
                        c3.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c3.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c4){
                if (c4.isClickable()){
                    if (!c4.isFocusable()){
                        c4.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c4.setFocusable(true);
                    } else{
                        c4.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c4.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c5){
                if (!c5.isClickable()){
                    if (!c5.isFocusable()){
                        c5.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c5.setFocusable(true);
                    } else{
                        c5.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c5.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c6){
                if (c6.isClickable()){
                    if (!c6.isFocusable()){
                        c6.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c6.setFocusable(true);
                    } else{
                        c6.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c6.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_c7){
                if (!c7.isClickable()){
                    if (!c7.isFocusable()){
                        c7.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        c7.setFocusable(true);
                    } else{
                        c7.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        c7.setFocusable(false);
                    }
                }
            }

            if (v.getId() == R.id.seat_d1){
                if (d1.isClickable()){
                    if (!d1.isFocusable()){
                        d1.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d1.setFocusable(true);
                    } else{
                        d1.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d1.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d2){
                if (d2.isClickable()){
                    if (!d2.isFocusable()){
                        d2.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d2.setFocusable(true);
                    } else{
                        d2.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d2.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d3){
                if (!d3.isClickable()){
                    if (!d3.isFocusable()){
                        d3.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d3.setFocusable(true);
                    } else{
                        d3.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d3.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d4){
                if (d4.isClickable()){
                    if (!d4.isFocusable()){
                        d4.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d4.setFocusable(true);
                    } else{
                        d4.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d4.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d5){
                if (d5.isClickable()){
                    if (!d5.isFocusable()){
                        d5.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d5.setFocusable(true);
                    } else{
                        d5.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d5.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d6){
                if (!d6.isClickable()){
                    if (!d6.isFocusable()){
                        d6.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d6.setFocusable(true);
                    } else{
                        d6.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d6.setFocusable(false);
                    }
                }
            }
            if (v.getId() == R.id.seat_d7){
                if (!d7.isClickable()){
                    if (!d7.isFocusable()){
                        d7.setImageResource(R.drawable.your_seat_img);
                        jumlahKursiDitekan ++;
                        d7.setFocusable(true);
                    } else{
                        d7.setImageResource(R.drawable.available_img);
                        jumlahKursiDitekan --;
                        d7.setFocusable(false);
                    }
                }
            }
        } else{

        }
    }
}
