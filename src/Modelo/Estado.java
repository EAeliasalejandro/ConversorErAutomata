/*
 * Modelo del estado
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author EAeli
 */
public class Estado {
    private ArrayList<Estado> liga;
    private String numEstado;
    private ArrayList<String> transisiones;

    public ArrayList<Estado> getLiga() {
        return liga;
    }

    public void setLiga(ArrayList<Estado> liga) {
        this.liga = liga;
    }

    public String getNumEstado() {
        return numEstado;
    }

    public void setNumEstado(String numEstado) {
        this.numEstado = numEstado;
    }

    public ArrayList getTransisiones() {
        return transisiones;
    }

    public void setTransisiones(ArrayList transisiones) {
        this.transisiones = transisiones;
    }
    
    
}
