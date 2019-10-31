package com.example.wochentagsrechner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Startseite extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startseite);

        Button calcBtn = (Button) findViewById(R.id.calcBtn); //find in Resources -> id -> calcBtn
        calcBtn.setOnClickListener(this);

        Button help = (Button) findViewById(R.id.helpBtn);
        help.setOnClickListener(this);

        Button db = (Button) findViewById(R.id.DBBtn);
        db.setOnClickListener(this);

        Button clearBtn = (Button) findViewById(R.id.clearBtn);
        clearBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText dayNumEditText = (EditText) findViewById(R.id.dayNumEditText);
        EditText monthNumEditText = (EditText) findViewById(R.id.monthNumEditText);
        EditText yearNumEditText = (EditText) findViewById(R.id.yearNumEditText);

        switch(v.getId()){
            case R.id.calcBtn:
                int day, month, year;
                String finalResult = "";

                String buff_day, buff_month, buff_year;
                buff_day = dayNumEditText.getText().toString();
                buff_month = monthNumEditText.getText().toString();
                buff_year = yearNumEditText.getText().toString();

                if(!(buff_day.equals("")) || !(buff_month.equals("")) || !(buff_year.equals(""))) { //keinen Input abfangen

                    day = Integer.parseInt(dayNumEditText.getText().toString());
                    month = Integer.parseInt(monthNumEditText.getText().toString());
                    year = Integer.parseInt(yearNumEditText.getText().toString());

                    if(checkDate(day, month, year)){
                        finalResult = calculateDate(day, month, year);
                    }

                    if (!finalResult.equals("")) {

                        Intent intent1 = new Intent(this, Ergebnis.class);
                        intent1.putExtra("result", finalResult);
                        startActivity(intent1);

                    } else {
                        Toast.makeText(this, R.string.no_input, Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, R.string.no_input, Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.clearBtn:
                dayNumEditText.getText().clear();
                monthNumEditText.getText().clear();
                yearNumEditText.getText().clear();
                break;

            case R.id.helpBtn:
                Intent intent2 = new Intent(this, Hilfe.class);
                startActivity(intent2);
                break;

            case R.id.DBBtn:
                Intent intent3 = new Intent(this, Database.class);
                startActivity(intent3);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private boolean checkDate(int day, int month, int year) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String s = "" + day + "." + month + "." + year;
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(s);
            return true;
        } catch (ParseException pe) {
            Toast.makeText(this, R.string.no_valid_date, Toast.LENGTH_SHORT).show();
        }
        return false;

    }

    public String calculateDate(int day, int month, int year){
        String result = "";
        String finalResult = "";

        if ((day != 0) || (month != 0) || (year != 0)) { //Eingabe von 0 abfangen

            int h = month, k = year;
            int w_day;

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

            finalResult = "Der " + day + "." + month + "." + year + " ist ein " + result + ".";
        }

        return finalResult;
    }
}
