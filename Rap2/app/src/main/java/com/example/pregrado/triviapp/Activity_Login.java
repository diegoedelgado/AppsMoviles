package com.example.pregrado.triviapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pregrado.rap2.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import Modelo.Usuario;
/**
 * A login screen that offers login via email/password.
 */
public class Activity_Login extends AppCompatActivity {
    private Firebase ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__login);
        setFirebase();
    }

    public void loguin(View view){
        EditText email = (EditText) findViewById(R.id.txtemail);
        EditText contra = (EditText) findViewById(R.id.txtcontra);
        final String e = email.getText().toString();
        final String c = contra.getText().toString();

        final Intent pantallaJuego = new Intent(this,Activity_Juego.class);

        Usuario u = new Usuario(e,0);
        pantallaJuego.putExtra("Usuario",u);

        final Activity_Login pj = this;

        ref.authWithPassword(e, c, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                startActivity(pantallaJuego);

                System.out.println("Entro");
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(pj, "Usuario o contrasena invalida", Toast.LENGTH_SHORT).show();
                System.out.println("Fallo");
            }
        });
    }

    public void registarse(View view){
      Intent pantalla_registro = new Intent(this, Activity_Registro.class);
        startActivity(pantalla_registro);

    }



    public void setFirebase(){
        //INICIALIZACION DE LA BASE DE DATOS
        //Firebase.setAndroidContext(this);
        ref = new Firebase("https://vegantrivia.firebaseio.com");
        //
    }
}

