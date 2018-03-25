package com.oji.ajinurlaksono_1202150032_studycase5;

/**
 * Created by OJI on 23/03/2018.
 */

public class todoList {
    private int id; //inisiasi variable
    private String nama, deskripsi, prioritas; //inisiasi variable
    public todoList( int id, String nama, String deskripsi, String prioritas ) {
        this.id = id; //referencing ke attribute
        this.nama = nama; //referencing ke attribute
        this.deskripsi = deskripsi;//referencing ke attribute
        this.prioritas= prioritas;//referencing ke attribute
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
