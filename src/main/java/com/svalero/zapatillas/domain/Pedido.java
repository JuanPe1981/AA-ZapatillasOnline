package com.svalero.zapatillas.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private String code;
    private boolean pagado;
    private Date fechaPedido;

    private Usuario usuario;
    private List<Zapatilla> zapatillas;

    public Pedido() {
        zapatillas = new ArrayList<>();
    }

    public Pedido(String code, boolean pagado, Date fechaPedido, Usuario usuario) {
        this.code = code;
        this.pagado = pagado;
        this.fechaPedido = fechaPedido;
        this.usuario = usuario;
        zapatillas = new ArrayList<>();
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}
