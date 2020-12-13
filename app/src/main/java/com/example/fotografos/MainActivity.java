package com.example.fotografos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fotografos.Adapters.FotografoAdapter;
import com.example.fotografos.model.Fotografos;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, camara, nacionalidad, tipoFotografia;
    private String nombre_string, camara_string, nacionalidad_string, tipoFotografia_string;
    private ArrayList<Fotografos> fotografosList = new ArrayList<Fotografos>();
    ;
    private ListView listaPersonalizada;
    String TAG = "JUL";

    FotografoAdapter adapter;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.et_nombre);
        camara = findViewById(R.id.et_camara);
        nacionalidad = findViewById(R.id.et_nacionalidad);
        tipoFotografia = findViewById(R.id.et_tipoFotografia);
        listaPersonalizada = findViewById(R.id.lv_listaPersonalizada);




        adapter = new FotografoAdapter(fotografosList, getApplicationContext());

        listaPersonalizada.setAdapter(adapter);

        listaPersonalizada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String idIntent = String.valueOf(fotografosList.get(position).getFbId());
                Intent intent = new Intent(getApplicationContext(), detalle.class);
                intent.putExtra("id", idIntent);
                startActivity(intent);

            }
        });

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

        Registrar(item);
        //traerDatos();

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
                }
                break;
        }
    }

    private void traerDatos() {

        db.collection("fotografos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for (DocumentSnapshot doc : value) {
                    if (doc.exists()) {

                        String nombre = doc.getString("nombre");
                        String tipoFotografia = doc.getString("tipoFotografia");
                        String nacionalidad = doc.getString("nacionalidad");
                        String camara = doc.getString("camara");
                        String fbId = doc.getId();

                        Fotografos fotografo = new Fotografos(camara, tipoFotografia, nacionalidad, nombre, fbId);
                        fotografosList.add(fotografo);

                        //Si me trae los datos
                        //Toast.makeText(getApplicationContext(), "Importación exitosa: " + fbId + fotografo.getNombre(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No existe collection", Toast.LENGTH_SHORT).show();
                    }
                }

            }


        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        traerDatos();
    }
}



