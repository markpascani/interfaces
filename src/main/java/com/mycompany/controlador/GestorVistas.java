/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.controlador.ControladorCliente;
import com.mycompany.controlador.ControladorProveedor;
import com.mycompany.modelo.dao.clases.ArticuloDAOImpl;
import com.mycompany.modelo.dao.clases.ClienteDAOImpl;
import com.mycompany.modelo.dao.clases.PedidoDAO;
import com.mycompany.modelo.dao.clases.ProveedorDAOImpl;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Articulo;
import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.modelo.entidades.Pedido;
import com.mycompany.modelo.entidades.Proveedor;
import com.mycompany.utils.JDBC;
import com.mycompany.vista.ArticulosVista;
import com.mycompany.vista.ClienteVista;
import com.mycompany.vista.PedidosVista;
import com.mycompany.vista.ProveedorVista;
import com.mycompany.vista.interfaces.IVista;
import com.mycompany.vista.interfaces.PedidoListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mihai
 */
public class GestorVistas {

    public static void mostrarClientes() {
        Connection connection = null;
        try {
            connection = JDBC.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        IGenericDAO<Cliente, Integer> clienteDAO = new ClienteDAOImpl(connection);

        // Ahora creamos el controlador y le pasamos la vista correcta
        ControladorCliente controlador = new ControladorCliente(clienteDAO, null);
        // Luego crear la vista y asignar el controlador
        ClienteVista clientes = new ClienteVista(controlador);

        // Finalmente, actualizar la vista en el controlador
        controlador.setVista(clientes);

        clientes.setVisible(true);
    }

    public static void mostrarProveedores() {
        Connection connection = null;
        try {
            connection = JDBC.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        IGenericDAO<Proveedor, Integer> proveedorDAO = new ProveedorDAOImpl(connection);
        ControladorProveedor controlador = new ControladorProveedor(proveedorDAO, null);
        ProveedorVista proveedorVista = new ProveedorVista(controlador);
        controlador.setVista(proveedorVista);
        proveedorVista.setVisible(true);
    }

    public static void mostrarArticulos() {
        Connection connection = null;
        try {
            connection = JDBC.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        IGenericDAO<Articulo, Integer> articuloDAO = new ArticuloDAOImpl(connection);
        ControladorArticulos controlador = new ControladorArticulos(articuloDAO, null);
        ArticulosVista articulosVista = new ArticulosVista(controlador);
        controlador.setVista(articulosVista);
        articulosVista.setVisible(true);
    }

    public static void mostrarPedidos() {
        Connection connection = null;
        try {
            connection = JDBC.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(GestorVistas.class.getName()).log(Level.SEVERE, null, ex);
        }
        PedidosVista vistaPedidos = new PedidosVista();
        vistaPedidos.setVisible(true);
        IGenericDAO<Pedido, Integer> pedidoDAO = new PedidoDAO(connection);
        IGenericDAO<Cliente, Integer> clienteDAO = new ClienteDAOImpl(connection);
        IGenericDAO<Articulo, Integer> articuloDAO = new ArticuloDAOImpl(connection);
        IGenericDAO<Proveedor, Integer> proveedorDAO = new ProveedorDAOImpl(connection);

            PedidoControlador controlador = new PedidoControlador(vistaPedidos, pedidoDAO, clienteDAO, proveedorDAO, articuloDAO, connection);


    }

}
