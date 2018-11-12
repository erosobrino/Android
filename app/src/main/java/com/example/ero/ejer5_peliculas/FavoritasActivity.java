package com.example.ero.ejer5_peliculas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavoritasActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);
        setTitle(R.string.peliculas_favoritas);

        final ListView lvFavoritas = findViewById(R.id.lvFavoritas);
        lvFavoritas.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Intent itPrincipal = getIntent();

        peliculas = (ArrayList<Pelicula>) itPrincipal.getExtras().get("peliculas");
        ArrayList<String> tituloPeliculas = new ArrayList<>();
        for (int i = 0; i < peliculas.size(); i++) {
            tituloPeliculas.add(peliculas.get(i).titulo);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_checked, tituloPeliculas);
        lvFavoritas.setAdapter(adapter);

        for (int j = 0; j < peliculas.size(); j++) {
            lvFavoritas.setItemChecked(j, peliculas.get(j).favorita);
        }
        adapter.notifyDataSetChanged();

        lvFavoritas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                peliculas.get(position).setFavorita(lvFavoritas.isItemChecked(position));
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btVolver:
                Intent itDevolverFav = new Intent();
                itDevolverFav.putExtra("peliculas", peliculas);
                setResult(RESULT_OK, itDevolverFav);
                finish();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritas, menu);
        return true;
    }
}
