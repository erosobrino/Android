package com.example.ero.ejer5_peliculas;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorCompleto extends BaseAdapter {
    ArrayList<Pelicula> lista;
    LayoutInflater inflador;

    public AdaptadorCompleto(Context context, ArrayList<Pelicula> lista) {
        this.inflador = LayoutInflater.from(context);
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Contenedor contenedor = null;
        if (view == null) {
            view = inflador.inflate(R.layout.fila_completa, null);
            contenedor = new Contenedor();
            contenedor.ivPortada = view.findViewById(R.id.ivPortada);
            contenedor.ivFavorito = view.findViewById(R.id.ivFavorito);
            contenedor.ivClasificacion = view.findViewById(R.id.ivClasificacion);
            contenedor.tvTitulo = view.findViewById(R.id.tvTitulo);
            contenedor.tvDirector = view.findViewById(R.id.tvDirector);
            contenedor.tvFecha = view.findViewById(R.id.tvFecha);
            contenedor.tvDuracion = view.findViewById(R.id.tvDuracion);
            contenedor.tvSala = view.findViewById(R.id.tvSala);
            view.setTag(contenedor);
        } else contenedor = (Contenedor) view.getTag();

        Pelicula pelicula = (Pelicula) getItem(position);
        contenedor.ivPortada.setImageResource(pelicula.portada);
        contenedor.ivClasificacion.setImageResource(pelicula.clasi);
        contenedor.tvTitulo.setText(pelicula.titulo);
        contenedor.tvDirector.setText(pelicula.director);
        contenedor.tvFecha.setText(pelicula.getFecha());
        if (pelicula.favorita) {
            contenedor.ivFavorito.setImageResource(R.drawable.ic_favorite_red);
        } else {
            contenedor.ivFavorito.setImageResource(R.drawable.ic_favorite);
        }
        contenedor.tvDuracion.setText(pelicula.duracion+" min");
        contenedor.tvSala.setText(pelicula.sala);

        return view;
    }

    class Contenedor {
        ImageView ivPortada, ivFavorito, ivClasificacion;
        TextView tvTitulo, tvDirector, tvFecha, tvDuracion, tvSala;
    }
}
