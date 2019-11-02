package com.example.wochentagsrechner;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Hilfe extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hilfe);

        Button backBtn = (Button) findViewById(R.id.helpBackBtn);
        backBtn.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
