package com.example.ero.tema3_ejer2;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha extends AppCompatActivity {
    boolean seleccionada = false;
    String stringFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fecha);

        Button btCancelar = findViewById(R.id.btCancelar);
        Button btAceptar = findViewById(R.id.btEnviar);
        CalendarView calendario = findViewById(R.id.calendarView);
        final Calendar fecha = Calendar.getInstance();
        calendario.setDate(fecha.getTimeInMillis());
        calendario.setFirstDayOfWeek(0);

        calendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                fecha.set(year, month, dayOfMonth);
                SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-YYYY");
                stringFecha = formatofecha.format(fecha.getTime());
                Log.i("fecha",stringFecha+" "+year+" "+month+" "+dayOfMonth);
                seleccionada = true;
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionada) {
                    Intent itFecha = new Intent();
                    itFecha.putExtra("Fecha",stringFecha);
                    setResult(RESULT_OK,itFecha);
                    finish();
                } else {
                    Toast sinEleccionToast = Toast.makeText(getApplicationContext(), "Debes introducir una fecha", Toast.LENGTH_SHORT);
                    sinEleccionToast.show();
                }
            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
