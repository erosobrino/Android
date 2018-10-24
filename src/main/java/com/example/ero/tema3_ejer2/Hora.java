package com.example.ero.tema3_ejer2;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Hora extends AppCompatActivity {
    boolean seleccionada = false;
    String stringHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hora);

        Button btCancelar = findViewById(R.id.btCancelar2);
        Button btAceptar = findViewById(R.id.btEnviar2);
        Calendar calendar = Calendar.getInstance();
        int hour= calendar.get(Calendar.HOUR_OF_DAY);
        int minute= calendar.get(Calendar.MINUTE);
        TimePicker timePicker=findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        }else{
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                stringHora=hourOfDay+":"+minute;
                seleccionada=true;
            }
        });

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seleccionada){
                    Intent itHora=new Intent();
                    itHora.putExtra("Hora",stringHora);
                    setResult(RESULT_OK,itHora);
                    finish();
                }else{
                    Toast sinHoraToast=Toast.makeText(getApplicationContext(),"Hora sin seleccionar",Toast.LENGTH_SHORT);
                    sinHoraToast.show();
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
