package com.svalero.zapatillas.domain;

import com.svalero.zapatillas.util.DateUtils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int idPedido;
    private String code;
    private boolean pagado;
    private LocalDate fechaPedido;

    private Usuario usuario;
    private List<Zapatilla> zapatillas;

    public Pedido() {
        zapatillas = new ArrayList<>();
    }

    public Pedido(String code, boolean pagado, LocalDate fechaPedido, Usuario usuario) {
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

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Zapatilla> getZapatillas() {
        return zapatillas;
    }

    public void setZapatillas(List<Zapatilla> zapatillas) {
        this.zapatillas = zapatillas;
    }

    @Override
    public String toString() {
        return "Código: " + code + "\n" +
                "Pagado: " + pagado + "\n" +
                "Fecha: " + DateUtils.formatLocalDate(fechaPedido) + "\n";
    }
}
