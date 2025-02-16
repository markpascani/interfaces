/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao.clases;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Pedido;
import com.mycompany.utils.JDBC;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mihai
 */
public class PedidoDAO implements IGenericDAO<Pedido, Integer> {

    private final Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    // INSERT: ahora incluye numero_factura (se puede insertar como NULL)
    private static final String INSERT_SQL
            = "INSERT INTO pedidos_historico (codigo_cliente, codigo_proveedor, codigo_articulo, unidades, fecha_pedido, numero_factura) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String DELETE_SQL
            = "DELETE FROM pedidos_historico WHERE id_pedido = ?";

    // UPDATE: ahora permite modificar numero_factura
    private static final String UPDATE_SQL
            = "UPDATE pedidos_historico "
            + "SET codigo_cliente = ?, codigo_proveedor = ?, codigo_articulo = ?, unidades = ?, fecha_pedido = ?, numero_factura = ? "
            + "WHERE id_pedido = ?";

    private static final String SELECT_BY_ID_SQL
            = "SELECT * FROM pedidos_historico WHERE id_pedido = ?";

    private static final String EXISTS_SQL
            = "SELECT 1 FROM pedidos_historico WHERE id_pedido = ?";

    // Nuevo UPDATE para asignar un número de factura a los pedidos sin facturar
    private static final String UPDATE_FACTURA_SQL
            = "UPDATE pedidos_historico SET numero_factura = ? WHERE codigo_cliente = ? AND (numero_factura IS NULL OR numero_factura = 0)";

    @Override
    public boolean darDeAlta(Pedido pedido) {
        try (PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL)) {

            // codigo_cliente o null
            if (pedido.getCodigoCliente() != null) {
                pstmt.setInt(1, pedido.getCodigoCliente());
            } else {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            }

            // codigo_proveedor o null
            if (pedido.getCodigoProveedor() != null) {
                pstmt.setInt(2, pedido.getCodigoProveedor());
            } else {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            }

            pstmt.setInt(3, pedido.getCodigoArticulo());
            pstmt.setInt(4, pedido.getUnidades());
            pstmt.setDate(5, Date.valueOf(pedido.getFechaPedido()));

            // 📌 GUARDAR EL NÚMERO DE FACTURA SI EXISTE
            if (pedido.getNumeroFactura() != null) {
                pstmt.setInt(6, pedido.getNumeroFactura());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean darDeBaja(Integer id) {
        try (PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Pedido pedido) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL)) {
            // codigo_cliente o null
            if (pedido.getCodigoCliente() != null) {
                pstmt.setInt(1, pedido.getCodigoCliente());
            } else {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            }

            // codigo_proveedor o null
            if (pedido.getCodigoProveedor() != null) {
                pstmt.setInt(2, pedido.getCodigoProveedor());
            } else {
                pstmt.setNull(2, java.sql.Types.INTEGER);
            }

            pstmt.setInt(3, pedido.getCodigoArticulo());
            pstmt.setInt(4, pedido.getUnidades());
            pstmt.setDate(5, Date.valueOf(pedido.getFechaPedido()));

            // Actualizar numero_factura si existe
            if (pedido.getNumeroFactura() != null) {
                pstmt.setInt(6, pedido.getNumeroFactura());
            } else {
                pstmt.setNull(6, java.sql.Types.INTEGER);
            }

            pstmt.setInt(7, pedido.getIdPedido());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pedido obtenerPorID(Integer id) {
        try (PreparedStatement pstmt = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Pedido p = new Pedido();
                    p.setIdPedido(rs.getInt("id_pedido"));

                    int cCliente = rs.getInt("codigo_cliente");
                    if (!rs.wasNull()) {
                        p.setCodigoCliente(cCliente);
                    }
                    int cProveedor = rs.getInt("codigo_proveedor");
                    if (!rs.wasNull()) {
                        p.setCodigoProveedor(cProveedor);
                    }

                    p.setCodigoArticulo(rs.getInt("codigo_articulo"));
                    p.setUnidades(rs.getInt("unidades"));
                    p.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());

                    // Obtener numero_factura si existe
                    int numFactura = rs.getInt("numero_factura");
                    if (!rs.wasNull()) {
                        p.setNumeroFactura(numFactura);
                    }

                    return p;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean comprobarSiExistePorCodigo(Integer id) {
        try (PreparedStatement pstmt = connection.prepareStatement(EXISTS_SQL)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Si hay fila, existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Asigna un número de factura a todos los pedidos de un cliente sin
     * facturar.
     *
     * @param codigoCliente ID del cliente
     * @param numeroFactura Número de factura a asignar
     * @return true si se actualizó al menos un pedido, false en caso contrario.
     */
    public boolean asignarNumeroFactura(int codigoCliente, int numeroFactura) {
        try (PreparedStatement pstmt = connection.prepareStatement(UPDATE_FACTURA_SQL)) {
            pstmt.setInt(1, numeroFactura);
            pstmt.setInt(2, codigoCliente);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
