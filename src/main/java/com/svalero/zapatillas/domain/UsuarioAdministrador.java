package com.svalero.zapatillas.domain;

import java.time.LocalDate;
import java.util.Date;

public class UsuarioAdministrador extends Usuario {
    private int numeroEmpleado;
    private boolean trabajadorActivo;

    public UsuarioAdministrador(){
    }

    public UsuarioAdministrador(String usuario, String contraseña, String nombre, String apellido, String dni, LocalDate fechaNacimiento, int telefono){
        super (usuario, contraseña, nombre, apellido, dni, fechaNacimiento, telefono);
    }

    public int getNumeroEmpleado() {
        return numeroEmpleado;
    }

    public void setNumeroEmpleado(int numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    public boolean isTrabajadorActivo() {
        return trabajadorActivo;
    }

    public void setTrabajadorActivo(boolean trabajadorActivo) {
        this.trabajadorActivo = trabajadorActivo;
    }

}
