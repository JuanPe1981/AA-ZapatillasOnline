package com.svalero.zapatillas;
import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuUsuario {
    private Scanner teclado;
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
            System.out.println("3. Buscar Zapatilla por Modelo, Color y Número");
            System.out.println("4. Salir");
            System.out.println("Opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    verCatalogo();
                    break;
                case "2":
                    buscarZapatilla();
                    break;
                case "3":
                    buscarZapatillaModeloColorNumero();
            }
        } while (!opcion.equals("4"));
    }

    public void verCatalogo(){
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        try {
            ArrayList<Zapatilla> zapatillas = zapatillaDao.verTodo();
            for (Zapatilla zapatilla : zapatillas) {
                System.out.println(zapatilla.getModelo());
                System.out.println(zapatilla.getColor());
                System.out.println(zapatilla.getNumero());
                System.out.println(zapatilla.getPrecio());
                System.out.println();
            }
        } catch (SQLException sqle){
            System.out.println("No se ha podido ver el catálogo de zapatillas. Intentalo de nuevo.");
        }

    }

    public void buscarZapatilla (){
        System.out.println("Búsqueda por modelo: ");
        String modelo = teclado.nextLine();

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        try {
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
        } catch (SQLException sqle) {
            System.out.println("No se ha podido encontrar la zapatilla. Intentalo de nuevo.");
        }
    }

    public void buscarZapatillaModeloColorNumero(){
        System.out.println("Buscar modelo: ");
        String modelo = teclado.nextLine();
        System.out.println(("De color: "));
        String color = teclado.nextLine();
        System.out.println("De que numero: ");
        int numero = Integer.parseInt(teclado.nextLine());

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        Zapatilla zapatilla = zapatillaDao.buscarZapatillaColorNumero(modelo, color, numero);
        if (zapatilla == null) {
            System.out.println("Esta zapatilla con ese número no esta disponible.");
            return;
        }

        System.out.println(zapatilla.getModelo());
        System.out.println(zapatilla.getColor());
        System.out.println(zapatilla.getNumero());
        System.out.println(zapatilla.getPrecio());
    }
}
