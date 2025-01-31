/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao.clases;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Articulo;
import com.mycompany.utils.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mihai
 */
public class ArticuloDAOImpl implements IGenericDAO<Articulo, Integer>{
     @Override
    public boolean darDeAlta(Articulo articulo) {
        String sql = "INSERT INTO Articulos (codigo, descripcion, stock, stock_minimo, precio_compra, precio_venta) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, articulo.getCodigo());
            statement.setString(2, articulo.getDescripcion());
            statement.setFloat(3, articulo.getStock());
            statement.setFloat(4, articulo.getStockMinimo());
            statement.setFloat(5, articulo.getPrecioCompra());
            statement.setFloat(6, articulo.getPrecioVenta());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos al dar de alta artículo -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean darDeBaja(Integer codigo) {
        String sql = "DELETE FROM Articulos WHERE codigo = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos al dar de baja artículo -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Articulo articulo) {
        String sql = "UPDATE Articulos SET descripcion = ?, stock = ?, stock_minimo = ?, precio_compra = ?, precio_venta = ? "
                   + "WHERE codigo = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, articulo.getDescripcion());
            statement.setFloat(2, articulo.getStock());
            statement.setFloat(3, articulo.getStockMinimo());
            statement.setFloat(4, articulo.getPrecioCompra());
            statement.setFloat(5, articulo.getPrecioVenta());
            statement.setInt(6, articulo.getCodigo());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos al modificar artículo -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public Articulo obtenerPorID(Integer codigo) {
        String sql = "SELECT * FROM Articulos WHERE codigo = ?";
        Articulo articulo = null;

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                articulo = new Articulo();
                articulo.setCodigo(rs.getInt("codigo"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setStock(rs.getFloat("stock"));
                articulo.setStockMinimo(rs.getFloat("stock_minimo"));
                articulo.setPrecioCompra(rs.getFloat("precio_compra"));
                articulo.setPrecioVenta(rs.getFloat("precio_venta"));
            }

        } catch (SQLException e) {
            System.err.println("Error en la base de datos al obtener artículo -> " + e.getMessage());
        }
        return articulo;
    }

    @Override
    public boolean comprobarSiExistePorCodigo(Integer codigo) {
        String sql = "SELECT COUNT(*) FROM Articulos WHERE codigo = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, codigo);
            ResultSet rs = statement.executeQuery();

            return rs.next() && rs.getInt(1) > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos al comprobar existencia de artículo -> " + e.getMessage());
            return false;
        }
    }
}
