package com.tfg.dani.testauto.Vista;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tfg.dani.testauto.Modelo.Test;
import com.tfg.dani.testauto.R;

import java.util.ArrayList;


public class MiArrayAdapter extends ArrayAdapter<Test> {


    //ATRIBUTOS: el contexto(activity) y los datos
    private Activity ctx = null; //referencia a la activity para luego poder hacer el findviewByid
    private ArrayList<Test> listTest;

    //ojo que aqui suelen poner Context en vez de activity pero se refieren a la Activity
    public MiArrayAdapter(Activity activity, ArrayList<Test> listTest) {

        super(activity, 0, listTest);

        this.ctx = activity;
        this.listTest = listTest;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View fila = convertView; //vista que vamos a comprobar

        if (fila == null) { //hay que inflar una vista PORQUE HAY HUECO en el viewgroup
            fila = ctx.getLayoutInflater().inflate(R.layout.layout_fila, null);
        } else { //solo reciclar sin inflar

        }


        //rellenar la fila con los datos del test
       TextView tvIdTest = fila.findViewById(R.id.tv_idTest);
        tvIdTest.setText(String.valueOf(listTest.get(position).getId()));

        TextView tvSuspendido = fila.findViewById(R.id.tv_suspendido);
        tvSuspendido.setText(String.valueOf(listTest.get(position).getSuspendido()));

        TextView tvAprobado = fila.findViewById(R.id.tv_aprobado);
        tvAprobado.setText(String.valueOf(listTest.get(position).getAprobado()));

        TextView tvFechaUltimo = fila.findViewById(R.id.tv_fechaUltimo);
        tvFechaUltimo.setText(listTest.get(position).getFechaRealizacion());


        return fila;
    }
}
