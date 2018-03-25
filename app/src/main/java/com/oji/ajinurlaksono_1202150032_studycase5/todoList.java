package com.oji.ajinurlaksono_1202150032_studycase5;

/**
 * Created by OJI on 23/03/2018.
 */

public class todoList {
    private int id;
    private String nama, deskripsi, prioritas;
    public todoList( int id, String nama, String deskripsi, String prioritas ) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.prioritas= prioritas;
    }
    public int getId(){return id;}

    public String getDekripsi() {
        return deskripsi;
    }

    public String getNama() {
        return nama;
    }

    public String getPrioritas() {
        return prioritas;
    }


}
