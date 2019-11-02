package com.example.wochentagsrechner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Database extends AppCompatActivity implements View.OnClickListener {

    public static DatabaseHelper myDB;
    private ArrayAdapter<DBList> arrayAdapter;
    private ListView dataListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        myDB = new DatabaseHelper(this);

        arrayAdapter = myDB.getAllData(this);
        dataListView = (ListView) findViewById(R.id.dataListView);

        dataListView.setClickable(true);
        dataListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            DBList result = (DBList)parent.getItemAtPosition(position);
            showDialog(result.getDatum(), result.getEingabe(), result.getErgebnis(), result.getKommentar(), result.getId());
        });

        dataListView.setAdapter(arrayAdapter);

        Button dbBackBtn = (Button) findViewById(R.id.dbBackBtn);
        dbBackBtn.setOnClickListener(this);
    }

    private void showDialog(int datum, String eingabe, String ergebnis, String kommentar, final int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setNegativeButton("Löschen", (DialogInterface dialog, int which) -> {
                myDB.delete(id);
                dialog.cancel();
                arrayAdapter = myDB.getAllData(this);
                dataListView.setAdapter(arrayAdapter);
            });

        builder.setPositiveButton("Ok", (DialogInterface dialog, int which) -> {
                dialog.cancel();
        });
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        Date date = new Date(datum);
        builder.setTitle("" + simple.format(date));
        builder.setMessage("Der " + eingabe + " ist ein " + ergebnis + ". \nKommentar: " + kommentar);
        builder.show();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
