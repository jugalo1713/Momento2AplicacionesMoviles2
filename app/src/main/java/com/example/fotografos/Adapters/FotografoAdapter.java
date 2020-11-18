package com.example.fotografos.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fotografos.R;
import com.example.fotografos.model.Fotografos;

import java.util.ArrayList;

public class FotografoAdapter extends BaseAdapter {
    private ArrayList<Fotografos> fotografo;
    private Context contextLoc;

    public FotografoAdapter(ArrayList<Fotografos> fotografo, Context context) {
        this.fotografo = fotografo;
        this.contextLoc = context;
    }


    @Override
    public int getCount() {
        return fotografo.size() ;
    }

    @Override
    public Fotografos getItem(int position) {
        return fotografo.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(contextLoc);
            convertView = layoutInflater.inflate(R.layout.fofografosadapter, parent, false);


        }

        TextView tv_nombre = convertView.findViewById(R.id.tv_nombre);
        TextView tv_camara = convertView.findViewById(R.id.tv_camara);
        TextView tv_nacionalidad = convertView.findViewById(R.id.tv_nacionalidad);
        TextView tv_tipoFotografia = convertView.findViewById(R.id.tv_tipoFotografia);

        String nombre = fotografo.get(position).getNombre();
        String camara = fotografo.get(position).getCamara();
        String nacionalidad = fotografo.get(position).getNacionalidad();
        String tipoFotografia = fotografo.get(position).getTipoFotografia();

        tv_nombre.setText(nombre);
        tv_camara.setText(camara);
        tv_nacionalidad.setText(nacionalidad);
        tv_tipoFotografia.setText(tipoFotografia);

        return convertView;
    }
}
