package com.tfg.dani.testauto.Modelo;

public class CuestionFallada {

    private int id;
    private int contadorFallada;

    public CuestionFallada(int id, int contadorFallada) {
        this.id = id;
        this.contadorFallada = contadorFallada;
    }

    public CuestionFallada() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContadorFallada() {
        return contadorFallada;
    }

    public void setContadorFallada(int contadorFallada) {
        this.contadorFallada = contadorFallada;
    }
}
