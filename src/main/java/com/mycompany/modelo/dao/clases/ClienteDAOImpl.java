/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.modelo.dao.clases;

import com.mycompany.modelo.dao.interfaces.ClienteDAO;
import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.utils.JDBC;
import java.io.PipedInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *  Implementa la logica de acceso a la base de datos para la tabla cliente
 * @author mihai
 */
public class ClienteDAOImpl implements ClienteDAO{

    @Override
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO Cliente "
                + "(codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_venta) "
                + "values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try(Connection connection = JDBC.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            
            statement.setInt(1, cliente.getCodigo());
            statement.setString(2, cliente.getNif());
            statement.setString(3, cliente.getApellidos());
            statement.setString(4, cliente.getNombre());
            statement.setString(6, cliente.getDomicilio());
            statement.setString(7, cliente.getCodigoPostal());
            statement.setString(8, cliente.getLocalidad());
            statement.setString(9, cliente.getTelefono());
            statement.setString(9, cliente.getMovil());
            statement.setString(10, cliente.getFax());
            statement.setString(11, cliente.getEmail());
            statement.setFloat(12, 0);
            
            return statement.executeUpdate() > 0;
    
        }catch(SQLException e){
            System.err.println("Error en la base de datos.-> "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean borrarCliente(int codigoCliente) {
        String sql = "DELETE FROM Cliente WHERE id = ?";
        try(Connection connection = JDBC.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, codigoCliente);
            return statement.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error en la base de datos.-> "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean editarCliente(Cliente cliente) {
        String sql = "UPDATE Clientes SET "
                + "nombre = ?, "
                + "apellido = ?, "
                + "domicilio = ?, "
                + "codigo_postal = ?, "
                + "localidad = ?, "
                + "telefono = ?, "
                + "fax = ?, "
                + "email = ?";
        
        try(Connection connection = JDBC.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cliente.getApellidos());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getDomicilio());
            statement.setString(4, cliente.getLocalidad());
            statement.setString(5, cliente.getCodigoPostal());
            statement.setString(6, cliente.getTelefono());
            statement.setString(7, cliente.getFax());
            statement.setString(8, cliente.getEmail());
            
            return statement.executeUpdate() > 0;
            
        }catch(SQLException e){
            System.err.println("Error en la base de datos.-> "+e.getMessage());
            return false;
        }
    }
    

    @Override
    public Cliente obtenerCliente(int codigoCliente) {
        Cliente cliente = null;
        String sql = "SELECT codigo, nif, apellidos, nombre, domicilio, codigo_postal, localidad, telefono, movil, fax, email, total_venta"
                + " FROM Clientes WHERE "
                + "codigo = ?";
        
        try(Connection connection = JDBC.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, codigoCliente);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
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
                cliente.setTotalVentas(rs.getFloat("total_ventas"));
            }
        }catch(SQLException e){
            System.err.println("Error en la base de datos.-> "+e.getMessage());
        }
        return cliente;
    }

    @Override
    public boolean comprobarCodigo(int codigoCliente) {
        String sql = "SELECT COUNT(codigo) FROM Clientes WHERE codigo = ?";
        try(Connection connection = JDBC.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, codigoCliente);
            ResultSet rs = statement.executeQuery();
            
            if(rs.next() && rs.getInt(1) > 0) return true;
            else return false;
        }catch(Exception e){
            System.err.println("Error en la base de datos.-> "+e.getMessage());
            return false;
        }
    }
    
  
    
}
