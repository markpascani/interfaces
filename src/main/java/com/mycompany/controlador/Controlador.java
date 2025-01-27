/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.interfaces.ClienteDAO;
import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.vista.IVista;

/**
 *
 * @author mihai
 */
public class Controlador {

    private final ClienteDAO clienteDAO;
    private IVista<Cliente> vista; // la vista que implementará IVista<Cliente>

    public Controlador(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    // Para inyectar la vista en el controlador
    public void setVista(IVista<Cliente> vista) {
        this.vista = vista;
    }

    public void iniciarVista() {
        vista.limpiarCampos();
        vista.estadoCampos(false);
    }

    public void altaCliente() {
        Cliente nuevo = vista.obtenerClienteDeFormulario();
        boolean respuesta = clienteDAO.insertarCliente(nuevo);

        // Comprobación de código en la DB
        if (nuevo.getCodigo() <= 0) {
            vista.mostrarMensaje("Código inválido para alta.");
            return;
        }

        if (!clienteDAO.comprobarCodigo(nuevo.getCodigo())) {
            vista.mostrarMensaje("No existe cliente con ese codigo.");
            return;
        }

        if (respuesta) {
            vista.mostrarMensaje("Cliente insertado correctamente.");
        } else {
            vista.mostrarMensaje("Error al insertar el cliente.");
        }

        vista.limpiarCampos();
    }

    public void bajaCliente() {

        // 1) Obtenemos el código desde la vista
        int codigo = vista.obtenerCodigoCliente();
        if (codigo < 0) {
            vista.mostrarMensaje("El código de cliente no es válido.");
            return;
        }

        if (!clienteDAO.comprobarCodigo(codigo)) {
            vista.mostrarMensaje("No existe cliente con ese codigo.");
            return;
        }

        // 2) Eliminamos
        boolean eliminado = clienteDAO.borrarCliente(codigo);
        // 3) Mostramos mensaje
        if (eliminado) {
            vista.mostrarMensaje("Cliente eliminado con éxito.");
        } else {
            vista.mostrarMensaje("Error al eliminar el cliente.");
        }
        vista.limpiarCampos();

    }

    public void modificarCliente() {
        // 1) Obtener un Cliente con los datos de la vista
        Cliente modificado = vista.obtenerClienteDeFormulario();

        // Comprobación de código en la DB
        if (modificado.getCodigo() <= 0) {
            vista.mostrarMensaje("Código inválido para alta.");
            return;
        }

        if (!clienteDAO.comprobarCodigo(modificado.getCodigo())) {
            vista.mostrarMensaje("No existe cliente con ese codigo.");
            return;
        }
        // 2) Editar en DAO
        boolean ok = clienteDAO.editarCliente(modificado);
        // 3) Mensaje
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
        Cliente c = clienteDAO.obtenerCliente(codigo);
        // 3) Si existe, mostrarlo; si no, mensaje de error
        if (c != null) {
            vista.mostrarCliente(c);
        } else {
            vista.mostrarMensaje("No se encontró un cliente con ese código.");
        }
    }
}
