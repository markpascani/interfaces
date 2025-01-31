/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Articulo;
import com.mycompany.vista.ArticulosVista;
import com.mycompany.vista.ProveedorVista;
import com.mycompany.vista.interfaces.IVista;

/**
 *
 * @author Mihai Stinga
 */
public class ControladorArticulos extends ControladorBase<Articulo> {

    public ControladorArticulos(IGenericDAO<Articulo, Integer> dao, IVista<Articulo> vista) {
        super(dao);
        this.vista = vista;
    }

    @Override
    public String validarEntidad(Articulo articulo) {
        if (articulo == null) {
            vista.mostrarMensaje("Los datos del artículo no pueden estar vacíos.");
            return "campoCódigo";
        }

        if (articulo.getCodigo() <= 0) {
            vista.mostrarMensaje("El código del artículo debe ser un número positivo.");
            return "campoCódigo";
        }

        if (articulo.getDescripcion().isEmpty() || articulo.getDescripcion().length() > 25) {
            vista.mostrarMensaje("La descripción no puede estar vacía ni superar 25 caracteres.");
            return "campoDescripcion";
        }

        if (articulo.getStock() < 0) {
            vista.mostrarMensaje("El stock no puede ser negativo.");
            return "campoStock";
        }

        if (articulo.getStockMinimo() < 0) {
            vista.mostrarMensaje("El stock mínimo no puede ser negativo.");
            return "campoStockMinimo";
        }

        if (articulo.getPrecioCompra() <= 0) {
            vista.mostrarMensaje("El precio de compra debe ser mayor que 0.");
            return "campoPrecioCompra";
        }

        if (articulo.getPrecioVenta() <= 0) {
            vista.mostrarMensaje("El precio de venta debe ser mayor que 0.");
            return "campoPrecioVenta";
        }

        return ""; // Si todo está bien, devuelve una cadena vacía.
    }

    public boolean validarFormulario() {
        Articulo articulo = vista.obtenerEntidadDelFormulario();
        String campoErroneo = validarEntidad(articulo);

        if (!campoErroneo.isEmpty()) {
            vista.enfocarCampo(campoErroneo);
            return false;
        }
        return true;
    }

    public void gestionarOperacion(ArticulosVista.MODO modo) {
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

    public void verificarCodigo(ArticulosVista.MODO modo) {
        comprobarExistencia(modo);
    }

}
