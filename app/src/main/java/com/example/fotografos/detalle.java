package com.example.fotografos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fotografos.model.Fotografos;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class detalle extends AppCompatActivity {

    private TextView nombre, camara, nacionalidad, tipoFotografia;
    private String nombre_string, camara_string, nacionalidad_string, tipoFotografia_string, id_string;
    private TextView id;
    private DocumentReference documentReference;
    private Fotografos fotografo;
    private Button volver, actualizar, eliminar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        nombre = findViewById(R.id.et_nombre_Detalle);
        camara = findViewById(R.id.et_camara_Detalle);
        nacionalidad = findViewById(R.id.et_nacionalidad_Detalle);
        tipoFotografia = findViewById(R.id.et_tipoFotografia_Detalle);
        id = findViewById(R.id.et_id_Detalle);
        volver = findViewById(R.id.btn_volver);
        actualizar = findViewById(R.id.btn_actualizar);
        eliminar = findViewById(R.id.btn_eliminar);


        id_string = getIntent().getStringExtra("id");

        id.setText(id_string);

        traerDatoDetallado(id_string);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }


    private void traerDatoDetallado(String idLoc)
    {

        documentReference = db.collection("fotografos")
                .document(idLoc);

        documentReference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                        if (task.isSuccessful())
                        {

                            try {
                                DocumentSnapshot document  = task.getResult();
                                fotografo = document.toObject(Fotografos.class);

                                nombre.setText(fotografo.getNombre());
                                camara.setText(fotografo.getCamara());
                                nacionalidad.setText(fotografo.getNacionalidad());
                                tipoFotografia.setText(fotografo.getTipoFotografia());

                                //Toast.makeText(getApplicationContext(), "El tipo de fotografía es: "+fotografo.getTipoFotografia(), Toast.LENGTH_SHORT).show();

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext(),"Error: "+e, Toast.LENGTH_SHORT).show();
                            }


                        }

                    }
                });





    }


    private void Actualizar() {

        nombre_string = nombre.getText().toString();
        camara_string = camara.getText().toString();
        nacionalidad_string = nacionalidad.getText().toString();
        tipoFotografia_string = tipoFotografia.getText().toString();

        DocumentReference documentReference = db.collection("fotografos").document(id_string);


                if (nombre_string.equals("") || camara_string.equals("") || nacionalidad_string.equals("") || tipoFotografia_string.equals(""))
                {
                    validacion();
                } else
                    {
                    Fotografos fotografo = new Fotografos(camara_string, tipoFotografia_string, nacionalidad_string, nombre_string);

                    Map<String, Object> fotografoA = new HashMap<>();
                    fotografoA.put("nombre", fotografo.getNombre());
                    fotografoA.put("camara", fotografo.getCamara());
                    fotografoA.put("nacionalidad", fotografo.getNacionalidad());
                    fotografoA.put("tipoFotografia", fotografo.getTipoFotografia());

                    documentReference.update(fotografoA);

                    Toast.makeText(this, "Actualizado", Toast.LENGTH_LONG).show();

                }

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


    private void eliminar() {



        DocumentReference documentReference = db.collection("fotografos").document(id_string);

        documentReference.delete();
        Toast.makeText(this, "Se ha eliminado exitosamente", Toast.LENGTH_SHORT).show();
    }




}




