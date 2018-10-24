package com.example.ero.tema3_ejer2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int cont = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btCont = findViewById(R.id.btCont);
        final TextView tvCont = findViewById(R.id.tvContador);
        final CheckBox cbDecremento = findViewById(R.id.cbDecremento);
        Switch switchColor = findViewById(R.id.switch1);
        final RadioGroup rgRadio = findViewById(R.id.radioGroup);
        final TextView tvSeleccionado = findViewById(R.id.tvItem);
        Button btFecha = findViewById(R.id.btFecha);
        Button btHora = findViewById(R.id.btHora);
        TextView tvFecha = findViewById(R.id.tvFecha);
        TextView tvHora = findViewById(R.id.tvHora);

        btCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDecremento.isChecked()) {
                    cont--;
                } else {
                    cont++;
                }
                tvCont.setText(cont + "");
            }
        });

        switchColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                } else {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }
        });

        rgRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tvSeleccionado.setText(checkedId + "");
            }
        });

        btFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itFecha=new Intent(getApplicationContext(),Fecha.class);
                startActivityForResult(itFecha,1);
            }
        });

        btHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itHora=new Intent(getApplicationContext(),Hora.class);
                startActivityForResult(itHora,2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==1 && resultCode==RESULT_OK){
            String fecha=data.getStringExtra("Fecha");
            TextView tvFecha = findViewById(R.id.tvFecha);
            tvFecha.setText(fecha);
        }
        if (requestCode==2 && resultCode==RESULT_OK){
            String hora=data.getStringExtra("Hora");
            TextView tvHora = findViewById(R.id.tvHora);
            tvHora.setText(hora);
        }
    }
}
