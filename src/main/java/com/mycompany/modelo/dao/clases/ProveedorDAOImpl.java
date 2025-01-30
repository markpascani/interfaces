/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao.clases;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.utils.JDBC;
import com.mycompany.modelo.entidades.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mihai
 */
public class ProveedorDAOImpl implements IGenericDAO<Proveedor, Integer> {

    @Override
    public boolean darDeAlta(Proveedor proveedor) {
        String sql = "INSERT INTO Proveedores "
                + "(codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_compras) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, proveedor.getCodigo());
            statement.setString(2, proveedor.getNif());
            statement.setString(3, proveedor.getApellidos());
            statement.setString(4, proveedor.getNombre());
            statement.setString(5, proveedor.getDomicilio());
            statement.setString(6, proveedor.getCodigoPostal());
            statement.setString(7, proveedor.getLocalidad());
            statement.setString(8, proveedor.getTelefono());
            statement.setString(9, proveedor.getMovil());
            statement.setString(10, proveedor.getFax());
            statement.setString(11, proveedor.getEmail());
            statement.setFloat(12, 0); // 🔹 total_compras inicializado en 0

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean darDeBaja(Integer codigoProveedor) {
        String sql = "DELETE FROM Proveedores WHERE codigo = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoProveedor);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en la base de datos -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Proveedor proveedor) {
        String sql = "UPDATE Proveedores SET "
                + "nombre = ?, apellidos = ?, domicilio = ?, codigo_postal = ?, localidad = ?, "
                + "telefono = ?, fax = ?, movil = ?, email = ? WHERE codigo = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getApellidos());
            statement.setString(3, proveedor.getDomicilio());
            statement.setString(4, proveedor.getCodigoPostal());
            statement.setString(5, proveedor.getLocalidad());
            statement.setString(6, proveedor.getTelefono());
            statement.setString(7, proveedor.getFax());
            statement.setString(8, proveedor.getMovil());
            statement.setString(9, proveedor.getEmail());
            statement.setInt(10, proveedor.getCodigo());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en la base de datos -> " + e.getMessage());
            return false;
        }
    }

    @Override
    public Proveedor obtenerPorID(Integer codigoProveedor) {
        Proveedor proveedor = new Proveedor();
        String sql = "SELECT * FROM Proveedores WHERE codigo = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoProveedor);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                proveedor.setCodigo(rs.getInt("codigo"));
                proveedor.setNif(rs.getString("nif"));
                proveedor.setApellidos(rs.getString("apellidos"));
                proveedor.setNombre(rs.getString("nombre"));
                proveedor.setDomicilio(rs.getString("domicilio"));
                proveedor.setCodigoPostal(rs.getString("codigo_postal"));
                proveedor.setLocalidad(rs.getString("localidad"));
                proveedor.setTelefono(rs.getString("telefono"));
                proveedor.setMovil(rs.getString("movil"));
                proveedor.setFax(rs.getString("fax"));
                proveedor.setEmail(rs.getString("email"));
                proveedor.setTotalCompras(rs.getFloat("total_compras"));

                return proveedor;
            }
        } catch (SQLException e) {
            System.err.println("Error en la base de datos -> " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean comprobarSiExistePorCodigo(Integer codigoProveedor) {
        String sql = "SELECT COUNT(codigo) FROM Proveedores WHERE codigo = ?";
        try (Connection connection = JDBC.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoProveedor);
            ResultSet rs = statement.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (Exception e) {
            System.err.println("Error en la base de datos -> " + e.getMessage());
            return false;
        }
    }
}
