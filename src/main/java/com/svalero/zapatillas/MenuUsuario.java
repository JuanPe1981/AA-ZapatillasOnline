package com.svalero.zapatillas;
import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;

import java.sql.Connection;
import java.util.ArrayList;
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
        }
    }

    public void buscarModelo (){
        boolean encontrado = false;
        System.out.println("Búsqueda por modelo: ");
        String modelo = teclado.nextLine();
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        zapatillaDao.buscarModelo(modelo);
        //TODO mostrar la infroacion de la zapatilla
    }
}
