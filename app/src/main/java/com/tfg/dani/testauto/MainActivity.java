package com.tfg.dani.testauto;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "122";
    private EditText txtEmail;
    private EditText txtPass;
    private Button btnRegistrar,btnEntrar;
    private ProgressDialog progressDialog;

    //Declarar el objeto para autenticación
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicio el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();


        txtEmail = findViewById(R.id.txtEmail);
        txtPass = findViewById(R.id.txtPass);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        progressDialog = new ProgressDialog(this);


        btnRegistrar.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);

    }

    private void registrarUsuario() {

        //Obtener email y contraseña
        String email = txtEmail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        //Verificar que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//con esta librería me evito comparar con equals
            Toast.makeText(this, "Introducir un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Introducir contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Realizando el registro de usuario...");
        progressDialog.show();

        //crear un nuevo usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //ver si se pudo hacer y controlar la excepción
                        if(!task.isSuccessful()) {

                            try { throw task.getException();
                            } catch(FirebaseAuthWeakPasswordException e) {
                                Toast.makeText(MainActivity.this, "La contraseña debe tener al menos 6 dígitos", Toast.LENGTH_LONG).show();
                                txtPass.requestFocus();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Toast.makeText(MainActivity.this, "Formato de email incorrecto", Toast.LENGTH_LONG).show();
                                txtEmail.requestFocus();
                            } catch(FirebaseAuthUserCollisionException e) {
                                Toast.makeText(MainActivity.this, "Este usuario ya existe", Toast.LENGTH_LONG).show();
                                 txtEmail.requestFocus();
                            } catch(Exception e) { Log.e(TAG, e.getMessage()); }
                        }
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Se ha registrado el usuario con el email: " + txtEmail.getText(), Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void entrar() {

        //Obtener email y contraseña
        String email = txtEmail.getText().toString().trim();
        String password = txtPass.getText().toString().trim();

        //Verificar que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {//con esta librería me evito comparar con equals
            Toast.makeText(this, "Introducir un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Introducir contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Bienvenido " + txtEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent i=new Intent(getApplication(),ListaTest.class);
                            //paso a la actividad el usuario que ha sido loggeado
                            i.putExtra("usuarioLoggeado", firebaseAuth.getCurrentUser().getUid());

                            startActivity(i);

                        }else{

                            Toast.makeText(MainActivity.this, "Datos incorrectos", Toast.LENGTH_LONG).show();
                            txtEmail.requestFocus();
                            txtPass.requestFocus();
                        }

                        progressDialog.dismiss();
                    }
                });

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.btnRegistrar:
                  registrarUsuario();
                break;
            case R.id.btnEntrar:
                  entrar();
                break;

        }

    }



}
