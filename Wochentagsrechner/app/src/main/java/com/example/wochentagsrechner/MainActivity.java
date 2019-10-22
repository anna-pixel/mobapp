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

                int day, month, year;

                EditText dayNumEditText = (EditText) findViewById(R.id.dayNumEditText);
                EditText monthNumEditText = (EditText) findViewById(R.id.monthNumEditText);
                EditText yearNumEditText = (EditText) findViewById(R.id.yearNumEditText);

                TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

                String buff_day, buff_month, buff_year;
                buff_day = dayNumEditText.getText().toString();
                buff_month = monthNumEditText.getText().toString();
                buff_year = yearNumEditText.getText().toString();

                if(!(buff_day.equals("")) || !(buff_month.equals("")) || !(buff_year.equals(""))){ //keinen Input abfangen

                    day = Integer.parseInt(dayNumEditText.getText().toString());
                    month = Integer.parseInt(monthNumEditText.getText().toString());
                    year = Integer.parseInt(yearNumEditText.getText().toString());

                    if((day != 0) || (month != 0) || (year != 0)) { //Eingabe von 0 abfangen


                        int h = month, k = year;
                        int w_day;

                        String result = "";

                        if (month < 3) {
                            h = month + 12;
                            k = year - 1;
                        }

                        w_day = (day + 2 * h + (3 * h + 3) / 5 + k + k / 4 - k / 100 + k / 400 + 1) % 7;

                        if (w_day == 0)
                            result = "Sonntag";
                        else if (w_day == 1)
                            result = "Montag";
                        else if (w_day == 2)
                            result = "Dienstag";
                        else if (w_day == 3)
                            result = "Mittwoch";
                        else if (w_day == 4)
                            result = "Donnerstag";
                        else if (w_day == 5)
                            result = "Freitag";
                        else if (w_day == 6)
                            result = "Samstag";

                        resultTextView.setText(result);
                        result = "";
                    }
                }


            }
        });
    }
}
