package com.svalero.zapatillas;
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
        //TODO Ver en la base de datos
        for (Zapatilla zapatilla : catalogoZapatillas) {
            System.out.println("Modelo: " + zapatilla.getNombre());
            System.out.println("Color:  " + zapatilla.getColor());
            System.out.println("Número: " + zapatilla.getNumero());
            System.out.println("Precio: " + zapatilla.getPrecio());
            System.out.println();
        }
    }

    public void buscarZapatilla(){
        boolean encontrado = false;
        System.out.println("Búsqueda por nombre: ");
        String nombre = teclado.nextLine();
        //TODO Buscar en la base de datos
        for (Zapatilla zapatilla : catalogoZapatillas) {
            if (zapatilla.getNombre().contains(nombre)) {
                System.out.println("Zapatilla encontrada");
                System.out.println(zapatilla.getNombre());
                System.out.println(zapatilla.getColor());
                System.out.println(zapatilla.getNumero());
                System.out.println(zapatilla.getPrecio());
                System.out.println();
                encontrado = true;
            }
        }
        if (!encontrado)
            System.out.println("No se encuentra ese modelo de zapatilla.");
        System.out.println();
    }

}
