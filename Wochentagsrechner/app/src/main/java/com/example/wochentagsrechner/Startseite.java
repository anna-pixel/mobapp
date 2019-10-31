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

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startseite);

        myDB = new DatabaseHelper(this);

        // Button über die ID finden und jedem Button einen OnClickListener hinzufügen
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
        //Textfelder über ID in Ressourcen finden
        EditText dayNumEditText = (EditText) findViewById(R.id.dayNumEditText);
        EditText monthNumEditText = (EditText) findViewById(R.id.monthNumEditText);
        EditText yearNumEditText = (EditText) findViewById(R.id.yearNumEditText);

        switch(v.getId()){
            case R.id.calcBtn:
                int day, month, year;
                String finalResult = "";

                //Zwischenspeichern des Inputs als String
                String buff_day, buff_month, buff_year;
                buff_day = dayNumEditText.getText().toString();
                buff_month = monthNumEditText.getText().toString();
                buff_year = yearNumEditText.getText().toString();

                //Prüfen, ob String nicht leer ist -> keinen Input abfangen
                if(!(buff_day.equals("")) || !(buff_month.equals("")) || !(buff_year.equals(""))) {

                    day = Integer.parseInt(dayNumEditText.getText().toString());
                    month = Integer.parseInt(monthNumEditText.getText().toString());
                    year = Integer.parseInt(yearNumEditText.getText().toString());

                    //Input auf valides Datum prüfen
                    if(checkDate(day, month, year)){
                        //Wochentag berechnen
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
                //löscht Eingaben
                dayNumEditText.getText().clear();
                monthNumEditText.getText().clear();
                yearNumEditText.getText().clear();
                break;

            case R.id.helpBtn:
                //Aufruf der Hilfe-Seite
                Intent intent2 = new Intent(this, Hilfe.class);
                startActivity(intent2);
                break;

            case R.id.DBBtn:
                //Aufruf der Datenbank-Seite
                Intent intent3 = new Intent(this, Database.class);
                startActivity(intent3);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    private boolean checkDate(int day, int month, int year) {
        //fügt Integer-Werte zu einem String zusammen und prüft, ob dieser zu einem SimpleDate umgewandelt werden kann

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

    private String calculateDate(int day, int month, int year){
        /* Wochentagsberechnung anhand folgender Formel: http://www.straub.as/java/basic/Lwochentag.html
           gibt berechneten Wochentag als fertigen String zurück
        */

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

            switch (w_day){
                case 0:
                    result = "Sonntag";
                    break;
                case 1:
                    result = "Montag";
                    break;
                case 2:
                    result = "Dienstag";
                    break;
                case 3:
                    result = "Mittwoch";
                    break;
                case 4:
                    result = "Donnerstag";
                    break;
                case 5:
                    result = "Freitag";
                    break;
                case 6:
                    result = "Samstag";
                    break;
                default:
                    result = "";
                    break;
            }

            finalResult = "Der " + day + "." + month + "." + year + " ist ein " + result + ".";
        }

        return finalResult;
    }
}
