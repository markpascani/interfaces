/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.interfaces.ClienteDAO;
import com.mycompany.modelo.entidades.Cliente;

/**
 *
 * @author mihai
 */
public class Controlador {

    private final ClienteDAO clienteDAO;

    public Controlador(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean eliminarCliente(int codigoCliente) {

        return clienteDAO.borrarCliente(codigoCliente);

    }

    public boolean insertarCliente(Cliente cliente) {

        clienteDAO.insertarCliente(cliente);
        return true;

    }

    public boolean actualizarCliente(Cliente cliente) {

        return clienteDAO.editarCliente(cliente);

    }

    public Cliente obtenerCliente(int codigoCliente) {
        return clienteDAO.obtenerCliente(codigoCliente);
    }
    
    public boolean comprobarExistenciaCliente(int codigoCliente){
        return clienteDAO.comprobarCodigo(codigoCliente);
    }
}
