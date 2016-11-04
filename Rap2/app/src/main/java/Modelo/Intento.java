package Modelo;

import java.io.Serializable;
import java.util.Arrays;
/**
 * Created by DIEGOEDELGADO on 03/11/2016.
 */

public class Intento implements Serializable{

    private String m;
    private String c;
    private int id;

    public Intento() {
    }

    public String getM() {
        return m;
    }

    public void setM(String M) {
        this.m = m;
    }

    public String getC() {
        return c;
    }

    public void setC(String C) {
        this.c = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "intentos{" +
                "c='" + c + '\'' +
                ", id='" + id + '\'' +
                ", m=" + m +
                '}';
    }
}
