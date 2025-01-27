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
public interface IGenericDAO<T, ID> {
    //Alta
    boolean darDeAlta(T entidad);
    
    //Baja
    boolean darDeBaja(ID codigo);
    
    //Editar
    boolean modificar(T entidad);
    
    //Obtener por codigo
    T obtenerPorID(ID codigo);
    
    //Comprobar si un id ya existe
    boolean comprobarSiExistePorCodigo(ID codigo);
    
}
