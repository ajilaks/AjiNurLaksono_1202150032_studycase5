package com.oji.ajinurlaksono_1202150032_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tambahTodo extends AppCompatActivity {

    DatabaseHelper mDbHelper;
    private Button bSubmit;
    private EditText tNama,tDeskripsi, tPrioritas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_todo);
        tNama = (EditText) findViewById(R.id.tNama);//referencing variable
        tDeskripsi = (EditText) findViewById(R.id.tDeskripsi); //referencing variable
        tPrioritas = (EditText) findViewById(R.id.tPrioritas); //referencing variable
        mDbHelper = new DatabaseHelper(this);
    }
    public void tambah(View view) {
        String nama = tNama.getText().toString(); //get pada textview
        String deskripsi = tDeskripsi.getText().toString(); //get pada textview
        String prioritas = tPrioritas.getText().toString(); //get pada textview
        AddData(nama, deskripsi,prioritas); //panggil tambah data

    }
    public void AddData(String nama,String deskripsi,String prioritas ){
        boolean inserData = mDbHelper.addData(nama,deskripsi,prioritas);
//memasukkan data dari parameter
        if (inserData){
            Toast.makeText(this,"Berhasil", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class); //intent menuju activity selanjutnya
            startActivity(i);
        }else {
            Toast.makeText(this,"Gagal", Toast.LENGTH_LONG).show();

        }

    }

    //method untuk menampilkan data
    public void tampilDAta(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
