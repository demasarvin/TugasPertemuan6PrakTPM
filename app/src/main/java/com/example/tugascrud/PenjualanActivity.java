package com.example.tugascrud;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class PenjualanActivity extends AppCompatActivity {

    List<Penjualan> penjualanList;
    SQLiteDatabase mDatabase;
    ListView listViewPenjualans;
    PenjualanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penjualan);

        listViewPenjualans = (ListView) findViewById(R.id.listViewPenjualan);
        penjualanList = new ArrayList<>();

        mDatabase = openOrCreateDatabase(MainActivity.DATABASE_NAME, MODE_PRIVATE, null);

        showPenjualansFromDatabase();
    }

    private void showPenjualansFromDatabase() {

        Cursor cursorPenjualans = mDatabase.rawQuery("SELECT * FROM penjualans", null);

        if (cursorPenjualans.moveToFirst()) {
            do {
                penjualanList.add(new Penjualan(
                        cursorPenjualans.getInt(0),
                        cursorPenjualans.getString(1),
                        cursorPenjualans.getDouble(2),
                        cursorPenjualans.getDouble(3)
                ));
            } while (cursorPenjualans.moveToNext());
        }
        cursorPenjualans.close();

        adapter = new PenjualanAdapter(this, R.layout.list_penjualan, penjualanList, mDatabase);

        listViewPenjualans.setAdapter(adapter);
    }

}
