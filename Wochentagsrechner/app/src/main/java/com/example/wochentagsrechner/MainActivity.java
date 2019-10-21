package com.example.wochentagsrechner;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calcBtn = (Button) findViewById(R.id.calcBtn); //find in Resources -> id -> calcBtn
        calcBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText dayNumEditText = (EditText) findViewById(R.id.dayNumEditText);
                EditText monthNumEditText = (EditText) findViewById(R.id.monthNumEditText);
                EditText yearNumEditText = (EditText) findViewById(R.id.yearNumEditText);

                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                int day = Integer.parseInt(dayNumEditText.getText().toString());
                int month = Integer.parseInt(monthNumEditText.getText().toString());
                int year = Integer.parseInt(yearNumEditText.getText().toString());

                int h = month, k = year ;
                int w_day;

                String result = "";

                if (month < 3)
                {
                    h = month + 12 ;
                    k = year-1 ;
                }

                w_day = (day+2*h + (3*h+3)/5 + k + k/4 - k/100 + k/400 + 1)%7 ;

                if (w_day==0)
                    result = "Sonntag";
                else if (w_day==1)
                    result = "Montag";
                else if (w_day==2)
                    result = "Dienstag";
                else if (w_day==3)
                    result = "Mittwoch";
                else if (w_day==4)
                    result = "Donnerstag";
                else if (w_day==5)
                    result = "Freitag";
                else if (w_day==6)
                    result = "Samstag";

                resultTextView.setText(result);
                result = "";

            }
        });
    }
}
