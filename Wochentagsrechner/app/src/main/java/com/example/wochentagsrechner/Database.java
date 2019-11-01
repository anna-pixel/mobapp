package com.example.wochentagsrechner;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Database extends AppCompatActivity implements View.OnClickListener {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        myDB = new DatabaseHelper(this);

        Cursor res = myDB.getAllData();

        /*Intent intent = getIntent();
        Cursor res = (Cursor) intent.getSerializableExtra("db");*/
        if (res.getCount()== 0){
            showMessage("Error", "No data found");
            //Toast.makeText(this, "Keine Daten verfügbar", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while(res.moveToNext()){
            buffer.append("Datum: " + res.getString(1) + "\n" );
            buffer.append("Eingabe: " + res.getString(2) + "\n" );
            buffer.append("Ergebnis: " + res.getString(3) + "\n" );
            buffer.append("Kommentar: " + res.getString(4) + "\n\n" );
        }

        showMessage("Data", buffer.toString());
        RecyclerView list = (RecyclerView) findViewById(R.id.dbListView);
        list.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });

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
