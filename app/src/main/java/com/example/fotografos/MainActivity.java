package com.example.fotografos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, camara, nacionalidad, tipoFotografia;
    private String nombre_string, camara_string, nacionalidad_string, tipoFotografia_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.et_nombre);
        camara = findViewById(R.id.et_camara);
        nacionalidad = findViewById(R.id.et_nacionalidad);
        tipoFotografia = findViewById(R.id.et_tipoFotografia);




        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        nombre_string = nombre.getText().toString();
        camara_string = camara.getText().toString();
        nacionalidad_string = nacionalidad.getText().toString();
        tipoFotografia_string = tipoFotografia.getText().toString();

        switch (item.getItemId())
        {
            case R.id.icon_add:
                    if (nombre_string.equals("") || camara_string.equals("")  || nacionalidad_string.equals("")  || tipoFotografia_string.equals("")  )
                    {
                        validacion();
                    }
                    else
                    {
                        Toast.makeText(this, "Agregado"+ camara_string, Toast.LENGTH_LONG).show();
                        limpiarCajas();
                    }
                break;


        }
        return true;

    }

    private void limpiarCajas() {
        nombre.setText("");
        nacionalidad.setText("");
        camara.setText("");
        tipoFotografia.setText("");
    }

    private void validacion()
    {

        nombre_string = nombre.getText().toString();
        camara_string = camara.getText().toString();
        nacionalidad_string = nacionalidad.getText().toString();
        tipoFotografia_string = tipoFotografia.getText().toString();


        if (nombre_string.equals("")   )
        {
            nombre.setError("Requerido");
        }
        else if(camara_string.equals(""))
        {
            camara.setError("Requerido");
        }
        else if(nacionalidad_string.equals(""))
        {
            nacionalidad.setError("Requerido");
        }
        else if (tipoFotografia_string.equals(""))
        {
            tipoFotografia.setError("Requerido");
        }
        else
        {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }
}