/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.vista;

import com.mycompany.controlador.ControladorBase;
import com.mycompany.modelo.entidades.Cliente;

/**
 *
 * @author mihai
 */
public interface IVista<T> {

    // Mostrar mensajes al usuario
    void mostrarMensaje(String mensaje);

    // Limpiar todos los campos
    void limpiarCampos();

    // Habilitar o deshabilitar campos (o podría usarse un enum con modos)
    void estadoCampos(boolean estado);

    // Tomar datos del formulario y convertirlos a un objeto
    T obtenerEntidadDelFormulario();

    // Mostrar los datos de un objeto en el formulario
    void mostrarEntidad(T entidad);

    // Retornar el código (int) escrito en el campo correspondiente
    int obtenerCodigoEntidad();

    // Estado inicial
    void estadoInicial();

    //Cancelar accion
    void cancelarAccion();


    // Limitar entrada a solo letras, con longitud min y max, en un campo específico
    void limitarEntradaLetrasConLongitudMinYMax(javax.swing.JTextField campo, int min, int max);

    // Limitar entrada a solo cifras, con longitud exacta, en un campo específico
    void limitarEntradaACifraConLongitudExacta(javax.swing.JTextField campo, int longitud);

    // Comprobar email en un campo concreto
    void comprobarEmail(javax.swing.JTextField campo);

    // Limitar entrada a longitud min y max
    void limitarEntradaALongitudMinYMax(javax.swing.JTextField campo, int min, int max);

    void irAPaginaDeInicio();
    
    void enfocarCampo(String campo);
}
