package com.example.sitimufida.crudjson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TambahAnggotaActivity extends Activity {
    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    EditText inputNama;
    EditText inputAlamat;

    // inisialisasi url tambahanggota.php
    private static String url_tambah_anggota = "http://192.168.56.1:81/crud_adro_biasa/member/insert_member.php";

    //  inisialisasi nama node dari json yang dihasilkan oleh php (utk class ini hanya node "sukses")
    private static final String TAG_SUKSES = "sukses";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_anggota);

        // inisialisasi Edit Text
        inputNama = (EditText) findViewById(R.id.inputNama);
        inputAlamat = (EditText) findViewById(R.id.inputAlamat);

        // inisialisasi  button
        Button btnTambahAnggota = (Button) findViewById(R.id.btnTambahAnggota);

        // klik even tombol tambah anggota
        btnTambahAnggota.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // buat method pada background thread
                new BuatAnggotaBaru().execute();
            }
        });
    }

    /**
     * Background Async Task untuk menambah data anggota baru
     */
    class BuatAnggotaBaru extends AsyncTask<String, String, String> {

        /**
         * sebelum memulai background thread tampilkan Progress Dialog
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(TambahAnggotaActivity.this);
            pDialog.setMessage("Menambah data..silahkan tunggu");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        /**
         * menambah data
         */
        protected String doInBackground(String... args) {
            String nama = inputNama.getText().toString();
            String alamat = inputAlamat.getText().toString();

            // membangun Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("nama", nama));
            params.add(new BasicNameValuePair("alamat", alamat));

            // mengambil JSON Object
            //dengan method POST
            JSONObject json = jsonParser.makeHttpRequest(url_tambah_anggota, "POST", params);

            // periksa log cat respon
            Log.d("Respon tambah anggota", json.toString());

            // check for success tag
            try {
                int sukses = json.getInt(TAG_SUKSES);

                if (sukses == 1) {
                    // jika sukses menambah data baru
                    Intent i = new Intent(getApplicationContext(), SemuaAnggotaActivity.class);
                    startActivity(i);

                    // tutup activity ini
                    finish();
                } else {
                    // jika gagal dalam menambah data
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        protected void onPostExecute(String file_url) {
            // hilangkan dialog ketika selesai menambah data baru
            pDialog.dismiss();
        }

    }
}

