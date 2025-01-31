/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controlador;

import com.mycompany.modelo.entidades.Cliente;
import com.mycompany.vista.interfaces.IVista;
import com.mycompany.modelo.dao.interfaces.IGenericDAO;
import com.mycompany.modelo.entidades.Proveedor;
import com.mycompany.vista.ArticulosVista;
import com.mycompany.vista.ClienteVista;
import com.mycompany.vista.ProveedorVista;

/**
 *
 * @author mihai
 */
public abstract class ControladorBase<T> {

    protected final IGenericDAO<T, Integer> dao;
    protected IVista<T> vista;

    public ControladorBase(IGenericDAO<T, Integer> dao) {
        this.dao = dao;
    }

    public void setVista(IVista<T> vista) {
        this.vista = vista;
    }

    public void alta() {
        T entidad = vista.obtenerEntidadDelFormulario();
        String campoErroneo = validarEntidad(entidad);

        if (!campoErroneo.isEmpty()) {
            vista.mostrarMensaje("Error en los datos. Revisa el campo indicado.");
            vista.enfocarCampo(campoErroneo);  // Nuevo método en la vista
            return;
        }
        boolean respuesta = dao.darDeAlta(entidad);
        vista.mostrarMensaje(respuesta ? "Registro insertado correctamente." : "Error al insertar.");
        vista.cancelarAccion();
    }

    public void baja() {
        int codigo = vista.obtenerCodigoEntidad();
        boolean eliminado = dao.darDeBaja(codigo);
        vista.mostrarMensaje(eliminado ? "Registro eliminado con éxito." : "Error al eliminar.");
        vista.cancelarAccion();
    }

    public void modificar() {
        T modificado = vista.obtenerEntidadDelFormulario();
        String campoErroneo = validarEntidad(modificado);

        if (!campoErroneo.isEmpty()) {
            vista.mostrarMensaje("Error en los datos. Revisa el campo indicado.");
            vista.enfocarCampo(campoErroneo);
            return;
        }
        boolean ok = dao.modificar(modificado);
        vista.mostrarMensaje(ok ? "Registro actualizado con éxito." : "Error al actualizar.");
    }

    public void buscar() {
        int codigo = vista.obtenerCodigoEntidad();
        if (codigo < 0) {
            vista.mostrarMensaje("Código inválido.");
            return;
        }
        T entidad = dao.obtenerPorID(codigo);
        if (entidad != null) {
            vista.mostrarEntidad(entidad);
        } else {
            vista.mostrarMensaje("No se encontró un registro con ese código.");
        }
    }

    public void volverAPaginaDeInicio() {
        vista.irAPaginaDeInicio();
    }

    public boolean comprobarExistencia(Enum<?> modo) {
        int codigo = vista.obtenerCodigoEntidad();

        if (codigo <= 0) {
            vista.mostrarMensaje("Código inválido.");
            return false; // Bloquea la operación si el código es inválido
        }

        boolean existe = dao.comprobarSiExistePorCodigo(codigo);

        if (modo instanceof ClienteVista.MODO) {
            return manejarExistencia((ClienteVista.MODO) modo, existe, codigo);
        } else if (modo instanceof ProveedorVista.MODO) {
            return manejarExistencia((ProveedorVista.MODO) modo, existe, codigo);
        } else if (modo instanceof ArticulosVista.MODO) {
            return manejarExistencia((ArticulosVista.MODO) modo, existe, codigo);
        }

        vista.mostrarMensaje("Modo desconocido.");
        return false;
    }

    private boolean manejarExistencia(ArticulosVista.MODO modo, boolean existe, int codigo) {
        switch (modo) {
            case ALTA:
                if (existe) {
                    vista.mostrarMensaje("El codigo ya existe. No se puede dar de alta");
                    return false;
                }
                vista.estadoCampos(true);
                return true;
            case BAJA:
                if (!existe) {
                    vista.mostrarMensaje("El código no existe. No se puede dar de baja.");
                    return false;
                }
                vista.mostrarEntidad(dao.obtenerPorID(codigo));
                vista.estadoCampos(false);
                return true;
            case MODIFICACION:
                if (!existe) {
                    vista.mostrarMensaje("El código no existe. No se puede modificar.");
                    return false;
                }
                vista.mostrarEntidad(dao.obtenerPorID(codigo));
                vista.estadoCampos(true);
                return true;
            default:
                vista.mostrarMensaje("Modo desconocido.");
                return false;
        }
    }

    private boolean manejarExistencia(ClienteVista.MODO modo, boolean existe, int codigo) {
        switch (modo) {
            case ALTA:
                if (existe) {
                    vista.mostrarMensaje("El cliente ya existe. No puedes darlo de alta nuevamente.");
                    return false;
                } else {
                    vista.mostrarMensaje("El código está disponible. Puedes continuar.");
                    activarFormulario(true);
                    return true;
                }

            case BAJA:
            case MODIFICACION:
                if (!existe) {
                    vista.mostrarMensaje("El cliente no existe en la base de datos.");
                    return false;
                } else {
                    T entidad = dao.obtenerPorID(codigo);
                    vista.mostrarEntidad(entidad);
                    activarFormulario(true);
                    return true;
                }

            default:
                return false;
        }
    }

    private boolean manejarExistencia(ProveedorVista.MODO modo, boolean existe, int codigo) {
        switch (modo) {
            case ALTA:
                if (existe) {
                    vista.mostrarMensaje("El proveedor ya existe. No puedes darlo de alta nuevamente.");
                    return false;
                } else {
                    vista.mostrarMensaje("El código está disponible. Puedes continuar.");
                    activarFormulario(true);
                    return true;
                }

            case BAJA:
            case MODIFICACION:
                if (!existe) {
                    vista.mostrarMensaje("El proveedor no existe en la base de datos.");
                    return false;
                } else {
                    T entidad = dao.obtenerPorID(codigo);
                    vista.mostrarEntidad(entidad);
                    activarFormulario(true);
                    return true;
                }

            default:
                return false;
        }
    }

    public void activarFormulario(boolean activar) {
        vista.estadoCampos(activar);
    }

    public abstract String validarEntidad(T entidad);
    // Cada controlador específico debe sobrescribir este método

}
