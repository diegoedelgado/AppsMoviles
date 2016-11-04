package Modelo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Pregrado on 28/03/2016.
 */
public class Pregunta implements Serializable{

    private String pregunta;
    private String respuesta;
    private int id;

    public Pregunta() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String[] getRespuestas() {
        return respuesta.split(",");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String respuestaCorrecta(){
        return respuesta.split(",")[0];
    }

    public String[] respuestasIncorrectas(){
        String[] temp = respuesta.split(",");
        return new String[]{temp[1],temp[2],temp[3]};
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "pregunta='" + pregunta + '\'' +
                ", respuesta='" + respuesta + '\'' +
                ", id=" + id +
                '}';
    }
}

