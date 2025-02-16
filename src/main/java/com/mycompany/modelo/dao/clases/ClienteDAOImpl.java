/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao.clases;

import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.utils.JDBC;
import java.io.PipedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;

/**
 * Implementa la logica de acceso a la base de datos para la tabla cliente
 *
 * @author mihai
 */
public class ClienteDAOImpl implements IGenericDAO<Cliente, Integer> {

    private final Connection connection;

    public ClienteDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean darDeAlta(Cliente cliente) {
        String sql = "INSERT INTO Clientes "
                + "(codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_venta) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, cliente.getCodigo());
            statement.setString(2, cliente.getNif());
            statement.setString(3, cliente.getApellidos());
            statement.setString(4, cliente.getNombre());
            statement.setString(5, cliente.getDomicilio());
            statement.setString(6, cliente.getCodigoPostal());
            statement.setString(7, cliente.getLocalidad());
            statement.setString(8, cliente.getTelefono());
            statement.setString(9, cliente.getMovil());
            statement.setString(10, cliente.getFax());
            statement.setString(11, cliente.getEmail());
            statement.setFloat(12, 0);

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos.-> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean darDeBaja(Integer codigoCliente) {
        String sql = "DELETE FROM Clientes WHERE codigo = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoCliente);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error en la base de datos.-> " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean modificar(Cliente cliente) {
        String sql = "UPDATE Clientes SET "
                + "nombre = ?, "
                + "apellidos = ?, "
                + "domicilio = ?, "
                + "codigo_postal = ?, "
                + "localidad = ?, "
                + "telefono = ?, "
                + "fax = ?,"
                + "movil = ?,"
                + "email = ?,"
                + "total_venta = ?"
                + "WHERE codigo = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getDomicilio());
            statement.setString(4, cliente.getCodigoPostal());
            statement.setString(5, cliente.getLocalidad());
            statement.setString(6, cliente.getTelefono());
            statement.setString(7, cliente.getFax());
            statement.setString(8, cliente.getMovil());
            statement.setString(9, cliente.getEmail());
            statement.setFloat(10, cliente.getTotalVentas());
            statement.setInt(11, cliente.getCodigo());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error en la base de datos.-> " + e.getMessage());
            return false;
        }
    }

    @Override
    public Cliente obtenerPorID(Integer codigoCliente) {
        Cliente cliente = new Cliente();
        String sql = "SELECT codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_venta"
                + " FROM Clientes WHERE "
                + "codigo = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoCliente);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNif(rs.getString("nif"));
                cliente.setApellidos(rs.getString("apellidos"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDomicilio(rs.getString("domicilio"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));
                cliente.setLocalidad(rs.getString("localidad"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setMovil(rs.getString("movil"));
                cliente.setFax(rs.getString("fax"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTotalVentas(rs.getFloat("total_venta"));

                return cliente;
            }
        } catch (SQLException e) {
            System.err.println("Error en la base de datos.-> " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean comprobarSiExistePorCodigo(Integer codigoCliente) {
        String sql = "SELECT COUNT(codigo) FROM Clientes WHERE codigo = ?";
        try (
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, codigoCliente);
            ResultSet rs = statement.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error en la base de datos.-> " + e.getMessage());
            return false;
        }
    }

}
