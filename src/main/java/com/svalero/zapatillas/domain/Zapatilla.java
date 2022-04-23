package com.svalero.zapatillas.domain;

import java.util.ArrayList;
import java.util.List;

public class Zapatilla {

    private int idZapatilla;
    private String modelo;
    private String color;
    private int numero;
    private float precio;

    private List<Pedido> pedidos;

    public Zapatilla() {
        pedidos = new ArrayList<>();
    }

    public Zapatilla(String modelo, String color, int numero, float precio){
        this.modelo = modelo;
        this.color = color;
        this.numero = numero;
        this.precio = precio;
        pedidos = new ArrayList<>();
    }

    public int getIdZapatilla() {
        return idZapatilla;
    }

    public void setIdZapatilla(int idZapatilla) {
        this.idZapatilla = idZapatilla;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
