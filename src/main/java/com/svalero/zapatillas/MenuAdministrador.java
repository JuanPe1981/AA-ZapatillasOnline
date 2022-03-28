package com.svalero.zapatillas;

import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAdministrador {
    private Scanner teclado;

    public MenuAdministrador() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu() {
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
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.añadir(zapatilla);
        System.out.println("La zapatilla se añadio correctamente");

    }

    public void eliminarZapatilla() {
        System.out.println("Modelo de Zapatilla a eliminar: ");
        String modelo = teclado.nextLine();
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.borrar(modelo);
    }

    public void buscarZapatilla() {
        boolean encontrado = false;
        System.out.println("Búsqueda por modelo: ");
        String modelo = teclado.nextLine();
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.buscarModelo(modelo);
        //TODO buscar de la base de datos - Zapatillas

        if (!encontrado)
            System.out.println("No se encuentra ese modelo de zapatilla.");
        System.out.println();
    }

    public void modificarZapatilla() {
        boolean modificado = false;
        System.out.println("Modelo de zapatilla a modificar el precio: ");
        String modelo = teclado.nextLine();
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.modificar(modelo)
        //TODO Modificar de la base de datos - zapatillas

        if (!modificado)
            System.out.println("No se pudo modificar la zapatilla.");
    }

    public void verCatalogo() {
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.verTodo();
    }
}
