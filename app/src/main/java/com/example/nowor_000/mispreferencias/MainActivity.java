package com.example.nowor_000.mispreferencias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText edNombre, edContraseña;
    private Button btnEntrar;
    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctx = this;
        //añadir elementos layout
        addViews();

        comprobarPreferencias();


        btnEntrar.setOnClickListener(new View.OnClickListener() {//validar nombre y contraseña
            @Override
            public void onClick(View v) {

                String nombre = edNombre.getText().toString();
                String contraseña = edContraseña.getText().toString();

                if (validarString(nombre) && validarString(contraseña)) {

                    /**
                     * modificar pref
                     */
                    PreferenciasFragment.setString(ctx, PreferenciasFragment.getKeyNombreEd(), nombre);
                    PreferenciasFragment.setString(ctx, PreferenciasFragment.getKeyContraseñaEd(), contraseña);

                    PreferenciasFragment.showUserSettings(ctx);

                } else {
                    Toast.makeText(getApplicationContext(), "Datos incompletos", Toast.LENGTH_SHORT).show();


                }


            }
        });

    }

    private void addViews() {


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        btnEntrar = (Button) findViewById(R.id.btnEntrar);
        edNombre = (EditText) findViewById(R.id.edNombre);
        edContraseña = (EditText) findViewById(R.id.edContraseña);

    }

    //<editor-fold desc="Menu">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, PreferenciasActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //</editor-fold>


    public void comprobarPreferencias() {


        if (PreferenciasFragment.getBoolean(ctx, PreferenciasFragment.getKeyNombreChk(), false))
            edNombre.setText(PreferenciasFragment.getString(ctx, PreferenciasFragment.getKeyNombreEd()));


        if (PreferenciasFragment.getBoolean(ctx, PreferenciasFragment.getKeyContraseñaChk(), false))
            edContraseña.setText(PreferenciasFragment.getString(ctx, PreferenciasFragment.getKeyContraseñaEd()));


    }

    Boolean validarString(String texto) {
        return texto != null && texto.trim().length() > 0;
    }
}
