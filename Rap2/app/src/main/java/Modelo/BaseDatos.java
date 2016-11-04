package Modelo;

import com.example.pregrado.triviapp.Activity_Principal;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Created by Pregrado on 29/03/2016.
 */
public class BaseDatos implements Serializable{

    final private Firebase ref;
    Activity_Principal pantalla;
    private Usuario usuario;

    public BaseDatos(Firebase ruta, Activity_Principal pantalla_principal) {
        this.ref = ruta;
        this.pantalla = pantalla_principal;
    }

    public Pregunta[] recuperarPreguntas(final int num){

        Firebase ref1 = ref.child("preguntas");
        final Pregunta[] p = new Pregunta[num];
        Pregunta[] temp = new Pregunta[num];
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
                for(int i = 0; i<num && iter.hasNext(); i++){
                    DataSnapshot postSnapshot = iter.next();
                    p[i]= (Pregunta) postSnapshot.getValue(Pregunta.class);
                    System.out.println(p[i].toString());
                }
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }

        });
        return p;
    }

//    public Pregunta[] recuperarPreguntas(final int num){
//
//        Firebase ref1 = ref.child("preguntas");
//        Pregunta[] p = new Pregunta[num];
//        WrapValueEventListener j = new WrapValueEventListener(p){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Pregunta[] p=(Pregunta[])this.object;
//                Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator();
//                for(int i = 0; i<num && iter.hasNext(); i++){
//                    DataSnapshot postSnapshot = iter.next();
//                    p[i]= (Pregunta) postSnapshot.getValue(Pregunta.class);
//                    System.out.println(p[i].toString());
//                }
//            }
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        };
//        ref1.addListenerForSingleValueEvent(j);
//        return p;
//    }


    public void registrarUsuario(String correo,String contrasena){

        final String c = correo;
        final String p =  contrasena;

        ref.createUser(c,p, new Firebase.ResultHandler() {
            @Override
            public void onSuccess() {
                char arroba = 64;
                Firebase user = ref.child("usuarios").child(c.split(String.valueOf(arroba))[0]);
                 Usuario u = new Usuario(c,0);
                user.setValue(u);
                System.out.println("Se registro correctamente");
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                System.out.println("No se registro");
            }
        });

    }
}
