package com.svalero.zapatillas;
import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MenuUsuario {
    private Scanner teclado;
    private BaseDatos baseDatos;
    private Connection connection;

    public MenuUsuario() {
        teclado = new Scanner(System.in);
    }

    public void conectar(){
        BaseDatos baseDatos = new BaseDatos();
        connection = baseDatos.getConnection();
    }

    public void mostrarMenu(){
        conectar();
        String opcion = null;

        do {
            System.out.println("Catalogo de Zapatillas");
            System.out.println("1. Ver catalogo");
            System.out.println("2. Buscar Zapatilla");
            System.out.println("3. Salir");
            System.out.println("Opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    verCatalogo();
                    break;
                case "2":
                    buscarModelo();
            }
        } while (!opcion.equals("3"));
    }

    public void verCatalogo(){
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        ArrayList<Zapatilla> zapatillas = zapatillaDao.verTodo();
        for (Zapatilla zapatilla : zapatillas) {
            System.out.println(zapatilla.getModelo());
            System.out.println(zapatilla.getColor());
            System.out.println(zapatilla.getNumero());
            System.out.println(zapatilla.getPrecio());
            System.out.println();
        }
    }

    public void buscarModelo (){
        System.out.println("Búsqueda por modelo: ");
        String modelo = teclado.nextLine();

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);

        ArrayList<Zapatilla> zapatillas = zapatillaDao.buscarModelo(modelo);
        if (zapatillas == null) {
            System.out.println("Ese modelo no esta disponible");
            return;
        }
        for (Zapatilla zapatilla : zapatillas){
            System.out.println(zapatilla.getModelo());
            System.out.println(zapatilla.getColor());
            System.out.println(zapatilla.getNumero());
            System.out.println(zapatilla.getPrecio());
            System.out.println();
        }

        //TODO mostrar la infroacion de la zapatilla
    }
}
