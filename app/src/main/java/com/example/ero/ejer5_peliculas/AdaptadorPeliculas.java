package com.example.ero.ejer5_peliculas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorPeliculas extends BaseAdapter {
    ArrayList<Pelicula> lista;
    LayoutInflater inflador;

    public AdaptadorPeliculas(Context contexto, ArrayList<Pelicula> lista) {
        this.inflador = LayoutInflater.from(contexto);
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
            view = inflador.inflate(R.layout.fila, null);
            contenedor = new Contenedor();
            contenedor.logotipo = (ImageView) view.findViewById(R.id.ivPortada);
            contenedor.clasificacion = (ImageView) view.findViewById(R.id.ivClasificacion);
            contenedor.txtTitulo = (TextView) view.findViewById(R.id.tvTituloPrincipal);
            contenedor.txtDirector = (TextView) view.findViewById(R.id.tvDirector);
            view.setTag(contenedor);
        } else contenedor = (Contenedor) view.getTag();

        Pelicula pelicula = (Pelicula) getItem(position);
        contenedor.logotipo.setImageResource(pelicula.portada);
        contenedor.clasificacion.setImageResource(pelicula.clasi);
        contenedor.txtTitulo.setText(pelicula.titulo);
        contenedor.txtDirector.setText(pelicula.director);

        return view;
    }

    class Contenedor {
        ImageView logotipo, clasificacion;
        TextView txtTitulo, txtDirector;
    }
}