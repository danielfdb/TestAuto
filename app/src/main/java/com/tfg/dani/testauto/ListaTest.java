package com.tfg.dani.testauto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tfg.dani.testauto.Modelo.Test;
import com.tfg.dani.testauto.Vista.MiArrayAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class ListaTest extends AppCompatActivity {

    private ListView lvTest;
    private ArrayList<Test> listTest;
    private ArrayAdapter<Test> adapterTest;

    //para las referencias a mi BBDD por usuario
    private DatabaseReference refMiBBDD;
    private DatabaseReference userLoggeado;
    private String usuarioActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_test);
        lvTest=findViewById(R.id.lvTest);

        Intent intent = getIntent();
        usuarioActual=intent.getExtras().getString("usuarioLoggeado");

        refMiUSer(); //para controlar el usuario

        mostrarDatos();

        //introducirDatosDePrueba();

        adapterTest = new MiArrayAdapter(this, listTest);
        // Asignamos el adaptador al spinner
        lvTest.setAdapter(adapterTest);
    }

    //*************************************PARA VER QUE LOS DATOS INTRODUCIDOS PERTENECEN SOLO A ESE USUARIO*****************************
    private void introducirDatosDePrueba() {

        Test pruebaTest = new Test();

        pruebaTest.setUid(UUID.randomUUID().toString()); //esta linea para asegurar la persistencia correctamente

        pruebaTest.setId(1111);
        pruebaTest.setAprobado(1);
        pruebaTest.setSuspendido(0);
        pruebaTest.setContRealizado(0);
        pruebaTest.setFechaRealizacion(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

        //introduzco los datos en el (hijo que corresponde dentro de mi usuario)
        userLoggeado.child("Test").child(pruebaTest.getUid()).setValue(pruebaTest);

    }


    private void refMiUSer() {

        refMiBBDD= FirebaseDatabase.getInstance().getReference().child("usuarios");
        //carpeta con el nombre de identificador del usuario correspondiente
        userLoggeado=refMiBBDD.child(usuarioActual);

    }

    private void mostrarDatos() {

        listTest= new ArrayList<Test>();

        //entro a la "clase" Test dentro del usuario correspondiente
        userLoggeado.child("Test").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listTest.clear();
                //recorro todos los hijos de Test para sacar sus datos
                for(DataSnapshot objSnapShot:dataSnapshot.getChildren()){
                  Test test=objSnapShot.getValue(Test.class);
                  listTest.add(test);

                  //añado mi adapterPropio
                  adapterTest= new MiArrayAdapter(ListaTest.this,listTest);
                  lvTest.setAdapter(adapterTest);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        }
}
