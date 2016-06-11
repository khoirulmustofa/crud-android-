package com.example.sitimufida.crudjson;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class SemuaAnggotaActivity extends  ListActivity{// Progress Dialog
    private ProgressDialog pDialog;

    // Membuat objek JSONParser
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> memberList;

    // inisialisasi url semuanggota.php
    private static String url_semua_anggota = "http://192.168.56.1:81/crud_adro_biasa/member/getall_member.php";

    // inisialisasi nama node dari json yang dihasilkan oleh php
    private static final String TAG_SUKSES = "sukses";
    private static final String TAG_MEMBER = "member";
    private static final String TAG_IDMEM = "id";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_ALAMAT = "alamat";


    // buat JSONArray member
    JSONArray member = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.semua_anggota);

        // Hashmap untuk ListView
        memberList = new ArrayList<HashMap<String, String>>();

        // buat method untuk menampilkan data pada Background Thread
        new AmbilDataJson().execute();

        // ambil listview
        ListView lv = getListView();

        // pada saat mengklik salah satu nama member
        // lalu alihkan pada class EditanggotaActivity
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ambil nilai dari ListItem yang dipilih
                String idmem = ((TextView) view.findViewById(R.id.idmem)).getText().toString();

                // Starting new intent
                Intent in = new Intent(getApplicationContext(), EditAnggotaActivity.class);
                // kirim idmem ke activity berikutnya
                in.putExtra(TAG_IDMEM, idmem);

                // mulai activity baru dan dapatkan respon dengan result kode 100
                startActivityForResult(in, 100);
            }
        });

    }

    // Respon dari Edit anggota Activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // jika result code 100
        if (resultCode == 100) {
            // jika result code 100 diterima
            // artinya user mengedit/menghapus member
            // reload layar ini lagi
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    /**
     * Background Async Task untuk menampilkan semua data anggota dengan HTTP Request
     * */
    class AmbilDataJson extends AsyncTask<String, String, String> {

        /**
         * sebelum memulai background thread tampilkan Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SemuaAnggotaActivity.this);
            pDialog.setMessage("Mengambil Data Anggota. Silahkan Tunggu...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * mengambil semua data anggota/member dari url
         * */
        protected String doInBackground(String... args) {
            // membangun Parameter
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // ambil JSON string dari URL
            JSONObject json = jParser.makeHttpRequest(url_semua_anggota, "GET", params);

            // cek log cat untuk JSON reponse
            Log.d("Semua Anggota: ", json.toString());


            try {
                // mengecek untuk TAG SUKSES
                int sukses = json.getInt(TAG_SUKSES);

                if (sukses == 1) {
                    // data ditemukan
                    // mengambil  Array dari member
                    member = json.getJSONArray(TAG_MEMBER);

                    // looping data semua member/anggota
                    for (int i = 0; i < member.length(); i++) {
                        JSONObject c = member.getJSONObject(i);

                        // tempatkan setiap item json di variabel
                        String id = c.getString(TAG_IDMEM);
                        String nama = c.getString(TAG_NAMA);
                        String alamat = c.getString(TAG_ALAMAT);

                        // buat new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // menambah setiap child node ke HashMap key => value
                        map.put(TAG_IDMEM, id);
                        map.put(TAG_NAMA, nama);
                        map.put(TAG_ALAMAT, alamat);

                        // menambah HashList ke ArrayList
                        memberList.add(map);
                    }
                } else {
                    // tidak ditemukan data anggota/member
                    // Tampilkan layar tambahAnggotaActivity
                    Intent i = new Intent(getApplicationContext(),TambahAnggotaActivity.class);
                    // tutup semua activity sebelumnya
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * setelah menyelesaikan background task hilangkan the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // hilangkan dialog setelah mendapatkan semua data member
            pDialog.dismiss();
            // update UI dari Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * update hasil parsing JSON ke ListView
                     * */
                    ListAdapter adapter = new SimpleAdapter(
                            SemuaAnggotaActivity.this, memberList,
                            R.layout.list_item, new String[] { TAG_IDMEM,
                            TAG_NAMA,TAG_ALAMAT},
                            new int[] { R.id.idmem, R.id.nama,R.id.alamat });
                    // update listview
                    setListAdapter(adapter);
                }
            });

        }

    }
}
