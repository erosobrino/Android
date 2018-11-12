package com.example.ero.ejer5_peliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListadoCompletoActivity extends AppCompatActivity {
    AdaptadorCompleto adaptador;
    ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_completo);
        setTitle(R.string.listado_completo);

        Intent itPrincipal = getIntent();
        peliculas = (ArrayList<Pelicula>) itPrincipal.getExtras().get("peliculas");


        adaptador = new AdaptadorCompleto(this, peliculas);
        ListView lista = findViewById(R.id.listaCompleta);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itSinopsis=new Intent(getApplicationContext(),SinopsisPeliculaActivity.class);
                startActivity(itSinopsis);
            }
        });

    }
}
