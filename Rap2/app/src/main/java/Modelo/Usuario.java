package Modelo;

import java.io.Serializable;

/**
 * Created by Pregrado on 31/03/2016.
 */
public class Usuario implements Serializable {

    private String correo;
    private int puntaje;

    public Usuario() {
    }

    public Usuario(String correo, int puntaje) {
        this.correo = correo;
        this.puntaje = puntaje;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

}
