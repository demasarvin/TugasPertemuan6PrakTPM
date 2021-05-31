package com.example.tugascrud;

public class Penjualan {
    int id;
    String tanggal;
    double pendapatan, pengeluaran;

    public Penjualan(int id, String tanggal, double pendapatan, double pengeluaran) {
        this.id = id;
        this.tanggal = tanggal;
        this.pendapatan = pendapatan;
        this.pengeluaran = pengeluaran;
    }

    public int getId() {
        return id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public double getPendapatan() {
        return pendapatan;
    }

    public double getPengeluaran() { return pengeluaran; }
}
