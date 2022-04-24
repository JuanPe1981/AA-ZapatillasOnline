package com.svalero.zapatillas;

import com.svalero.zapatillas.dao.BaseDatos;
import com.svalero.zapatillas.dao.PedidoDao;
import com.svalero.zapatillas.dao.UsuarioDao;
import com.svalero.zapatillas.dao.ZapatillaDao;
import com.svalero.zapatillas.domain.Pedido;
import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.exception.UsuarioNoFuncionaException;
import com.svalero.zapatillas.exception.ZapatillaNoExisteException;
import com.svalero.zapatillas.exception.ZapatillaYaExisteException;
import com.svalero.zapatillas.util.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuAdministrador {
    private Scanner teclado;
    private BaseDatos baseDatos;
    private Connection connection;
    private Usuario currentUser;

    public MenuAdministrador() {
        teclado = new Scanner(System.in);
    }

    public void conectar() {
        baseDatos = new BaseDatos();
        connection = baseDatos.getConnection();
    }

    public void mostrarMenu() {
        conectar();
        login();

        String opcion = null;

        do {
            System.out.println("Catálogo de Zapatillas (Usuario actual: " + currentUser.getNombre() + ")");
            System.out.println("1. Añadir Zapatilla");
            System.out.println("2. Eliminar Zapatilla");
            System.out.println("3. Buscar Zapatilla");
            System.out.println("4. Modificar Zapatilla");
            System.out.println("5. Ver catalogo");
            System.out.println("Gestiones de usuarios");
            System.out.println("6. Registrar un nuevo usuario");
            System.out.println("Gestión de pedidos");
            System.out.println("7. Registrar pedido");
            System.out.println("8. Ver detalles de un pedido");
            System.out.println("9. Marcar un pedido como pagado");
            System.out.println(("10. Ver pedidos entre fechas"));
            System.out.println("S. Salir");
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
                case "6":
                    registrarUsuario();
                    break;
                case "7":
                    addPedido();
                    break;
                case "8":
                    verDetallesPedido();
                    break;
                case "9":
                    pedidoPagado();
                    break;
                case "10":
                    pedidosEntreFechas();
                    break;
            }
        } while (!opcion.equals("S"));
    }

    private void login() {
        System.out.println("¿Cual es tu usuario?");
        String nombreUsuario = teclado.nextLine();
        System.out.println("¿Cual es tu contraseña?");
        String password = teclado.nextLine();

        UsuarioDao usuarioDao = new UsuarioDao(connection);
        try {
            currentUser = usuarioDao.getUsuario(nombreUsuario, password)
                    .orElseThrow(UsuarioNoFuncionaException::new);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido comunicar con la base de datos. Inténtelo de nuevo. Ahora");
            System.exit(0);
        } catch (UsuarioNoFuncionaException unfe) {
            System.out.println(unfe.getMessage());
            MenuPrincipal menuPrincipal = new MenuPrincipal();
            menuPrincipal.mostrarMenu();
        }
    }

    public void anadirZapatilla() {
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);

        System.out.print("Modelo: ");
        String modelo = teclado.nextLine();
        System.out.print("Color: ");
        String color = teclado.nextLine();
        System.out.print("Número: ");
        int numero = Integer.parseInt(teclado.nextLine());
        System.out.print("Precio: ");
        float precio = Float.parseFloat(teclado.nextLine());
        Zapatilla zapatilla = new Zapatilla(modelo.trim(), color.trim(), numero, precio);
        try {
            zapatillaDao.añadir(zapatilla);
            System.out.println("La zapatilla se añadio correctamente");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido añadir la zapatilla. Intentalo de nuevo.");
        } catch (ZapatillaYaExisteException zyee){
            System.out.println(zyee.getMessage());
        }
    }

    public void eliminarZapatilla() {
        System.out.println("Modelo de Zapatilla a eliminar: ");
        String modelo = teclado.nextLine();
        System.out.println("Numero de Zapatilla a Eleminar: ");
        int numero = Integer.parseInt(teclado.nextLine());

        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        try {
            boolean borrado = zapatillaDao.borrar(modelo, numero);
            if (borrado) {
                System.out.println("El modelo de zapatilla borrado correctamente");
            } else
                System.out.println("No se pudo borrar la zapatilla. No existe");
        }catch (SQLException sqle){
            System.out.println("No se ha podido eliminar la zapatilla. Intentalo de nuevo.");
        } catch (ZapatillaNoExisteException znee) {
            System.out.println(znee.getMessage());
        }
    }

    public void buscarZapatilla() {
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
                System.out.println(zapatilla.getIdZapatilla());
                System.out.println(zapatilla.getModelo());
                System.out.println(zapatilla.getColor());
                System.out.println(zapatilla.getNumero());
                System.out.println(zapatilla.getPrecio());
                System.out.println();
            }
        }catch (SQLException sqle) {
            System.out.println("No se ha podido encontrar la zapatilla. Intentalo de nuevo.");
        } catch (ZapatillaNoExisteException znee) {
            System.out.println("El modelo de zapatilla no está disponible");
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
        try {
            boolean modificado = zapatillaDao.modificar(modelo, numero, nuevaZapatilla);
            if (modificado)
                System.out.println("La Zapatilla se modifico correctamente");
            else
                System.out.println("La zapatilla no se pudo modificar. No existe");
        }catch (SQLException sqle) {
            System.out.println("No se ha podido modificar la zapatilla. Intentalo de nuevo.");
        }catch (ZapatillaNoExisteException znee) {
            System.out.println(znee.getMessage());
        }

    }

    public void verCatalogo() {
        ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
        try {
            ArrayList<Zapatilla> zapatillas = zapatillaDao.verTodo();
            for (Zapatilla zapatilla : zapatillas) {
                System.out.println(zapatilla.getIdZapatilla());
                System.out.println(zapatilla.getModelo());
                System.out.println(zapatilla.getColor());
                System.out.println(zapatilla.getNumero());
                System.out.println(zapatilla.getPrecio());
                System.out.println();
            }
        }catch (SQLException sqle){
            System.out.println("No se ha podido ver el catálogo de zapatilla. Intentalo de nuevo.");
        }
    }

    private void registrarUsuario() {
        UsuarioDao usuarioDao = new UsuarioDao(connection);

    }

    private void addPedido() {

        System.out.println("Que zapatilla quieres comprar?");
        String pedidoZapatillas = teclado.nextLine();

        try {
            String[] zapatillaArray = pedidoZapatillas.split(",");
            ZapatillaDao zapatillaDao = new ZapatillaDao(connection);
            List<Zapatilla> zapatillas = new ArrayList<>();
            for (String zapatillaModelo: zapatillaArray) {
                Zapatilla zapatilla = zapatillaDao.buscarZapatillaModelo(zapatillaModelo.trim()).get();
                zapatillas.add(zapatilla);
            }

            PedidoDao pedidodao = new PedidoDao(connection);
            pedidodao.addPedido(currentUser, zapatillas);
            System.out.println("El pedido se ha creado correctamente");
        } catch (SQLException sqle) {
            System.out.println("No se ha podido hacer el pedido. Intentalo de nuevo.");
            sqle.printStackTrace();
        }
    }

    private void verDetallesPedido() {
    }

    private void pedidoPagado() {
    }

    private void pedidosEntreFechas() {
        System.out.println("Desde: (dd.MM.yyyy)");
        String desdeFechaString = teclado.nextLine();
        System.out.println("Hasta: (dd.MM.yyyy)");
        String hastaFechaString = teclado.nextLine();

        LocalDate desdeFecha = DateUtils.parseLocalDate(desdeFechaString);
        LocalDate hastaFecha = DateUtils.parseLocalDate(hastaFechaString);

        PedidoDao pedidoDao = new PedidoDao(connection);
        try {
            List<Pedido> pedidos = pedidoDao.getPedidosEntreFechas(desdeFecha, hastaFecha);
            pedidos.forEach(System.out::println);
        } catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con la base de datos. Intentelo de nuevo");
            sqle.printStackTrace();
        }
    }

}
