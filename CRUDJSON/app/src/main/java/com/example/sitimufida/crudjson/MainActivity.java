package com.example.sitimufida.crudjson;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnLihatAnggota;
    Button btnTambahAnggota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // inisialisasi button/tombol
        btnLihatAnggota = (Button) findViewById(R.id.btnLihatAnggota);
        btnTambahAnggota = (Button) findViewById(R.id.btnTambahAnggota);

        // even klik untuk menampilkan class SemuaAnggotaActivity
        btnLihatAnggota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Tampilkan semua anggota activity lewat intent
                Intent i = new Intent(getApplicationContext(), SemuaAnggotaActivity.class);
                startActivity(i);

            }
        });

        // even klik menampilkan TambahAnggotaACtivity
        btnTambahAnggota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Tampilkan tambah anggota activity lewat intent
                Intent i = new Intent(getApplicationContext(), TambahAnggotaActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
