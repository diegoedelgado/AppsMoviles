package com.example.pregrado.triviapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pregrado.rap2.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import Modelo.Usuario;

public class Activity_Registro extends AppCompatActivity {

    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__registro);
        setFirebase();
    }

    public void registrarUsuario(View view){

        EditText email = (EditText) findViewById(R.id.txtemail2);
        EditText contra = (EditText) findViewById(R.id.txtcontra2);
        final String e = email.getText().toString();
        final String c = contra.getText().toString();

        final Intent pantallaJuego = new Intent(this,Activity_Juego.class);
        Usuario u = new Usuario(e,0);
        pantallaJuego.putExtra("Usuario",u);
        final Activity_Registro pj = this;

        ref.createUser(e,c,new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                char arroba = 64;
                Firebase user = ref.child("usuarios").child(c.split(String.valueOf(arroba))[0]);
                Usuario u = new Usuario(c, 0);
                user.setValue(u);
                startActivity(pantallaJuego);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Toast.makeText(pj, "Usuario o contraseña invalida", Toast.LENGTH_SHORT).show();            }
        });

    }

    public void setFirebase(){
        //INICIALIZACION DE LA BASE DE DATOS
        //Firebase.setAndroidContext(this);
        ref = new Firebase("https://vegantrivia.firebaseio.com");
        //
    }

}
