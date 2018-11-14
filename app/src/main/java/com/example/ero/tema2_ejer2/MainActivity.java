package com.example.ero.tema2_ejer2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Libro implements Serializable {
    public String titulo;
    public String autor;
    public int valoracion;

    public Libro(String titulo, String autor, int valoracion) {
        this.titulo = titulo;
        this.autor = autor;
        this.valoracion = valoracion;
    }
}

public class MainActivity extends AppCompatActivity {
    int indiceSeleccionado = -1;
    private static final String TAG = "Procesos";
    Libro libroNuevo = null;
    final List<Libro> libros = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toast alerta1 = Toast.makeText(getApplicationContext(), "Creando", Toast.LENGTH_SHORT);
//        alerta1.show();
//        File f = new File(getFilesDir(), "ero.txt");
//        if (!f.exists()) {
//            try {
//                f.createNewFile();
//                Toast alerta = Toast.makeText(getApplicationContext(), "Se crea", Toast.LENGTH_SHORT);
//                alerta.show();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            Toast alerta = Toast.makeText(getApplicationContext(), "ya existe " + f.getAbsolutePath(), Toast.LENGTH_LONG);
//            alerta.show();
//        }
//        try (FileWriter fichOut = new FileWriter(f)) {
//            while (true) {
//                fichOut.write("funciona");
//            }
//        } catch (Exception e) {
//
//        }

        Libro libro1 = new Libro("Libro1", "Ero", 5);
        libros.add(libro1);
        Libro libro2 = new Libro("Libro2", "Julio", 1);
        libros.add(libro2);
        Libro libro3 = new Libro("Libro3", "Miguel", 2);
        libros.add(libro3);
        Libro libro4 = new Libro("Libro4", "Dani", 4);
        libros.add(libro4);

        final TextView tvCantidad = findViewById(R.id.cantidad);
        final TextView tvTitulo = findViewById(R.id.titulo);
        final TextView tvAutor = findViewById(R.id.autor);
        final RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setEnabled(false);
        Button anterior = findViewById(R.id.anterior);
        Button siguiente = findViewById(R.id.siguiente);
        Button nuevo = findViewById(R.id.nuevo);
        Button borrar = findViewById(R.id.borrar);
        Button buscar = findViewById(R.id.buscar);
        tvCantidad.setText("Hay 0 libros");

        if (libros.size() > 0) {
            indiceSeleccionado = 0;
            modificarPantalla(tvCantidad, tvTitulo, tvAutor, ratingBar, libros);

            nuevo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent itAñadir = new Intent(MainActivity.this, IntroducirLibro.class);
                    startActivityForResult(itAñadir, 1);
                }
            });

            anterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indiceSeleccionado > 0 && libros.size() > 0) {
                        if (libros.size() > 0) {
                            indiceSeleccionado--;
                            if (indiceSeleccionado < 0) {
//                                Toast alerta = Toast.makeText(getApplicationContext(), "No hay mas", Toast.LENGTH_SHORT);
//                                alerta.show();
                            } else {
                                modificarPantalla(tvCantidad, tvTitulo, tvAutor, ratingBar, libros);
                            }
                        }
                    }
                }
            });

            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indiceSeleccionado < (libros.size() - 1) && libros.size() > 0) {
                        indiceSeleccionado++;
                        modificarPantalla(tvCantidad, tvTitulo, tvAutor, ratingBar, libros);
                    } else {
//                        Toast alerta = Toast.makeText(getApplicationContext(), "No hay mas", Toast.LENGTH_SHORT);
//                        alerta.show();
                    }
                    Log.i(TAG, "" + indiceSeleccionado);
                }
            });

            borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (indiceSeleccionado >= 0 && libros.size() > 0) {
                        libros.remove(indiceSeleccionado);
                        if (indiceSeleccionado >= 0 && indiceSeleccionado < libros.size()) {
                            modificarPantalla(tvCantidad, tvTitulo, tvAutor, ratingBar, libros);
                        } else {
                            if (libros.size() > 0) {
                                indiceSeleccionado--;
                                modificarPantalla(tvCantidad, tvTitulo, tvAutor, ratingBar, libros);
                            } else {
                                tvCantidad.setText("No hay libros");
                                tvTitulo.setText("");
                                tvAutor.setText("");
                                ratingBar.setRating(0);
                            }
                        }
                    } else {
                        Toast alerta = Toast.makeText(getApplicationContext(), "No hay mas", Toast.LENGTH_SHORT);
                        alerta.show();
                    }
                    Log.i(TAG, "borrado" + indiceSeleccionado);
                }
            });

            buscar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (libros.size() > 0) {
                        Intent itBuscar = new Intent(Intent.ACTION_VIEW);
                        itBuscar.setData(Uri.parse("http://www.google.es/search?q=" + libros.get(indiceSeleccionado).titulo));
                        startActivity(itBuscar);
                    } else {
                        Toast alerta = Toast.makeText(getApplicationContext(), "No hay libros a buscar", Toast.LENGTH_SHORT);
                        alerta.show();
                    }
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == 1) && (resultCode == RESULT_OK)) {
            libroNuevo = ((Libro) data.getSerializableExtra("libro"));
            libros.add(libroNuevo);
            TextView tvCantidad = findViewById(R.id.cantidad);
            TextView tvTitulo = findViewById(R.id.titulo);
            TextView tvAutor = findViewById(R.id.autor);
            RatingBar ratingBar = findViewById(R.id.ratingBar);
            tvCantidad.setText("Libro " + (indiceSeleccionado + 1) + "/" + libros.size());
            tvTitulo.setText(libros.get(indiceSeleccionado).titulo);
            tvAutor.setText(libros.get(indiceSeleccionado).autor);
            ratingBar.setRating(libros.get(indiceSeleccionado).valoracion);
        }
    }

    public void modificarPantalla(TextView tvCantidad, TextView tvTitulo, TextView
            tvAutor, RatingBar ratingBar, List<Libro> libros) {
        int cantidad = indiceSeleccionado + 1;
        Log.i(TAG, "cant" + cantidad);
        tvCantidad.setText("Libro " + cantidad + "/" + libros.size());
        tvTitulo.setText(libros.get(indiceSeleccionado).titulo);
        tvAutor.setText(libros.get(indiceSeleccionado).autor);
        ratingBar.setRating(libros.get(indiceSeleccionado).valoracion);
    }
}

