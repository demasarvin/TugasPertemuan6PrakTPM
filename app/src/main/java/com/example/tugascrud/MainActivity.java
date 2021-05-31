package com.example.tugascrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String DATABASE_NAME = "penjualann";

    TextView textViewViewPenjualans;
    EditText editTextTanggal, editTextPendapatan, editTextPengeluaran;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewViewPenjualans = (TextView) findViewById(R.id.textViewViewPenjualans);
        editTextTanggal = (EditText) findViewById(R.id.editTextTanggal);
        editTextPendapatan = (EditText) findViewById(R.id.editTextPendapatan);
        editTextPengeluaran = (EditText) findViewById(R.id.editTextPengeluaran);

        findViewById(R.id.buttonAddPenjualan).setOnClickListener(this);
        textViewViewPenjualans.setOnClickListener(this);

        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createPenjualanTable();

    }

    private boolean inputsAreCorrect(String tanggal, String pendapatan, String pengeluaran) {
        if (tanggal.isEmpty()) {
            editTextTanggal.setError("Please enter a Tanggal");
            editTextTanggal.requestFocus();
            return false;
        }

        if (pendapatan.isEmpty() || Integer.parseInt(pendapatan) <= 0) {
            editTextPendapatan.setError("Please enter Pendapatan");
            editTextPendapatan.requestFocus();
            return false;
        }

        if (pengeluaran.isEmpty() || Integer.parseInt(pengeluaran) <= 0) {
            editTextPengeluaran.setError("Please enter pengeluaran");
            editTextPengeluaran.requestFocus();
            return false;
        }

        return true;
    }

    private void addPenjualan() {
        String tanggal = editTextTanggal.getText().toString().trim();
        String pendapatan = editTextPendapatan.getText().toString().trim();
        String pengeluaran = editTextPengeluaran.getText().toString().trim();



        if (inputsAreCorrect(tanggal, pendapatan, pengeluaran)) {

            String insertSQL = "INSERT INTO Penjualans \n" +
                    "(tanggal, pendapatan, pengeluaran)\n" +
                    "VALUES \n" +
                    "(?, ?, ?);";

            mDatabase.execSQL(insertSQL, new String[]{tanggal, pendapatan, pengeluaran});

            Toast.makeText(this, "Data Penjualan Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonAddPenjualan:

                addPenjualan();

                break;
            case R.id.textViewViewPenjualans:

                startActivity(new Intent(this, PenjualanActivity.class));

                break;
        }
    }

    private void createPenjualanTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS Penjualans (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT Penjualans_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    tanggal varchar(200) NOT NULL,\n" +
                        "    pendapatan double NOT NULL,\n" +
                        "    pengeluaran double NOT NULL\n" +
                        ");"
        );
    }

}