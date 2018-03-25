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
        tNama = (EditText) findViewById(R.id.tNama);
        tDeskripsi = (EditText) findViewById(R.id.tDeskripsi);
        tPrioritas = (EditText) findViewById(R.id.tPrioritas);
        mDbHelper = new DatabaseHelper(this);
    }
    public void tambah(View view) {
        String nama = tNama.getText().toString();
        String deskripsi = tDeskripsi.getText().toString();
        String prioritas = tPrioritas.getText().toString();
        AddData(nama, deskripsi,prioritas);

    }
    public void AddData(String nama,String deskripsi,String prioritas ){
        boolean inserData = mDbHelper.addData(nama,deskripsi,prioritas);

        if (inserData){
            Toast.makeText(this,"Berhasil", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }else {
            Toast.makeText(this,"Gagal", Toast.LENGTH_LONG).show();

        }

    }


    public void tampilDAta(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
