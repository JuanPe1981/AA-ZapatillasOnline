package com.svalero.zapatillas.domain;

public class Zapatilla {

    private String nombre;
    private String color;
    private int numero;
    private float precio;

    public Zapatilla(String nombre, String color, int numero, float precio){
        this.nombre = nombre;
        this.color = color;
        this.numero = numero;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
