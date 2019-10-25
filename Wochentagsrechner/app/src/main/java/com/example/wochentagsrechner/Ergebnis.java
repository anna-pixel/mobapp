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
        setContentView(R.layout.ergebnis);

        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(this);

        Button helpBtn = (Button) findViewById(R.id.ergebnisHelpBtn);
        helpBtn.setOnClickListener(this);

        Intent intent = getIntent();
        String result = intent.getStringExtra("result");

        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText(result);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
            case R.id.ergebnisHelpBtn:
                Intent intent2 = new Intent(this, Hilfe.class);
                startActivity(intent2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }


    }
}
