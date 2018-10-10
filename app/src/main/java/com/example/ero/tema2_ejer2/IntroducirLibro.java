package com.example.ero.tema2_ejer2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class IntroducirLibro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducir_libro);

        final EditText etTitulo=findViewById(R.id.etTitulo);
        final EditText etAutor=findViewById(R.id.etAutor);
        final RatingBar ratingBar=findViewById(R.id.ratingBar2);
        Button añadir=findViewById(R.id.Añadir);
        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etTitulo.length()!=0 && etAutor.length()!=0){
                    String titulo=etTitulo.getText().toString();
                    String autor=etAutor.getText().toString();
                    int puntuacion= ((int) ratingBar.getRating());
                    Intent intentSec=getIntent();
                    Libro libro=new Libro(titulo,autor,puntuacion);
                    intentSec.putExtra("libro",libro);
                    setResult(RESULT_OK, intentSec);
                    finish();
                }else{
                    Toast toast=Toast.makeText(getApplicationContext(),"Introduce los datos", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
    }
}
