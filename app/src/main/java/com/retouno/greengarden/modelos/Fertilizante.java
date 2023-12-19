package com.retouno.greengarden.modelos;

public class Fertilizante {

    private float gramos;
    private float precio;
    private String mes;

    public Fertilizante(float gramos, float precio, String mes) {
        this.gramos = gramos;
        this.precio = precio;
        this.mes = mes;
    }

    public float getGramos() {
        return gramos;
    }

    public void setGramos(float gramos) {
        this.gramos = gramos;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

}
