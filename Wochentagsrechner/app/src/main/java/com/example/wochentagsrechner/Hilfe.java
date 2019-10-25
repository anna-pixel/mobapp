package com.example.wochentagsrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hilfe extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hilfe);

        Button backBtn = (Button) findViewById(R.id.helpBackBtn);
        backBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
