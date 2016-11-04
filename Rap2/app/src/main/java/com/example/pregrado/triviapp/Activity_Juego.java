package com.example.pregrado.triviapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pregrado.rap2.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.util.Random;

import Modelo.Pregunta;
import Modelo.Intento;

public class Activity_Juego extends AppCompatActivity {

    private Firebase ref;
    private int num;
    private int c;
    private int m;
    int x=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla__juego);
        num = -1;

        setFirebase();
        consultarPreguntaYPintar();
    }

    public void verificarRespuesta(View view){
        consultarPreguntaYPintar();
        final int cantB=0;
        x++;

        if(num != -1) {
            final Button botonElegido = (Button) view;
            //Referencio el nodo de preguntas
            Firebase ref1 = ref.child("preguntas");
            //Ordeno por el id de las preguntas
            Query queryRef = ref1.orderByChild("id").equalTo(num);
            final Activity_Juego pj = this;
            //Muestra pregunta.
            queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        Pregunta p = postSnapshot.getValue(Pregunta.class);

                        if(botonElegido.getText().equals(p.respuestaCorrecta())){
                            System.out.println("Correcto");
                                c++;
                            Toast.makeText(pj,"Correcto",Toast.LENGTH_SHORT).show();

                        }else{
                            System.out.println(":/");
                            Toast.makeText(pj,":/",Toast.LENGTH_SHORT).show();
                            m++;
                        }
                    }
                }
                @Override
                public void onCancelled(FirebaseError firebaseError) {
                }
            });
        }

        Firebase ref2 = ref.child("intentos");
        final Query query2 = ref2.orderByChild("id").equalTo(num);
        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //DataSnapshot post = dataSnapshot.getChildren();
                //post.getRef().setValue(m);
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Intento i = postSnapshot.getValue(Intento.class);
                    i.setM(m+"");
                    i.setC(c+"");
                    postSnapshot.getRef().setValue(i);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        if(x==5){

            Intent intent= new Intent(this, Activity_Estadistica.class);
            startActivity(intent);
            finish();
        }
    }

    public void setFirebase(){
        //INICIALIZACION DE LA BASE DE DATOS
        //Firebase.setAndroidContext(this);
        ref = new Firebase("https://vegantrivia.firebaseio.com");
        //
    }

    public void consultarPreguntaYPintar(){
        //Eleccion aleatoria de la pregunta a mostrar
        Random r = new Random();
        num = r.nextInt(5)+1;
        System.out.println("La pregunta a mostrarse  numero "+num);
        //

        //Referencio el nodo de preguntas
        Firebase ref1 = ref.child("preguntas");
        //Ordeno por el id de las preguntas
        Query queryRef = ref1.orderByChild("id").equalTo(num);

        //pintando la pregunta.
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Pregunta p = postSnapshot.getValue(Pregunta.class);
                    System.out.println(num + p.getPregunta());
                    String[] temp = p.getRespuestas();
                    shuffleArreglo(temp);
                    pintarPregunta(num,p.getPregunta(),temp);
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    public void shuffleArreglo(String[] temp) {
        //Incia en la ultima posicion
        for (int i = temp.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i+1));
            String t = temp[i];
            temp[i] = temp[rand];
            temp[rand] = t;
        }
    }

    public void pintarPregunta(int num,String pre,String[] res){
        Typeface font = Typeface.createFromAsset(getAssets(), "aqua.ttf");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.pantallajuego);
        TextView pregunta = (TextView) findViewById(R.id.labelPregunta);
        pregunta.setTypeface(font);
        pregunta.setTextColor(Color.WHITE);
        Button boton1 = (Button) findViewById(R.id.botonR1);
        Button boton2 = (Button) findViewById(R.id.botonR2);
        Button boton3 = (Button) findViewById(R.id.botonR3);
        Button boton4 = (Button) findViewById(R.id.botonR4);

        switch (num){
            case 1:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.uno));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(216,103,19),Color.rgb(251,176,59));
                break;
            case 2:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.dos));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(234,155,15),Color.rgb(242,204,15));
                break;
            case 3:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.tres));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(234,155,15),Color.rgb(242,204,15));
                break;
            case 4:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.cuatro));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(216,103,19),Color.rgb(251,176,59));
                break;
            case 5:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.cinco));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(235,255,41),Color.rgb(199,255,44));
                break;
            case 6:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.seis));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(235,255,41),Color.rgb(199,255,44));
                break;
            case 7:
                layout.setBackgroundDrawable(getResources().getDrawable(R.drawable.siete));
                pregunta.setText(pre);
                pintar(boton1, boton2, boton3, boton4, res, Color.rgb(235,255,41),Color.rgb(199,255,44));
                break;
        }
    }

    public void pintar(Button boton1,Button boton2, Button boton3, Button boton4,String[] res,int col,int col2){
        Typeface font = Typeface.createFromAsset(getAssets(), "aqua.ttf");
        boton1.setText(res[0]);boton1.setVisibility(View.VISIBLE);boton1.setBackgroundColor(col);boton1.setTypeface(font);boton1.setTextColor(Color.WHITE);
        boton2.setText(res[1]);boton2.setVisibility(View.VISIBLE);boton2.setBackgroundColor(col2);boton2.setTypeface(font);boton2.setTextColor(Color.WHITE);
        boton3.setText(res[2]);boton3.setVisibility(View.VISIBLE);boton3.setBackgroundColor(col);boton3.setTypeface(font);boton3.setTextColor(Color.WHITE);
        boton4.setText(res[3]);boton4.setVisibility(View.VISIBLE);boton4.setBackgroundColor(col2);boton4.setTypeface(font);boton4.setTextColor(Color.WHITE);
    }
}
