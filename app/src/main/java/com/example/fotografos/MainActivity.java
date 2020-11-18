package com.example.fotografos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fotografos.model.Fotografos;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText nombre, camara, nacionalidad, tipoFotografia;
    private String nombre_string, camara_string, nacionalidad_string, tipoFotografia_string;
    String TAG = "JUL";

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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


      /*  // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

                /////////////////////
                db.collection("users")
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });




                */



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
                        Fotografos fotografo = new Fotografos(camara_string, tipoFotografia_string, nacionalidad_string, nombre_string);

                        Map<String, Object> fotografoA = new HashMap<>();
                        fotografoA.put("nombre", fotografo.getNombre());
                        fotografoA.put("camara", fotografo.getCamara());
                        fotografoA.put("nacionalidad", fotografo.getNacionalidad());
                        fotografoA.put("tipo_fotografia", fotografo.getTipoFotografia());

                        db.collection("fotografos")
                                .add(fotografoA);

                        Toast.makeText(this, "Agregado", Toast.LENGTH_LONG).show();
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

    private void Registrar()
    {

    }
}



