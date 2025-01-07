/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.modelo.dao.interfaces;

import com.mycompany.modelo.entidades.Cliente;

/**
 *Interfaz EmpleadoDAO
 * Define los metodos para interactuar con la tabla de empleados en la base de datos
 * @author mihai
 */
public interface ClienteDAO {
    //Alta de nuevo cliente
    boolean insertarCliente(Cliente cliente);
    
    //Baja de cliente
    boolean borrarCliente(int codigoCliente);
    
    //Editar un cliente
    boolean editarCliente(Cliente cliente);
    
    //Obtener cliente por codigo
    Cliente obtenerCliente(int codigoCliente);
    
    //Comprobar si un id ya existe
    boolean comprobarCodigo(int codigoCliente);
    
}
