package com.svalero.zapatillas;

import com.svalero.zapatillas.dao.BaseDatos;

import java.sql.Connection;
import java.util.Scanner;

public class MenuPrincipal {
    private Scanner teclado;
    private BaseDatos baseDatos;
    private Connection connection;


    public MenuPrincipal() {
        teclado = new Scanner(System.in);
    }

    public void conectar(){
        BaseDatos baseDatos = new BaseDatos();
        connection = baseDatos.getConnection();
    }

    public void mostrarMenu() {
        conectar();
        String opcion = null;

        do {
            System.out.println("Menu principal Zapatilla");
            System.out.println("1. Menu Usuario");
            System.out.println("2. Menu administrador");
            System.out.println("3. Salir");
            System.out.print("Opcion: ");
            opcion =teclado.nextLine();

            switch (opcion) {
                case "1":
                    menuUsuario();
                    break;
                case "2":
                    menuAdministrador();
            }
        } while (!opcion.equals("3"));
    }

    public void menuUsuario() {
        MenuUsuario menuUsuario = new MenuUsuario();
        menuUsuario.mostrarMenu();
    }

    public void menuAdministrador() {
        MenuAdministrador menuAdministrador = new MenuAdministrador();
        menuAdministrador.mostrarMenu();
    }

}
