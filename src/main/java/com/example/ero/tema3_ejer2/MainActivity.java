package com.example.ero.tema3_ejer2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
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



        btCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbDecremento.isChecked()) {
                    cont--;
                } else {
                    cont++;
                }
                tvCont.setText(cont+"");
            }
        });

        switchColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
                }else{
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
            }
        });

        rgRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                tvSeleccionado.setText(checkedId+"");
            }
        });
    }
}
