/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Proveedor;
import com.mycompany.vista.IVistaConNIF;
import com.mycompany.vista.ProveedorVista;

/**
 *
 * @author mihai
 */
public class ControladorProveedor extends ControladorBase<Proveedor> {

    public ControladorProveedor(IGenericDAO<Proveedor, Integer> dao, IVistaConNIF<Proveedor> vista) {
        super(dao);
        this.vista = vista;
    }

    public void calcularLetraNIF() {
        if (vista instanceof IVistaConNIF) {
            IVistaConNIF<Proveedor> vistaConNIF = (IVistaConNIF<Proveedor>) vista;
            int nif = vistaConNIF.obtenerCifrasNIF();
            String cadenaLetras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int resto = nif % 23;
            vistaConNIF.establecerLetraNIF(String.valueOf(cadenaLetras.charAt(resto)));
        }
    }

    @Override
    public String validarEntidad(Proveedor proveedor) {
        if (proveedor == null) {
            vista.mostrarMensaje("Los datos del proveedor no pueden estar vacíos.");
            return "campoCodigo"; // Indica qué campo falló
        }

        if (proveedor.getNif().length() != 9) {
            vista.mostrarMensaje("El NIF debe tener 8 cifras y 1 letra.");
            return "campoNIF";
        }

        if (proveedor.getNombre().isEmpty() || proveedor.getNombre().length() > 15) {
            vista.mostrarMensaje("El nombre no puede estar vacío ni superar 15 caracteres.");
            return "campoNombre";
        }

        if (proveedor.getApellidos().isEmpty() || proveedor.getApellidos().length() > 35) {
            vista.mostrarMensaje("Los apellidos no pueden estar vacíos ni superar 35 caracteres.");
            return "campoApellidos";
        }

        if (!proveedor.getCodigoPostal().matches("\\d{5}")) {
            vista.mostrarMensaje("El código postal debe tener exactamente 5 cifras.");
            return "campoCP";
        }

        if (!proveedor.getTelefono().isEmpty() && !proveedor.getTelefono().matches("\\d{9}")) {
            vista.mostrarMensaje("El teléfono debe tener 9 cifras o estar vacío.");
            return "campoTelefono";
        }

        if (!proveedor.getMovil().isEmpty() && !proveedor.getMovil().matches("\\d{9}")) {
            vista.mostrarMensaje("El móvil debe tener 9 cifras o estar vacío.");
            return "campoMovil";
        }

        if (!proveedor.getFax().isEmpty() && !proveedor.getFax().matches("\\d{9}")) {
            vista.mostrarMensaje("El fax debe tener 9 cifras o estar vacío.");
            return "campoFax";
        }

        if (proveedor.getEmail().length() > 40) {
            vista.mostrarMensaje("El email no puede superar los 40 caracteres.");
            return "campoEmail";
        }

        return ""; // Si todo está bien, devolvemos una cadena vacía.
    }

    public boolean validarFormulario() {
        Proveedor proveedor = vista.obtenerEntidadDelFormulario();
        String campoErroneo = validarEntidad(proveedor);

        if (!campoErroneo.isEmpty()) {
            vista.enfocarCampo(campoErroneo);
            return false;
        }
        return true;
    }

    public void gestionarOperacion(ProveedorVista.MODO modo) {
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

    public void verificarCodigo(ProveedorVista.MODO modo) {
        comprobarExistencia(modo);
    }

    public void manejarNIF(String texto) {
        if (texto.length() == 8 && texto.matches("\\d+")) {
            calcularLetraNIF();
        } else {
            if (vista instanceof IVistaConNIF) {
                ((IVistaConNIF<Proveedor>) vista).establecerLetraNIF(""); // Limpiar el campo
            }
        }
    }

}
