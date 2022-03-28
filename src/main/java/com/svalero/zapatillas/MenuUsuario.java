package com.svalero.zapatillas;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Zapatilla;
import java.util.Scanner;

public class MenuUsuario {
    private Scanner teclado;

    public MenuUsuario() {
        teclado = new Scanner(System.in);
    }

    public void mostrarMenu(){
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
                    buscarZapatilla();
            }
        } while (!opcion.equals("3"));
    }

    public void verCatalogo(){
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.verTodo();
    }

    public void buscarZapatilla(){
        boolean encontrado = false;
        System.out.println("Búsqueda por modelo: ");
        String modelo = teclado.nextLine();
        ZapatillaDao zapatillaDao = new ZapatillaDao();
        zapatillaDao.buscarModelo(modelo);
        //TODO mostrar la infroacion de la zapatilla
    }
}
