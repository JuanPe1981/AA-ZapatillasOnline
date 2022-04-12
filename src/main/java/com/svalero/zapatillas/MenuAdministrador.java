package com.svalero.zapatillas;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuAdministrador {
    private Scanner teclado;
    private BaseDatos baseDatos;
    private Connection connection;

    public MenuAdministrador() {
        teclado = new Scanner(System.in);
    }

    public void conectar() {
        BaseDatos baseDatos = new BaseDatos();
        connection = baseDatos.getConnection();
    }

    public void mostrarMenu() {
        conectar();
        String opcion = null;

        do {
            System.out.println("Catálogo de Zapatillas");
            System.out.println("1. Añadir Zapatilla");
            System.out.println("2. Eliminar Zapatilla");
            System.out.println("3. Buscar Zapatilla");
            System.out.println("4. Modificar Zapatilla");
            System.out.println("5. Ver catalogo");
            System.out.println("6. Salir");
            System.out.println("Opción: ");
            opcion = teclado.nextLine();

            switch (opcion) {
                case "1":
                    anadirZapatilla();
                    break;
                case "2":
                    eliminarZapatilla();
                    break;
                case "3":
                    buscarZapatilla();
                    break;
                case "4":
                    modificarZapatilla();
                    break;
                case "5":
                    verCatalogo();
                    break;
            }
        } while (!opcion.equals("6"));
    }

    public void anadirZapatilla() {
        System.out.print("Modelo: ");
        String modelo = teclado.nextLine();
        System.out.print("Color: ");
        String color = teclado.nextLine();
        System.out.print("Número: ");
        int numero = Integer.parseInt(teclado.nextLine());
        System.out.print("Precio: ");
        float precio = Float.parseFloat(teclado.nextLine());
        Zapatilla zapatilla = new Zapatilla(modelo.trim(), color.trim(), numero, precio);
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        zapatillaDao.añadir(zapatilla);
        System.out.println("La zapatilla se añadio correctamente");

    }

    public void eliminarZapatilla() {
        System.out.println("Modelo de Zapatilla a eliminar: ");
        String modelo = teclado.nextLine();
        System.out.println("Numero de Zapatilla a Eleminar: ");
        int numero = Integer.parseInt(teclado.nextLine());

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        boolean borrado = zapatillaDao.borrar(modelo, numero);
        if (borrado) {
            System.out.println("El modelo de zapatilla borrado correctamente");
        } else
            System.out.println("No se pudo borrar la zapatilla. No existe");

    }

    public void buscarZapatilla() {
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
    }

    public void modificarZapatilla() {
        System.out.println("Modelo de zapatilla a modificar el precio: ");
        String modelo = teclado.nextLine();
        System.out.println("Numero de zapatilla a modificar: ");
        int numero = Integer.parseInt(teclado.nextLine());
        //TODO Buscar libro antes de pedir datos
        System.out.println("Nuevo modelo: ");
        String nuevoModelo = teclado.nextLine();
        System.out.println("Nuevo color: ");
        String nuevoColor = teclado.nextLine();
        System.out.println("Nuevo numero: ");
        int nuevoNumero = Integer.parseInt(teclado.nextLine());
        System.out.println("Nuevo Precio: ");
        float nuevoPrecio = Float.parseFloat(teclado.nextLine());
        Zapatilla nuevaZapatilla = new Zapatilla(nuevoModelo.trim(), nuevoColor.trim(), nuevoNumero, nuevoPrecio);

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        boolean modificado = zapatillaDao.modificar(modelo, numero, nuevaZapatilla);
        if (modificado)
            System.out.println("La Zapatilla se modifico correctamente");
        else
            System.out.println("La zapatilla no se pudo modificar. No existe");
    }

    public void verCatalogo() {
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        //TODO propagar la excepción al menu de usuario
        ArrayList<Zapatilla> zapatillas = zapatillaDao.verTodo();
        for (Zapatilla zapatilla : zapatillas) {
            System.out.println(zapatilla.getModelo());
            System.out.println(zapatilla.getColor());
            System.out.println(zapatilla.getNumero());
            System.out.println(zapatilla.getPrecio());
            System.out.println();
        }
    }
}
