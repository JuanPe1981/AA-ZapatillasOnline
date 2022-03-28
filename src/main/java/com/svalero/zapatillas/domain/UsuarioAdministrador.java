package com.svalero.zapatillas.domain;

public class UsuarioAdministrador extends Usuario {
    private int numeroEmpleado;
    private boolean trabajadorActivo;

    public UsuarioAdministrador(String usuario, String contraseña, String nombre, String apellido, String dni, int telefono, int numeroEmpleado, boolean trabajadorActivo){
        super (usuario, contraseña, nombre, apellido, dni, telefono);
        this.numeroEmpleado = numeroEmpleado;
        this.trabajadorActivo = trabajadorActivo;
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
