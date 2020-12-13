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
import android.widget.Button;
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


    private ArrayList<Fotografos> fotografosList = new ArrayList<Fotografos>();;
    private FotografoAdapter adapter;
    private ListView listaPersonalizada;
    private Button nuevo;
    private Fotografos fotografo;
    private String nombre, tipoFotografia, nacionalidad, camara, fbId;
    String TAG = "JUL";

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nuevo = findViewById(R.id.btn_nuevo);
        listaPersonalizada = findViewById(R.id.lv_listaPersonalizada);




        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), nuevo.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        traerDatos();
    }

    public void traerDatos() {



        db.collection("fotografos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for (DocumentSnapshot doc : value) {
                    if (doc.exists()) {

                        nombre = doc.getString("nombre");
                        tipoFotografia = doc.getString("tipoFotografia");
                        nacionalidad = doc.getString("nacionalidad");
                        camara = doc.getString("camara");
                        fbId = doc.getId();

                        fotografo = new Fotografos(camara, tipoFotografia, nacionalidad, nombre, fbId);
                        fotografosList.add(fotografo);


                        //Si me trae los datos
                        //Toast.makeText(getApplicationContext(), "Importación exitosa: " + fbId + fotografo.getNombre(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No existe collection", Toast.LENGTH_SHORT).show();
                    }
                }

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

            }
        });


    }


}



