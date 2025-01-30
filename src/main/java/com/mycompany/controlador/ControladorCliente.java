/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.vista.ClienteVista;
import com.mycompany.vista.IVista;
import com.mycompany.vista.IVistaConNIF;

/**
 *
 * @author mihai
 */
public class ControladorCliente extends ControladorBase<Cliente> {

    public ControladorCliente(IGenericDAO<Cliente, Integer> dao, IVistaConNIF<Cliente> vista) {
        super(dao);
        this.vista = vista;
    }

    public void calcularLetraNIF() {
        if (vista instanceof IVistaConNIF) {
            IVistaConNIF<Cliente> vistaConNIF = (IVistaConNIF<Cliente>) vista;
            int nif = vistaConNIF.obtenerCifrasNIF();
            String cadenaLetras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int resto = nif % 23;
            vistaConNIF.establecerLetraNIF(String.valueOf(cadenaLetras.charAt(resto)));
        }
    }

    @Override
    public String validarEntidad(Cliente cliente) {
        if (cliente == null) {
            vista.mostrarMensaje("Los datos del cliente no pueden estar vacíos.");
            return "campoCodigo"; // Indica qué campo falló
        }

        if (cliente.getNif().length() != 9) {
            vista.mostrarMensaje("El NIF debe tener 8 cifras y 1 letra.");
            return "campoNIF";
        }

        if (cliente.getNombre().isEmpty() || cliente.getNombre().length() > 15) {
            vista.mostrarMensaje("El nombre no puede estar vacío ni superar 15 caracteres.");
            return "campoNombre";
        }

        if (cliente.getApellidos().isEmpty() || cliente.getApellidos().length() > 35) {
            vista.mostrarMensaje("Los apellidos no pueden estar vacíos ni superar 35 caracteres.");
            return "campoApellidos";
        }

        if (!cliente.getCodigoPostal().matches("\\d{5}")) {
            vista.mostrarMensaje("El código postal debe tener exactamente 5 cifras.");
            return "campoCP";
        }

        if (!cliente.getTelefono().isEmpty() && !cliente.getTelefono().matches("\\d{9}")) {
            vista.mostrarMensaje("El teléfono debe tener 9 cifras o estar vacío.");
            return "campoTelefono";
        }

        if (!cliente.getMovil().isEmpty() && !cliente.getMovil().matches("\\d{9}")) {
            vista.mostrarMensaje("El móvil debe tener 9 cifras o estar vacío.");
            return "campoMovil";
        }

        if (!cliente.getFax().isEmpty() && !cliente.getFax().matches("\\d{9}")) {
            vista.mostrarMensaje("El fax debe tener 9 cifras o estar vacío.");
            return "campoFax";
        }

        if (cliente.getEmail().length() > 40) {
            vista.mostrarMensaje("El email no puede superar los 40 caracteres.");
            return "campoEmail";
        }

        return ""; // Si todo está bien, devolvemos una cadena vacía.
    }

    public boolean validarFormulario() {
        Cliente cliente = vista.obtenerEntidadDelFormulario();
        String campoErroneo = validarEntidad(cliente);

        if (!campoErroneo.isEmpty()) {
            vista.enfocarCampo(campoErroneo);
            return false;
        }
        return true;
    }

    public void gestionarOperacion(ClienteVista.MODO modo) {
        if (!validarFormulario()) {
            return;
        }

        switch (modo) {
            case ALTA:
                alta();
                break;
            case BAJA:
                baja();
                break;
            case MODIFICACION:
                modificar();
                break;
            default:
                vista.mostrarMensaje("No se ha seleccionado una operación.");
                return;
        }
        vista.cancelarAccion();
    }

    public void verificarCodigo(ClienteVista.MODO modo) {
        comprobarExistencia(modo);
    }

    public void manejarNIF(String texto) {
        if (texto.length() == 8 && texto.matches("\\d+")) {
            calcularLetraNIF();
        } else {
            if (vista instanceof IVistaConNIF) {
                ((IVistaConNIF<Cliente>) vista).establecerLetraNIF(""); // Limpiar el campo
            }
        }
    }

}
