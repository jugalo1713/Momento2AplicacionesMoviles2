package com.example.fotografos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotografos.model.Fotografos;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class nuevo extends AppCompatActivity {


    private EditText nombre, camara, nacionalidad, tipoFotografia;
    private String nombre_string, camara_string, nacionalidad_string, tipoFotografia_string;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button regresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        nombre = findViewById(R.id.et_nombre);
        camara = findViewById(R.id.et_camara);
        nacionalidad = findViewById(R.id.et_nacionalidad);
        tipoFotografia = findViewById(R.id.et_tipoFotografia);
        regresar = findViewById(R.id.btn_volverNuevo);



        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Registrar(item);

        return true;

    }

    private void limpiarCajas() // FUNCIONANDO OK
    {
        nombre.setText("");
        nacionalidad.setText("");
        camara.setText("");
        tipoFotografia.setText("");
    }


    private void validacion() //FUNCIONANDO OK
    {

        nombre_string = nombre.getText().toString();
        camara_string = camara.getText().toString();
        nacionalidad_string = nacionalidad.getText().toString();
        tipoFotografia_string = tipoFotografia.getText().toString();


        if (nombre_string.equals("")) {
            nombre.setError("Requerido");
        } else if (camara_string.equals("")) {
            camara.setError("Requerido");
        } else if (nacionalidad_string.equals("")) {
            nacionalidad.setError("Requerido");
        } else if (tipoFotografia_string.equals("")) {
            tipoFotografia.setError("Requerido");
        } else {
            Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
        }

    }

    private void Registrar(MenuItem item) {

        nombre_string = nombre.getText().toString();
        camara_string = camara.getText().toString();
        nacionalidad_string = nacionalidad.getText().toString();
        tipoFotografia_string = tipoFotografia.getText().toString();

        switch (item.getItemId()) {
            case R.id.icon_add:
                if (nombre_string.equals("") || camara_string.equals("") || nacionalidad_string.equals("") || tipoFotografia_string.equals("")) {
                    validacion();
                } else {
                    Fotografos fotografo = new Fotografos(camara_string, tipoFotografia_string, nacionalidad_string, nombre_string);

                    Map<String, Object> fotografoA = new HashMap<>();
                    fotografoA.put("nombre", fotografo.getNombre());
                    fotografoA.put("camara", fotografo.getCamara());
                    fotografoA.put("nacionalidad", fotografo.getNacionalidad());
                    fotografoA.put("tipoFotografia", fotografo.getTipoFotografia());

                    db.collection("fotografos")
                            .add(fotografoA);

                    Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                    Intent intent = new Intent(this, MainActivity.class);

                }
                break;
        }
    }



}