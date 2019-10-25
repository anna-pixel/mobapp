package com.example.wochentagsrechner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ergebnis extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");

        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(result);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
