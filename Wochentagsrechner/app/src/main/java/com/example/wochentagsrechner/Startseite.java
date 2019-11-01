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

        EditText commentEditText = (EditText) findViewById(R.id.commentEditText);
        String comment = commentEditText.getText().toString();

        switch(v.getId()){
            case R.id.calcBtn:
                int day, month, year;
                String result = "";

                //Prüfen, ob String nicht leer ist -> keinen Input abfangen
                if(!(dayNumEditText.getText().toString().equals(""))
                        | !(monthNumEditText.getText().toString().equals(""))
                        | !(yearNumEditText.getText().toString().equals(""))) {

                    day = Integer.parseInt(dayNumEditText.getText().toString());
                    month = Integer.parseInt(monthNumEditText.getText().toString());
                    year = Integer.parseInt(yearNumEditText.getText().toString());

                    //Input auf valides Datum prüfen
                    if(checkDate(day, month, year)){
                        //Wochentag berechnen
                        result = calculateDate(day, month, year);
                    }

                    if (!result.equals("")) {

                        Intent intent1 = new Intent(this, Ergebnis.class);
                        intent1.putExtra("result", result);
                        intent1.putExtra("input_day", day);
                        intent1.putExtra("input_month", month);
                        intent1.putExtra("input_year", year);

                        String input = "" + day + "." + month + "." + year;
                        boolean isSaved = myDB.insertData(input, result, comment);
                        if(isSaved) {
                            Toast.makeText(this, R.string.save_success, Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(this, R.string.save_fail, Toast.LENGTH_LONG).show();
                        }
                        startActivity(intent1);

                    }
                }else {
                    Toast.makeText(this, R.string.no_input, Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.clearBtn:
                //löscht Eingaben
                dayNumEditText.getText().clear();
                monthNumEditText.getText().clear();
                yearNumEditText.getText().clear();
                commentEditText.getText().clear();
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
            Toast.makeText(this, R.string.no_valid_date, Toast.LENGTH_LONG).show();
        }
        return false;
    }

    private String calculateDate(int day, int month, int year){
        /* Wochentagsberechnung anhand folgender Formel: http://www.straub.as/java/basic/Lwochentag.html
           gibt berechneten Wochentag als fertigen String zurück
        */
        String[] result_day = {"Sonntag", "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag"};
        String result = "";

        if ((day != 0) || (month != 0) || (year != 0)) { //Eingabe von 0 abfangen

            int h = month, k = year;
            int w_day;

            if (month < 3) {
                h = month + 12;
                k = year - 1;
            }

            w_day = (day + 2 * h + (3 * h + 3) / 5 + k + k / 4 - k / 100 + k / 400 + 1) % 7;

            result = result_day[w_day];
        }

        return result;
    }
}
