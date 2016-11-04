package com.example.pregrado.triviapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pregrado.rap2.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

import Modelo.Usuario;

public class PantallaRanking extends AppCompatActivity {

private Firebase ref;

    private TextView n1;
    private TextView n2;
    private TextView n3;
    private TextView n4;
    private TextView n5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://vegantrivia.firebaseio.com");
        n1= (TextView) findViewById(R.id.p1);
        n2= (TextView) findViewById(R.id.p2);
        n3= (TextView) findViewById(R.id.p3);
        n4= (TextView) findViewById(R.id.p4);
        n5= (TextView) findViewById(R.id.p5);

        pintarRanking();

    }


    public void pintarRanking(){

        Firebase ref1 = ref.child("usuarios");
        final ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ref1.addValueEventListener(new ValueEventListener() {
            int cantidad=0;
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Usuario u = postSnapshot.getValue(Usuario.class);
                    usuarios.add(u);
                    cantidad++;
                    System.out.println(u.getCorreo() + " - " + u.getPuntaje()+ " - sfgfgsdvsdvsfbsfbdfbdfbbbbbbbbbbbbbbbbbdfbdbf");
                }
                Usuario[] arrayUsuarios = new Usuario[cantidad];
                Arrays.sort(arrayUsuarios);
                for(int i=0;i<cantidad;i++){
                    if(i==0){
                        n1.setText(arrayUsuarios[i].getCorreo());
                    }
                    if(i==1){
                        n2.setText(arrayUsuarios[i].getCorreo());
                    }
                    if(i==2){
                        n3.setText(arrayUsuarios[i].getCorreo());
                    }
                    if(i==3){
                        n4.setText(arrayUsuarios[i].getCorreo());
                    }
                    if(i==4){
                        n5.setText(arrayUsuarios[i].getCorreo());
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }




}
