/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.vista.IVista;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;

/**
 *
 * @author mihai
 */
public class Controlador {

    private final IGenericDAO clienteDAO;
    private IVista<Cliente> vista; // la vista que implementará IVista<Cliente>

    public Controlador(IGenericDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }
    
    public void setVista(IVista<Cliente> vista){
        this.vista = vista;
    }


    public void altaCliente() {
        calcularLetraNIF();
        Cliente nuevo = vista.obtenerClienteDeFormulario();
        boolean respuesta = clienteDAO.darDeAlta(nuevo);
        if (respuesta) {
            vista.mostrarMensaje("Cliente insertado correctamente.");
        } else {
            vista.mostrarMensaje("Error al insertar el cliente.");
        }
        vista.cancelarAccion();
    }

    public void bajaCliente() {
        int codigo = vista.obtenerCodigoCliente();
        // Eliminamos
        boolean eliminado = clienteDAO.darDeBaja(codigo);
        // Mostramos mensaje
        if (eliminado) {
            vista.mostrarMensaje("Cliente eliminado con éxito.");
        } else {
            vista.mostrarMensaje("Error al eliminar el cliente.");
        }
        vista.cancelarAccion();
    }

    public void modificarCliente() {
        calcularLetraNIF();
        Cliente modificado = vista.obtenerClienteDeFormulario();
        boolean ok = clienteDAO.modificar(modificado);
        if (ok) {
            vista.mostrarMensaje("Cliente actualizado con éxito.");
        } else {
            vista.mostrarMensaje("Error al actualizar el cliente.");
        }
    }

    public void buscarCliente() {
        // 1) Obtener el código
        int codigo = vista.obtenerCodigoCliente();
        if (codigo < 0) {
            vista.mostrarMensaje("Código inválido.");
            return;
        }
        // 2) Buscar en la base
        Cliente c = (Cliente) clienteDAO.obtenerPorID(codigo);
        // 3) Si existe, mostrarlo; si no, mensaje de error
        if (c != null) {
            vista.mostrarCliente(c);
        } else {
            vista.mostrarMensaje("No se encontró un cliente con ese código.");
        }
    }

    public boolean comprobarExistenciaDelCliente() {
        int codigo = vista.obtenerCodigoCliente();
        
        if (codigo <= 0) {
            vista.mostrarMensaje("Código inválido.");
            return true;
        }
        
        // Buscamos si existe el id
        boolean existe = clienteDAO.comprobarSiExistePorCodigo(codigo);
        if(existe){
            vista.mostrarMensaje("El id existe en la base de datos.");
            Cliente cliente =  (Cliente) clienteDAO.obtenerPorID(codigo);
            vista.mostrarCliente(cliente);
            return true;
        }else{
            vista.mostrarMensaje("El id no existe en la base de datos.");
            return false;
        }
    }
    
    public void activarFormulario(boolean activar){
        vista.estadoCampos(activar);
    }
    
    public void calcularLetraNIF(){
        int nif = vista.obtenerCifrasNIFCliente();
        String cadenaLetras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = nif%23;  
        vista.establecerLetraNIF(String.valueOf(cadenaLetras.charAt(resto)));
    }
}
