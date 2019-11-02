package com.example.wochentagsrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Database extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        myDB = new DatabaseHelper(this);

        ArrayAdapter<DBList> arrayAdapter = myDB.getAllData(this);
        ListView dataListView = (ListView) findViewById(R.id.dataListView);

        dataListView.setAdapter(arrayAdapter);


        /*StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Datum: " + res.getString(1) + "\n" );
            buffer.append("Eingabe: " + res.getString(2) + "\n" );
            buffer.append("Ergebnis: " + res.getString(3) + "\n" );
            buffer.append("Kommentar: " + res.getString(4) + "\n\n" );
        }

        showMessage("Data", buffer.toString());*/

        Button dbBackBtn = (Button) findViewById(R.id.dbBackBtn);
        dbBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
