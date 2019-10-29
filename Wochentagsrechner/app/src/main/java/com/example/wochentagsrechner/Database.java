package com.example.wochentagsrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Database extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        Button dbBackBtn = (Button) findViewById(R.id.dbBackBtn);
        dbBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
