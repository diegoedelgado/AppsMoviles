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

import Modelo.Intento;
import Modelo.Usuario;

public class Activity_Estadistica extends AppCompatActivity {

    private Firebase ref;
    private TextView n1;
    private TextView n2;
    private TextView n3;
    private TextView n4;
    private TextView n5;

    private TextView n6;
    private TextView n7;
    private TextView n8;
    private TextView n9;
    private TextView n10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadistica);
        setFirebase();
        Firebase.setAndroidContext(this);
        n1= (TextView) findViewById(R.id.p1C);
        n2= (TextView) findViewById(R.id.p1M);
        n3= (TextView) findViewById(R.id.p2C);
        n4= (TextView) findViewById(R.id.p2M);
        n5= (TextView) findViewById(R.id.p3C);
        n6= (TextView) findViewById(R.id.p3M);
        n7= (TextView) findViewById(R.id.p4C);
        n8= (TextView) findViewById(R.id.p4M);
        n9= (TextView) findViewById(R.id.p5C);
        n10= (TextView) findViewById(R.id.p5M);
    }

    public void pintarRanking(){

        Firebase ref1 = ref.child("intentos");
        ref1.addValueEventListener(new ValueEventListener() {
            int cantidad=0;
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Intento u = postSnapshot.getValue(Intento.class);
                    cantidad++;
                }

                for(int i=0;i<cantidad;i++){
                    if(i==0){
                        n1.setText(2+i);
                        n2.setText(3+i);
                    }
                    if(i==1){
                        n3.setText(3+i);
                        n4.setText(2+i);
                    }
                    if(i==2){
                        n5.setText(4+i);
                        n6.setText(2+i);
                    }
                    if(i==3){
                        n7.setText(4+i);
                        n8.setText(2+i);
                    }
                    if(i==4){
                        n9.setText(3+i);
                        n10.setText(5+i);
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });

    }

    public void setFirebase(){
        //INICIALIZACION DE LA BASE DE DATOS
        //Firebase.setAndroidContext(this);
        ref = new Firebase("https://vegantrivia.firebaseio.com");
        //
    }

}
