package com.svalero.zapatillas.dao;

import com.svalero.zapatillas.domain.Pedido;
import com.svalero.zapatillas.domain.Usuario;
import com.svalero.zapatillas.domain.Zapatilla;
import com.svalero.zapatillas.util.DateUtils;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PedidoDao {

    private Connection connection;

    public PedidoDao (Connection connection) {
        this.connection = connection;
    }

    public void addPedido(Usuario usuario, List<Zapatilla> zapatillas) throws SQLException {
        String pedidoSql = "INSERT INTO PEDIDOS (CODE, IDUSUARIO, FECHAPEDIDO) VALUES ( ?, ?, ?)";

        connection.setAutoCommit(false);

        PreparedStatement pedidoStatement = connection.prepareStatement(pedidoSql);
        pedidoStatement.setString(1, UUID.randomUUID().toString());
        pedidoStatement.setInt(2, usuario.getIdUsuario());
        pedidoStatement.setDate(3, new Date(System.currentTimeMillis()));
        pedidoStatement.executeUpdate();

        Pedido pedido = ultimoPedido();
        int ultimoNumeroPedido = (int) pedido.getIdPedido();

        for (Zapatilla zapatilla : zapatillas) {
            String zapatillaSql = "INSERT INTO PEDIDO_ZAPATILLA (IDPEDIDO, IDZAPATILLA) VALUES (?, ?)";

            PreparedStatement zapatillaStatement = connection.prepareStatement(zapatillaSql);
            zapatillaStatement.setInt(1, ultimoNumeroPedido);
            zapatillaStatement.setInt(2, zapatilla.getIdZapatilla());
            zapatillaStatement.executeUpdate();
        }

        connection.commit();
        connection.setAutoCommit(true);
    }

    private Pedido ultimoPedido () {
        String ultimoPedidoSql = "SELECT * from (select * from PEDIDOS order by IDPEDIDO desc) where rownum = 1";

        Pedido pedido = null;

        try {
            PreparedStatement statement = connection.prepareStatement(ultimoPedidoSql);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                pedido = new Pedido();
                pedido.setIdPedido(resultSet.getInt("idpedido"));
                pedido.setCode(resultSet.getString("code"));
                pedido.setFechaPedido(DateUtils.toLocalDateFromSql(resultSet.getDate("fechapedido")));
                pedido.setPagado(resultSet.getBoolean("pagado"));
            }
        }catch (SQLException sqle) {
            System.out.println("No se ha podido conectar con el servidor de base de datos. Comprueba que los datos son correctos y que el servidor se ha iniciado");
            sqle.printStackTrace();
        }

        return pedido;
    }

    public Pedido getPedido() {
        return null;
    }

    public void pagoPedido() {

    }

    public List<Pedido> getPedidosEntreFechas(LocalDate desdeFecha, LocalDate hastaFecha) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE fechapedido BETWEEN ? AND ?";
        List<Pedido> pedidos = new ArrayList<>();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDate(1, DateUtils.toSqlDate(desdeFecha));
        statement.setDate(2, DateUtils.toSqlDate(hastaFecha));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt(1));
            pedido.setCode(resultSet.getString(5));
            pedido.setFechaPedido(DateUtils.toLocalDateFromSql(resultSet.getDate(4)));
            pedido.setPagado(resultSet.getBoolean(2));
            pedidos.add(pedido);
        }

        return pedidos;
    }
}
