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
    
    public Controlador(ClienteDAO clienteDAO){
        this.clienteDAO = clienteDAO;
    }
    
    public boolean eliminarCliente(int codigoCliente){
        if(clienteDAO.comprobarCodigo(codigoCliente)){
            return clienteDAO.borrarCliente(codigoCliente);
        }else{
            return false;
        }
    }
    
    public boolean insertarCliente(Cliente cliente){
        if(clienteDAO.comprobarCodigo(cliente.getCodigo())){
            return false;
        }else{
            clienteDAO.insertarCliente(cliente);
            return true;
        }
    }
    
    public boolean actualizarCliente(Cliente cliente){
        if(clienteDAO.comprobarCodigo(cliente.getCodigo())){
            return clienteDAO.editarCliente(cliente);
        }else{
            return false;
        }
    }
    
    public Cliente obtenerCliente(int codigoCliente){
        return clienteDAO.obtenerCliente(codigoCliente);
    }

}
